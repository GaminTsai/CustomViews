package com.zzt8888.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzt8888.APIService;
import com.zzt8888.base.MaterialApplication;
import com.zzt8888.materialdesign.BuildConfig;
import com.zzt8888.tools.AndroidUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private MaterialApplication application;

    public ApplicationModule(MaterialApplication application) {
        this.application = application;
    }



    @Singleton
    @Provides
    protected  Context providesContext(){
        return application;
    }

    @Singleton
    @Provides
    protected APIService providesAPIService(Retrofit retrofit) {
        APIService apiService = retrofit.create(APIService.class);
        return apiService;
    }

    @Singleton
    @Provides
    protected Retrofit providesRetrofit(OkHttpClient httpClient) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        return new Retrofit.Builder().baseUrl(APIService.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
    }

    @Singleton
    @Provides
    protected OkHttpClient providersHttpClient(Context context, Interceptor interceptor) {
        File cacheFile = new File(context.getCacheDir(), "ESports_cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .cache(cache).addInterceptor(logging)
                .addInterceptor(interceptor)
                .build();
        return httpClient;
    }

    @Singleton
    @Provides
    protected Interceptor providersInterceptor(Context context) {
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            HttpUrl url = request.url();
            boolean loadCache = Boolean.valueOf(url.queryParameter(APIService.CACHE_PARAM_KEY));

            // 去除多余的请求参数
            url = url.newBuilder()
                    .removeAllQueryParameters(APIService.CACHE_PARAM_KEY)
                    .build();

            Request.Builder requestBuilder = request.newBuilder()
                    .url(url);
            // 如果手动设置了cacheControl 则优先使用
            if ((!AndroidUtil.isNetworkAvailable(context) || loadCache) && request.cacheControl() != null) {
                requestBuilder.cacheControl(CacheControl.FORCE_CACHE);
            }
            request = requestBuilder.build();
            Response response = chain.proceed(request);
            Response.Builder builder = response.newBuilder();
            if (AndroidUtil.isNetworkAvailable(context) && !loadCache) {
                builder.header("Cache-Control", "public, max-age=0");
            } else {
                // tolerate 4-weeks stale
                int maxStale = 60 * 60 * 24 * 28;
                builder.header("Cache-Control", "public, only-if-cached, max-stale" + maxStale);
            }
            builder.removeHeader("Pragma");

            return builder.build();
        };
        return interceptor;
    }
}

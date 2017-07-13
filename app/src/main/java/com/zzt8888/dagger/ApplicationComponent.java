package com.zzt8888.dagger;

import android.content.Context;

import com.zzt8888.APIService;
import com.zzt8888.base.MaterialApplication;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MaterialApplication application);

    Context context();

    APIService apiService();

    OkHttpClient okHttpClient();

    Interceptor interceptor();

}

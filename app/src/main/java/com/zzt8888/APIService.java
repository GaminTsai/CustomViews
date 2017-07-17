package com.zzt8888;

import com.zzt8888.beans.ContentBean;
import com.zzt8888.beans.TypeDataBean;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface APIService {

    String CACHE_PARAM_KEY = "CACHE_PARAM_KEY";

    String BASE_URL = "http://gank.io/api/";


    /**
     * http://gank.io/api/day/2017/07/13
     */

    /**
     * 获取某几日干货网站数据:
     * http://gank.io/api/history/content/2/1
     * 注： 2 代表 2 个数据，1 代表：取第一页数据
     */

    @GET("history/content/{sum}/{index}")
    Observable<ContentBean> getHistoryContent(@Path("sum") int sum, @Path("index") int index);


    /**
     * http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于 0
     * 第几页：数字，大于 0
     */
    @GET("data/{category}/{sum}/{index}")
    Observable<TypeDataBean> getDataByCategory(@Path("category") String category, @Path("sum") int sum, @Path("index") int index);


}
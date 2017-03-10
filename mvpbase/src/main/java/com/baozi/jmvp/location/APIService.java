package com.baozi.jmvp.location;


import com.baozi.jmvp.bean.BaseResponse;


import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 */
public interface APIService {
    /**
     * 在这里写请求接口
     */
    @GET("login")
    Observable<BaseResponse<String>> login(@QueryMap HashMap<String, Object> params);

    /**
     * h5交互获取页面数据.
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    @GET
    Observable<BaseResponse<String>> loadWebViewData(@Url String url, @QueryMap HashMap<String, Object> params);
}

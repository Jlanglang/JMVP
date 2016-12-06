package com.baozi.mvpdemp.location;


import com.baozi.mvpdemp.bean.BaseResponse;


import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 */
public interface APIService {
    /**
     * 在这里写请求接口
     */
    @GET("login")
    Observable<BaseResponse<String>> login(@QueryMap HashMap<String,Object> params);
}

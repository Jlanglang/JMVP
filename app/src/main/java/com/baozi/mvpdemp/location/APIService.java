package com.baozi.mvpdemp.location;


import com.baozi.mvpdemp.bean.BaseResponse;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by baozi on 2016/12/5.
 */
public interface APIService {
    /**
     * 在这里写请求接口
     */
    @GET
    Observable<BaseResponse<String>> getData();
}

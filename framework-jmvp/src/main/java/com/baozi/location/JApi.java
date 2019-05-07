package com.baozi.location;


import com.baozi.linfeng.location.SimpleParams;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Jlanglang on 2017/8/29 0029.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */

public interface JApi {

    /**
     * 上传文件
     *
     * @param url
     * @param params
     * @return
     */
    @POST
    @Multipart
    Observable<String> BasePost(@Url String url, @PartMap HashMap<String, RequestBody> params);

    /**
     * 通用POST
     *
     * @param url
     * @param json
     * @return
     */
    @POST
    Observable<String> BasePost(@Url String url, @Body String json);

    /**
     * 通用POST
     *
     * @param url
     * @param json
     * @return
     */
    @POST
    Observable<String> BasePost(@Url String url, @Body SimpleParams json);

    /**
     * 通用get
     *
     * @param url
     * @return
     */
    @GET
    Observable<String> BaseGet(@Url String url, @QueryMap SimpleParams params);

}

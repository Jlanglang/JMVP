package com.baozi.location;


import com.baozi.linfeng.location.retrofit.RetrofitUtil;

/**
 * Created by Jlanglang on 2017/8/29 0029.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */

public class JApiImpl {
    public static JApi getJApi() {
        return RetrofitUtil.getApi(JApi.class);
    }
}
package com.linfeng.rx_retrofit_network.location.retrofit;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.linfeng.rx_retrofit_network.NetWorkManager;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //拿到请求体
        Request request = chain.request();
        //读接口上的@Headers里的注解配置
        String cacheControl = request.cacheControl().toString();

        //判断没有网络并且添加了@Headers注解,才使用网络缓存.
        if (!RetrofitUtil.isOpenInternet(NetWorkManager.getContext()) && !TextUtils.isEmpty(cacheControl)) {
            //重置请求体;
            request = request.newBuilder()
                    //强制使用缓存
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        //如果没有添加注解,则不缓存
        if (TextUtils.isEmpty(cacheControl) || "no-store".contains(cacheControl)) {
            //响应头设置成无缓存
            cacheControl = "no-store";
        } else if (RetrofitUtil.isOpenInternet(NetWorkManager.getContext())) {
            //如果有网络,则将缓存的过期时间,设置为0,获取最新数据
            cacheControl = "public, max-age=" + 0;
        } else {
            //...如果无网络,则根据@headers注解的设置进行缓存.
        }
        Response response = chain.proceed(request);
//                    Log.i("httpInterceptor", cacheControl);
        return response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma")
                .build();
    }
}

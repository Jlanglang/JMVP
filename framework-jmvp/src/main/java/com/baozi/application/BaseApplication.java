package com.baozi.application;

import android.app.Application;

import com.baozi.linfeng.NetWorkManager;

import java.io.IOException;
import java.util.zip.Inflater;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.InflaterSource;
import okio.Okio;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //比如www.baidu.com
        //3个参数,hostUrl,成功码,context
        NetWorkManager.addInterceptor(new InflaterRequestInterceptor());
        NetWorkManager.init("http://rpm.locatesys.cn/", "0", this);
    }

    public class InflaterRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null) {
                return chain.proceed(originalRequest);
            }
            Response proceed = chain.proceed(originalRequest);
            ResponseBody body = proceed.body();
            return proceed.newBuilder().body(inflater(body)).build();
        }

        private ResponseBody inflater(final ResponseBody body) {
            return new ResponseBody() {
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public long contentLength() {
                    return -1; // 无法提前知道压缩后的数据大小
                }

                @Override
                public BufferedSource source() {
                    return Okio.buffer(new InflaterSource(body.source(), new Inflater(true)));
                }
            };
        }
    }

}

package com.linfeng.rx_retrofit_network.location.retrofit;

import android.graphics.Bitmap;
import android.util.Log;

import com.linfeng.rx_retrofit_network.converter.FastjsonConverterFactory;
import com.linfeng.rx_retrofit_network.utils.BitmapUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Sunflower on 2015/11/4.
 */
public class RetrofitUtil {
    /**
     * 服务器地址
     */
    private static String API_HOST = "baseUrl";

    public static Retrofit getInstance() {
        return Instance.getInstance();
    }

    private static class Instance {
        private static Retrofit retrofit = getRetrofit();

        public static Retrofit getInstance() {
            return retrofit;
        }

        private static Retrofit getRetrofit() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("RxJava", message);
                }
            });
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(API_HOST)
                    .addConverterFactory(FastjsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit;
        }
    }


    /**
     * 生成对应的RequestBody
     *
     * @param param 参数
     * @return RequestBody
     */
    public static RequestBody createRequestBody(int param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(long param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(float param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(Object param) {
        return createRequestBody(String.valueOf(param));
    }

    public static RequestBody createRequestBody(double param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(String param) {
        return RequestBody.create(MediaType.parse("text/plain"), param);
    }

    protected RequestBody createRequestBody(File param) {
        return RequestBody.create(MediaType.parse("image/*"), param);
    }

    public static HashMap<String, RequestBody> createRequestBody(HashMap<String, Object> params) {
        HashMap<String, RequestBody> RequestBodyHashMap = new HashMap<>();
        Set<String> strings = params.keySet();
        for (String str : strings) {
            Object param = params.get(str);
            RequestBodyHashMap.put(str, createRequestBody(param));
        }
        return RequestBodyHashMap;
    }

    public static HashMap<String, RequestBody> creatRequestBodyImages(List<String> images, int dstWidth, int dstHeight) {
        if (images == null) {
            return null;
        }
        HashMap<String, RequestBody> photoRequestMap = new HashMap<>();
        int size = images.size();
        for (int i = 0; i < size; i++) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //读取文件并压缩图片
            Bitmap scaleBitmap = BitmapUtils.createScaleBitmap(images.get(i), dstWidth, dstHeight);
            //转化为二进制流数组
            scaleBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            scaleBitmap.recycle();
            photoRequestMap.put("file" + i + "\";filename=\"" +
                    System.currentTimeMillis(), RequestBody.create(MediaType.parse("multipart/form-data"), byteArray));
        }
        return photoRequestMap;
    }
}

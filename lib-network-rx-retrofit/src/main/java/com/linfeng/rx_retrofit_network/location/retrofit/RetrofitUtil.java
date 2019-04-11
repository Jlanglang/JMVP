package com.linfeng.rx_retrofit_network.location.retrofit;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.linfeng.rx_retrofit_network.BuildConfig;
import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.converter.GsonConverterFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Sunflower on 2015/11/4.
 */
public class RetrofitUtil {


    /**
     * 服务器地址
     */
    private static String API_HOST;
    private static Application mContext;
    private static final HashMap<Class, Object> apis = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T getApi(Class<T> c) {
        Object o = apis.get(c);
        if (null == o) {
            o = getInstance().create(c);
            apis.put(c, o);
        }
        return (T) o;
    }

    public static synchronized void init(String baseUrl, Application context) {
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        mContext = context;
        API_HOST = baseUrl;
        Instance.retrofit = Instance.getRetrofit();
        apis.clear();
    }

    public static Retrofit getInstance() {
        return Instance.retrofit;
    }

    private static class Instance {
        private static Retrofit retrofit = getRetrofit();

        private static Retrofit getRetrofit() {
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    //拿到请求体
                    Request request = chain.request();
                    //读接口上的@Headers里的注解配置
                    String cacheControl = request.cacheControl().toString();

                    //判断没有网络并且添加了@Headers注解,才使用网络缓存.
                    if (!isOpenInternet(mContext) && !TextUtils.isEmpty(cacheControl)) {
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
                    } else if (isOpenInternet(mContext)) {
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
            };
            OkHttpClient.Builder client = new OkHttpClient.Builder()
//                    .proxy(Proxy.NO_PROXY)//禁用代理，防止抓包
                    //拦截并设置缓存
                    .addNetworkInterceptor(cacheInterceptor)
                    //拦截并设置缓存
                    .addInterceptor(cacheInterceptor)
                    .cache(new Cache(mContext.getCacheDir(), 10240 * 1024));
            for (Interceptor i : NetWorkManager.mInterceptors) {
                client.addInterceptor(i);
            }
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client.addInterceptor(interceptor);
            }
            return new Retrofit.Builder()
                    .client(client.build())
                    .baseUrl(API_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
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

    /**
     * 此方法获取的bitmap为原始大小,图片文件过大可能造成oom
     *
     * @param images 图片集合
     */
    public static HashMap<String, RequestBody> creatRequestBodyImagesFiles(List<String> images) {
        if (images == null) {
            return null;
        }
        HashMap<String, RequestBody> photoRequestMap = new HashMap<>();
        int size = images.size();
        for (int i = 0; i < size; i++) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(images.get(i));
            //转化为二进制流数组
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            bitmap.recycle();
            photoRequestMap.put("file" + i + "\";filename=\"" +
                    System.currentTimeMillis(), RequestBody.create(MediaType.parse("multipart/form-data"), byteArray));
        }
        return photoRequestMap;
    }

    /**
     * 建议调用此方法前,先将bitmap压缩.
     *
     * @param images 图片集合
     */
    public static HashMap<String, RequestBody> creatRequestBodyBitmap(List<Bitmap> images) {
        if (images == null) {
            return null;
        }
        HashMap<String, RequestBody> photoRequestMap = new HashMap<>();
        int size = images.size();
        for (int i = 0; i < size; i++) {
            Bitmap bitmap = images.get(i);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //转化为二进制流数组
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            photoRequestMap.put("file" + i + "\";filename=\"" +
                    System.currentTimeMillis(), RequestBody.create(MediaType.parse("multipart/form-data"), byteArray));
        }
        return photoRequestMap;
    }

    private RequestBody buildMultipartFormRequestBody(List<File> files, String filesKey, HashMap<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        Set<String> strings = params.keySet();
        for (String key : strings) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                    RequestBody.create(null, params.get(key)));
        }
        if (files == null) {
            files = new ArrayList<>();
        }
        int size = files.size();
        if (size == 0) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + filesKey + "\""),
                    RequestBody.create(null, "[]"));
        }
        for (int i = 0; i < size; i++) {
            //根据文件名设置contentType
            builder.addPart(Headers.of("Content-Disposition",
                    "form-data; name=\"" + filesKey + "\"; fileName=\"" + System.currentTimeMillis() + "\""),
                    RequestBody.create(MediaType.parse("multipart/form-data"), files.get(i)));
        }
        return builder.build();

    }

    /**
     * 判断网络是否打开
     *
     * @return 是否打开网络
     */
    private static boolean isOpenInternet(Context context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        if (con != null) {
            boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
            boolean intenter = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
            return wifi || intenter;
        }
        return false;
    }
}

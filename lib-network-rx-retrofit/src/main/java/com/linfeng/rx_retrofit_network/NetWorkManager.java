package com.linfeng.rx_retrofit_network;


import android.app.Application;

import com.linfeng.rx_retrofit_network.factory.EncodeDecodeKey;
import com.linfeng.rx_retrofit_network.location.APICallBack;
import com.linfeng.rx_retrofit_network.location.onExceptionListener;
import com.linfeng.rx_retrofit_network.location.retrofit.RetrofitUtil;
import com.linfeng.rx_retrofit_network.location.rxandroid.RxParseInfo;

import java.net.Proxy;
import java.util.HashSet;

import okhttp3.Interceptor;

/**
 * 网络管理类
 */
public final class NetWorkManager {
    private static onExceptionListener errorListener;
    /**
     * code状态码处理回调
     */
    private static APICallBack apiExceptionCallBacks;
    /**
     * 私钥
     */
    private static String privateKey;
    /**
     * 公钥
     */
    private static String publicKey;

    private static Application mContext;

    public static HashSet<Interceptor> mInterceptors = new HashSet<>();

    public static HashSet<RxParseInfo> parseInterceptors = new HashSet<>();

    private static boolean mOpenApiException;

    private static String mSuccessCode;

    private static Proxy proxy; // 代理


    private NetWorkManager() {

    }

    /**
     * 初始化
     */
    public static void init(String baseUrl, String successCode, Application context) {
        mContext = context;
        mSuccessCode = successCode;
        RetrofitUtil.init(baseUrl, context);
    }

    /**
     * 初始化密钥
     *
     * @param key1 私钥
     * @param key2 公钥
     */
    public static void initKey(String key1, String key2) {
        privateKey = key1;
        publicKey = key2;
    }

    public static void addInterceptor(Interceptor interceptor) {
        mInterceptors.add(interceptor);
    }

    /**
     * 注册 异常时提示消息
     *
     * @param msg 消息
     */
    public static void setExceptionListener(onExceptionListener msg) {
        errorListener = msg;
    }

    /**
     * 获取异常时提示消息
     *
     * @return 消息
     */
    public static onExceptionListener getExceptionListener() {
        return errorListener;
    }

    /**
     * 添加全局code状态处理
     *
     * @param callBack 回调
     * @type 保证唯一性
     */
    public static void setApiCallBack(APICallBack callBack) {
        apiExceptionCallBacks = callBack;
    }

    /**
     * 获取状态处理回调
     *
     * @return APIExceptionCallBack回调接口
     */
    public static APICallBack getApiCallback() {
        return apiExceptionCallBacks;
    }

    public static EncodeDecodeKey getKey() {
        return Instance.key;
    }

    public static boolean isOpenApiException() {
        return mOpenApiException;
    }

    public static void setOpenApiException(boolean openApiException) {
        mOpenApiException = openApiException;
    }

    public static String getSuccessCode() {
        return mSuccessCode;
    }

    public static Application getContext() {
        return mContext;
    }


    public static Proxy getProxy() {
        return proxy;
    }


    public static HashSet<RxParseInfo> getRxParseInfos() {
        return parseInterceptors;
    }

    public static void addParseInterceptor(RxParseInfo parseInterceptor) {
        parseInterceptors.add(parseInterceptor);
    }

    /**
     * 加密对象单例
     */
    private static class Instance {
        private static EncodeDecodeKey key = getKey();

        private static EncodeDecodeKey getKey() {
            return new EncodeDecodeKey(privateKey, publicKey);
        }
    }
}

package com.linfeng.rx_retrofit_network;


import android.app.Application;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.linfeng.rx_retrofit_network.factory.EncodeDecodeKey;
import com.linfeng.rx_retrofit_network.location.APIExceptionCallBack;
import com.linfeng.rx_retrofit_network.location.APISuccessCallback;
import com.linfeng.rx_retrofit_network.location.retrofit.RetrofitUtil;

import java.util.HashMap;
import java.util.HashSet;

import okhttp3.Interceptor;

/**
 * 网络管理类
 */
public final class NetWorkManager {
    public final static int API_COMMON_EXCEPTION_CODE = Integer.MIN_VALUE;
    /**
     * 提示消息集合
     */
    private final static HashMap<Class<? extends Throwable>, String> errorMsgMap;
    /**
     * code状态码处理回调
     */
    private final static SparseArray<APIExceptionCallBack> apiExceptionCallBacks;
    /**
     * 私钥
     */
    private static String privateKey;
    /**
     * 公钥
     */
    private static String publicKey;

    public static Application mContext;
    public static HashSet<Interceptor> mInterceptors = new HashSet<>();

    /**
     * 初始化Network.
     */
    static {
        apiExceptionCallBacks = new SparseArray<>();
        errorMsgMap = new HashMap<>();
//        errorMsgMap.put(UnknownHostException.class, "请打开网络");
//        errorMsgMap.put(SocketTimeoutException.class, "请求超时");
//        errorMsgMap.put(ConnectException.class, "连接失败");
//        errorMsgMap.put(HttpException.class, "网络错误");
    }

    private static boolean mOpenApiException;

    private NetWorkManager() {

    }

    /**
     * 初始化
     */
    public static void init(String baseUrl, int successCode, Application context) {
        mContext = context;
        RetrofitUtil.init(baseUrl, context);
        putApiCallback(APISuccessCallback.INSTANCE, successCode);
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
     * @param t   异常类型
     * @param msg 消息
     */
    public static void putErrorMsg(@NonNull Class<? extends Throwable> t, String msg) {
        errorMsgMap.put(t, msg);
    }

    /**
     * 获取异常时提示消息
     *
     * @param t 异常类型
     * @return 消息
     */
    public static String getErrorMsg(@NonNull Class<? extends Throwable> t) {
        return errorMsgMap.get(t);
    }

    /**
     * 添加自定义code状态处理
     *
     * @param callBack 回调
     * @param codes    服务返回code状态
     * @return
     */
    public static void putApiCallback(APIExceptionCallBack callBack, int... codes) {
        int length = codes.length;
        for (int i = 0; i < length; i++) {
            int code = codes[i];
            apiExceptionCallBacks.put(code, callBack);
        }
    }

    /**
     * 添加全局code状态处理
     *
     * @param callBack 回调
     * @return
     */
    public static void putCommonAPIExceptionCallback(APIExceptionCallBack callBack) {
        apiExceptionCallBacks.put(API_COMMON_EXCEPTION_CODE, callBack);
    }

    /**
     * 获取状态处理回调
     *
     * @param code 服务器返回状态码
     * @return APIExceptionCallBack回调接口
     */
    public static APIExceptionCallBack getApiCallback(int code) {
        return apiExceptionCallBacks.get(code);
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

package com.linfeng.rx_retrofit_network;


import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.linfeng.rx_retrofit_network.factory.EncodeDecodeKey;
import com.linfeng.rx_retrofit_network.location.APIExceptionCallBack;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.HttpException;

public final class NetWorkManager {
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

    /**
     * 初始化Network.
     */
    static {
        errorMsgMap = new HashMap<>();
        errorMsgMap.put(UnknownHostException.class, "请打开网络");
        errorMsgMap.put(SocketTimeoutException.class, "请求超时");
        errorMsgMap.put(ConnectException.class, "连接失败");
        errorMsgMap.put(HttpException.class, "网络错误");

        apiExceptionCallBacks = new SparseArray<>();
    }

    private NetWorkManager() {

    }

    /**
     * 设置默认失败提示
     *
     * @param defaultMsg String
     */
    public static void init(String defaultMsg) {
        putErrorMsg(Exception.class, defaultMsg);
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
        String s = errorMsgMap.get(t);
        return s == null ? "" : s;
    }

    /**
     * 添加自定义code状态处理
     *
     * @param code     服务返回code状态
     * @param callBack 回调
     * @return
     */
    public static void putApiCallback(int code, APIExceptionCallBack callBack) {
        apiExceptionCallBacks.put(code, callBack);
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

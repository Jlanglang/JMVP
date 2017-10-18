package com.linfeng.rx_retrofit_network;


import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.linfeng.rx_retrofit_network.factory.EncodeDecodeKey;
import com.linfeng.rx_retrofit_network.location.APIExceptionCallBack;

import java.util.HashMap;

public final class NetWorkManager {
    /**
     * 单例
     */
    private final static NetWorkManager netWorkManager = new NetWorkManager();
    /**
     * 提示消息集合
     */
    private final static HashMap<Class<? extends Throwable>, String> errorMap;
    /**
     * code状态码处理回调
     */
    private final static SparseArray<APIExceptionCallBack> apiExcePitonCallBacks;
    /**
     * 私钥
     */
    private static String privateKey;
    /**
     * 公钥
     */
    private static String publicKey;

    /**
     * 初始化集合.
     */
    static {
        errorMap = new HashMap<>();
        apiExcePitonCallBacks = new SparseArray<>();
    }

    private NetWorkManager() {

    }

    public static NetWorkManager init(String defaultMsg) {
        return putErrorMsg(Exception.class, defaultMsg);
    }

    public static NetWorkManager initKey(String key1, String key2) {
        privateKey = key1;
        publicKey = key2;
        return netWorkManager;
    }

    public static NetWorkManager putErrorMsg(@NonNull Class<? extends Throwable> t, String msg) {
        errorMap.put(t, msg);
        return netWorkManager;
    }

    public static String getErrorMsg(@NonNull Class<? extends Throwable> t) {
        String s = errorMap.get(t);
        return s == null ? "" : s;
    }

    public static NetWorkManager putApiCallback(int code, APIExceptionCallBack callBack) {
        apiExcePitonCallBacks.put(code, callBack);
        return netWorkManager;
    }

    public static APIExceptionCallBack getApiCallback(int code) {
        return apiExcePitonCallBacks.get(code);
    }

    public static EncodeDecodeKey getKey() {
        return Instance.key;
    }

    /**
     * 加密对象单例.
     */
    private static class Instance {
        private static EncodeDecodeKey key = getKey();

        private static EncodeDecodeKey getKey() {
            return new EncodeDecodeKey(privateKey, publicKey);
        }
    }
}

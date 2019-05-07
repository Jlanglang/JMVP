package com.linfeng.rx_retrofit_network.location.rxandroid;

import android.text.TextUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.APICallBack;
import com.linfeng.rx_retrofit_network.location.APIException;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求通用设置转换器
 * 组件化的情况下,如果解析失败,请使用JsonArrayParesTransformer,JsonParesTransformer
 * 当data可能为null时,请使用JsonArrayParesTransformer,JsonParesTransformer
 */
public class NetWorkTransformer implements ObservableTransformer<String, String> {
    private static final int DEFAULT_TIME_OUT = 30;
    private static final int DEFAULT_RETRY = 5;

    @Override
    public ObservableSource<String> apply(Observable<String> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .timeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY)
                .map(response -> {
                    JsonElement jsonElement = JSONFactory.parseJson(response);
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();

                    RxParseInfo parseInfo = getParseInfo(asJsonObject);
                    if (parseInfo == null) {
                        return response;
                    }

                    //拿到后台返回code
                    String code = JSONFactory.getValue(jsonElement, parseInfo.getCodeKey());
                    String msg = JSONFactory.getValue(jsonElement, parseInfo.getMsgKey());
                    String data = JSONFactory.getValue(jsonElement, parseInfo.getDataKey());
                    //如果请求成功,直接返回数据
                    if (TextUtils.equals(code, NetWorkManager.getSuccessCode())) {
                        return data;
                    }

                    String errorMsg = null;
                    //通过code获取注册的接口回调.
                    APICallBack apiCallback = NetWorkManager.getApiCallback();
                    if (apiCallback != null) {
                        String callbackMsg = apiCallback.callback(code, response);
                        if (!TextUtils.isEmpty(callbackMsg)) {
                            errorMsg = callbackMsg;
                        }
                    }

                    //如果callback不处理,并打开isOpenApiException,则抛出服务器返回msg信息
                    if (TextUtils.isEmpty(errorMsg) && NetWorkManager.isOpenApiException()) {
                        errorMsg = msg;
                    }
                    //抛出异常,走到onError.
                    throw new APIException(code, errorMsg);
                });
    }

    private RxParseInfo getParseInfo(JsonObject asJsonObject) throws Exception {
        HashSet<RxParseInfo> parseInterceptors = NetWorkManager.getRxParseInfos();
        for (RxParseInfo p : parseInterceptors) { // 优先判断自定义添加的解析
            if (p.hasKey(asJsonObject)) {
                return p;
            }
        }
        if (RxParseInfo.DEFAULT.hasKey(asJsonObject)) { // 判断默认的,如果不符合,说明配置错误.
            return RxParseInfo.DEFAULT;
        }
        return null;
    }
}
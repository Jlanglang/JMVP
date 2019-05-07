package com.linfeng.rx_retrofit_network.location.rxandroid;

import android.text.TextUtils;

import com.google.gson.JsonElement;
import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.APICallBack;
import com.linfeng.rx_retrofit_network.location.APIException;

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
                    //拿到后台返回code
                    String codeKey = NetWorkManager.getCodeKey();
                    String code = JSONFactory.getValue(jsonElement, codeKey);

                    //如果请求成功,直接返回数据
                    if (code.equals(NetWorkManager.getSuccessCode() + "")) {
                        String dataKey = NetWorkManager.getDataKey();
                        return JSONFactory.getValue(jsonElement, dataKey);
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
                    //如果开启ApiException,抛出服务器返回的异常
                    if (NetWorkManager.isOpenApiException()) {
                        String msgKey = NetWorkManager.getMsgKey();
                        errorMsg = JSONFactory.getValue(jsonElement, msgKey);
                    }
                    //抛出异常,走到onError.
                    throw new APIException(code, errorMsg);
                });
    }

}
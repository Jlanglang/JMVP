package com.linfeng.rx_retrofit_network.location.rxandroid;

import android.text.TextUtils;
import android.util.SparseArray;

import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.location.APICallBack;
import com.linfeng.rx_retrofit_network.location.APIException;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;

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
 *
 * @param <T>
 */
public class NetWorkTransformer<T> implements ObservableTransformer<BaseResponse<T>, T> {
    private static final int DEFAULT_TIME_OUT = 30;
    private static final int DEFAULT_RETRY = 5;

    @Override
    public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .timeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY)
                .map(tBaseResponse -> {
                    //拿到后台返回code
                    int code = tBaseResponse.getCode();
                    //如果请求成功,直接返回数据
                    if (code == NetWorkManager.getSuccessCode()) {
                        return tBaseResponse.getData();
                    }
                    String errorMsg = null;
                    //通过code获取注册的接口回调.
                    APICallBack apiCallback = NetWorkManager.getApiCallback();
//                    for (int i = 0; i < apiCallback.size(); i++) {
//                        APICallBack callBack = apiCallback.valueAt(i);
                    if (apiCallback != null) {//否则走单个注册处理
                        String callbackMsg = apiCallback.callback(tBaseResponse.getCode(), tBaseResponse);
                        if (!TextUtils.isEmpty(callbackMsg)) {
                            errorMsg = callbackMsg;
                        }
                    }
//                    }
                    //如果开启ApiException,抛出服务器返回的异常
                    if (NetWorkManager.isOpenApiException()) {
                        errorMsg = tBaseResponse.getMsg();
                    }
                    //抛出异常,走到onError.
//                    throw new APIException(tBaseResponse);
                    throw new APIException(code, errorMsg);
                });
    }

}
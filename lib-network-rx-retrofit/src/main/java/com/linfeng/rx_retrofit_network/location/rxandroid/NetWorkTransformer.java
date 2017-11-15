package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.location.APIException;
import com.linfeng.rx_retrofit_network.location.APIExceptionCallBack;
import com.linfeng.rx_retrofit_network.location.APISuccessCallback;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求通用设置转换器
 * 组件化的情况下,如果解析失败,请使用JsonArrayParesTransformer,JsonParesTransformer
 *
 * @param <T>
 */
public class NetWorkTransformer<T> implements ObservableTransformer<BaseResponse<T>, T> {
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_RETRY = 5;


    /**
     * 处理请求结果,BaseResponse
     *
     * @param response 请求结果
     * @return 过滤处理, 返回只有data的Observable
     */
    private Observable<T> flatResponse(final BaseResponse<T> response) {
        return Observable.just(response)
                .map(new Function<BaseResponse<T>, T>() {
                    @Override
                    public T apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
                        //拿到后台返回code
                        int code = tBaseResponse.getCode();
                        //通过code获取注册的接口回调.
                        APIExceptionCallBack apiCallback = NetWorkManager.getApiCallback(code);
                        //如果请求成功,直接返回数据
                        if (apiCallback == APISuccessCallback.INSTANCE) {
                            return tBaseResponse.getData();//请求成功
                        }
                        //code为错误码.获取全局统一处理
                        APIExceptionCallBack commonApiCallback = NetWorkManager.getApiCallback(NetWorkManager.API_COMMON_EXCEPTION_CODE);
                        String errorMsg = null;
                        //如果配置全局,走全局处理
                        if (commonApiCallback != null) {
                            errorMsg = commonApiCallback.callback(tBaseResponse);
                        } else if (apiCallback != null) {//否则走单个注册处理
                            errorMsg = apiCallback.callback(tBaseResponse);
                        }
                        //如果开启Apixception,抛出服务器返回的异常
                        if (NetWorkManager.isOpenApiException()) {
                            errorMsg = tBaseResponse.getMsg();
                        }
                        //抛出异常,走到onError.
                        throw new APIException(code, errorMsg);
                    }
                });
    }

    @Override
    public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .timeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY)
                .flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        return flatResponse(tBaseResponse);
                    }
                });
    }

}
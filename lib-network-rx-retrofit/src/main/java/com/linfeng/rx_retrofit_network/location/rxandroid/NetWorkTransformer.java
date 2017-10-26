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
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求通用设置转换器
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
                .filter(new Predicate<BaseResponse<T>>() {
                    @Override
                    public boolean test(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
                        //如果数据为空,抛出空指针异常
                        if (tBaseResponse.getData() == null) {
                            throw new NullPointerException();
                        }
                        //拿到后台返回code
                        int code = tBaseResponse.getCode();
                        //通过code获取注册的接口回调.
                        APIExceptionCallBack apiCallback = NetWorkManager.getApiCallback(code);
                        if (apiCallback != null) {
                            if (apiCallback instanceof APISuccessCallback) {
                                return true;//请求成功
                            } else {
                                //请求失败,抛出自定义异常.触发注册的自定义接口回调
                                apiCallback.callback(tBaseResponse);
                                throw new APIException(tBaseResponse.getCode(), tBaseResponse.getMsg());
                            }
                        }
                        return true;//如果该code,获取不到APIExceptionCallBack,说明该code不需要处理
                    }
                })
                .map(new Function<BaseResponse<T>, T>() {
                    @Override
                    public T apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
                        return tBaseResponse.getData();
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
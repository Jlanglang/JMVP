package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.location.APIException;
import com.linfeng.rx_retrofit_network.location.BaseResponse;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @param <T>
 */
public class SimpleTransformer<T> implements Observable.Transformer<BaseResponse<T>, T> {
    private static final int Defautl_TIME_OUT = 5;
    private static final int DEFAUTL_RETRY = 5;

    @Override
    public Observable<T> call(Observable<BaseResponse<T>> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .timeout(Defautl_TIME_OUT, TimeUnit.SECONDS)
                .retry(DEFAUTL_RETRY)
                .flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> tBaseResponse) {
                        return flatResponse(tBaseResponse);
                    }
                });
    }

    /**
     * 处理请求结果,BaseResponse
     *
     * @param response 请求结果
     * @return 过滤处理, 返回只有data的Observable
     */
    private Observable<T> flatResponse(final BaseResponse<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    if (response.isSuccess()) {//请求成功
                        subscriber.onNext(response.getData());
                    } else {//请求失败
                        subscriber.onError(new APIException(response.getResultCode(), response.getMsg()));
                        return;
                    }
                }
                if (!subscriber.isUnsubscribed()) {
                    //请求完成
                    subscriber.onCompleted();
                }
            }
        });
    }
}
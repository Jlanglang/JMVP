package com.baozi.jmvp.location.rxandroid;

import com.baozi.jmvp.bean.BaseResponse;
import com.baozi.jmvp.location.APIException;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

class SimpleTransformer<T> implements Observable.Transformer<BaseResponse<T>, T> {
    @Override
    public  Observable<T> call(Observable<BaseResponse<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .timeout(5, TimeUnit.SECONDS)
                .retry(5)
                .flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> tBaseResponse) {
                        return flatResponse(tBaseResponse);
                    }
                });
    }

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response BaseResponse
     * @return Observable
     */
    public Observable<T> flatResponse(final BaseResponse<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response.isSuccess()) {//请求成功
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response.getData());
                    }
                } else {//请求失败
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException(response.getResultCode(), response.getMsg()));
                    }
                    return;
                }
                if (!subscriber.isUnsubscribed()) {//请求完成
                    subscriber.onCompleted();
                }
            }
        });
    }
}
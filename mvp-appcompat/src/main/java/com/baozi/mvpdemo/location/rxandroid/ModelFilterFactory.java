package com.baozi.mvpdemo.location.rxandroid;


import com.baozi.mvpdemo.location.BaseResponse;
import com.baozi.mvpdemo.location.APIException;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author jlanglang  2016/11/15 16:14
 */
public class ModelFilterFactory {
    private final static Observable.Transformer transformer = new SimpleTransformer();

    /**
     * 将Observable<BaseResponse<T>>转化Observable<T>,并处理BaseResponse
     *
     * @return Observable<T>
     */
    @SuppressWarnings("unchecked")
    public static <T> Observable<T> filter(Observable<BaseResponse<T>> observable) {
        return observable.compose(transformer);
    }

    /**
     * @param <T>
     */
    private static class SimpleTransformer<T> implements Observable.Transformer<BaseResponse<T>, T> {
        @Override
        public Observable<T> call(Observable<BaseResponse<T>> observable) {
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
                    if (!subscriber.isUnsubscribed()){
                        //请求完成
                        subscriber.onCompleted();
                    }
                }
            });
        }
    }
}

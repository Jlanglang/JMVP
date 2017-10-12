package com.linfeng.rx_retrofit_network.location.rxandroid;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class BaseTransformer<T> implements Observable.Transformer<T, T> {
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_RETRY = 5;

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .timeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY);
    }
}

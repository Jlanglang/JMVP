package com.linfeng.rx_retrofit_network.location.rxandroid;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class SimpleTransformer<T> implements ObservableTransformer<T, T> {
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_RETRY = 5;

    @Override
    public ObservableSource<T> apply(io.reactivex.Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
//                .timeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
//                .retry(DEFAULT_RETRY);
    }
}

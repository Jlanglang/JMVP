package com.linfeng.rx_retrofit_network.location.rxandroid;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class SimpleTransformer<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(io.reactivex.Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}

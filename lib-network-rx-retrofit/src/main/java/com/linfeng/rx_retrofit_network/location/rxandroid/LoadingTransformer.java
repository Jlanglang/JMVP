package com.linfeng.rx_retrofit_network.location.rxandroid;

import android.app.Dialog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author jlanglang  2017/7/2 10:39
 * @版本 2.0 rxjava添加Dialog
 * @Change
 */
public class LoadingTransformer<T> implements ObservableTransformer<T, T> {
    private Dialog mLoading;

    public LoadingTransformer(Dialog loading) {
        mLoading = loading;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mLoading.show();
            }
        }).doOnNext(new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {
                mLoading.dismiss();
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                mLoading.dismiss();
            }
        });
    }
}

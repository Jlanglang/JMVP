package com.linfeng.rx_retrofit_network.location.rxandroid;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @author jlanglang  2017/7/2 10:39
 * @版本 2.0 rxjava添加loading,实现LoadingDialog接口
 * @Change
 */
public class LoadingTransformer<T> implements Observable.Transformer<T, T> {
    private LoadingInterface mLoading;

    public LoadingTransformer(LoadingInterface loading) {
        mLoading = loading;
    }
    @Override
    public Observable<T> call(final Observable<T> o) {
        return o.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                mLoading.show();
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mLoading.dismiss();
            }
        }).doOnNext(new Action1<T>() {
            @Override
            public void call(T t) {
                mLoading.dismiss();
            }
        });
    }

    public interface LoadingInterface {

        void show();

        void dismiss();
    }
}

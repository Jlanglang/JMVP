package com.linfeng.rx_retrofit_network.location.rxandroid;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author jlanglang  2017/7/2 10:39
 * @版本 2.0 rxjava添加loading,实现LoadingDialog接口
 * @Change
 */
public class LoadingTransformer<T> implements ObservableTransformer<T, T> {
    private LoadingInterface mLoading;

    public LoadingTransformer(LoadingInterface loading) {
        mLoading = loading;
    }
//    @Override
//    public Observable<T> call(final Observable<T> o) {
//        return o.doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//                mLoading.show();
//            }
//        }).doOnError(new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                mLoading.dismiss();
//            }
//        }).doOnNext(new Action1<T>() {
//            @Override
//            public void call(T t) {
//                mLoading.dismiss();
//            }
//        });
//    }

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
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                mLoading.dismiss();
            }
        });
    }

    public interface LoadingInterface {

        void show();

        void dismiss();
    }
}

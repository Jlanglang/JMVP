package com.linfeng.rx_retrofit_network.location.rxandroid;


import com.linfeng.rx_retrofit_network.factory.NetWorkErrorFactory;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author jlanglang  2016/11/14 17:32
 * @版本 2.0
 * @Change
 */
public abstract class SimpleObserver<T> implements Observer<T> {
    protected Disposable mDisposable;
    private CompositeDisposable mCompositeDisposable;

    public SimpleObserver() {

    }

    public SimpleObserver(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
        if (!d.isDisposed() && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.add(d);
        }
    }

    @Override
    public void onNext(T t) {
        call(t);
    }

    @Override
    public void onError(Throwable e) {
        errorMessage(NetWorkErrorFactory.getError(e));
        e.printStackTrace();
        if (!mDisposable.isDisposed() && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.remove(mDisposable);
        }
    }

    @Override
    public void onComplete() {
        if (!mDisposable.isDisposed() && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.remove(mDisposable);
        }
    }

    public void errorMessage(String errorMsg) {

    }

    public abstract void call(T t);
}

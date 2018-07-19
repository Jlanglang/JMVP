package com.linfeng.rx_retrofit_network.location.rxandroid;


import android.text.TextUtils;
import android.widget.Toast;

import com.linfeng.rx_retrofit_network.NetWorkManager;
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
        this(null);
    }

    public SimpleObserver(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
        try {
            mCompositeDisposable.add(d);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void onNext(T t) {
        call(t);
    }

    @Override
    public void onError(Throwable e) {
        showErrorMsg(e, NetWorkErrorFactory.disposeError(e));
    }

//    private boolean isDisposed() {
//        return mCompositeDisposable != null && !mDisposable.isDisposed() && !mCompositeDisposable.isDisposed();
//    }

    @Override
    public void onComplete() {

    }

    /**
     * 默认提示
     *
     * @param e
     * @param errorMsg
     */
    @Deprecated
    public void showErrorMsg(Throwable e, String errorMsg) {
        if (!TextUtils.isEmpty(errorMsg)) {
            Toast.makeText(NetWorkManager.getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void call(@NonNull T t);
}

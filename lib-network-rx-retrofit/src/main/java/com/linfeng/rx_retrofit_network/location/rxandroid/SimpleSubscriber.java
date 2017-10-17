package com.linfeng.rx_retrofit_network.location.rxandroid;


import com.linfeng.rx_retrofit_network.location.APIException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author jlanglang  2016/11/14 17:32
 * @版本 2.0
 * @Change
 */
public abstract class SimpleSubscriber<T> implements Observer<T> {

    public Disposable mDisposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            call(t);
        } else {
            errorMessage("连接失败");
        }
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg;
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            errorMsg = exception.message;
        } else if (e instanceof UnknownHostException) {
            errorMsg = "请打开网络";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = "请求超时";
        } else if (e instanceof ConnectException) {
            errorMsg = "连接失败";
        } else if (e instanceof HttpException) {
            errorMsg = "请求超时";
        } else {
            errorMsg = "请求失败";
        }
        errorMessage(errorMsg);
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }

    public void errorMessage(String error) {
    }

    public abstract void call(T t);

    /**
     * 发送数,想控制发送数,可以重写该方法.
     *
     * @return 默认发送全部事件
     */
    public long request() {
        return Long.MAX_VALUE;
    }
}

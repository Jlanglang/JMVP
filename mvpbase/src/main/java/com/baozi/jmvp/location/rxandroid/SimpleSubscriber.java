package com.baozi.jmvp.location.rxandroid;


import com.baozi.jmvp.location.APIException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author jlanglang  2016/11/14 17:32
 * @版本 2.0
 * @Change
 */
public abstract class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
//            ToastUtil.showToast(exception.message);
        } else if (e instanceof UnknownHostException) {
//            ToastUtil.showToast("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
//            ToastUtil.showToast("请求超时");
        } else if (e instanceof ConnectException) {
//            ToastUtil.showToast("连接失败");
        } else if (e instanceof HttpException) {
//            ToastUtil.showToast("请求超时");
        } else {
//            ToastUtil.showToast("请求失败");
        }
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            call(t);
        } else {
//            ToastUtil.showToast("连接失败");
        }
    }

    public abstract void call(T t);
}

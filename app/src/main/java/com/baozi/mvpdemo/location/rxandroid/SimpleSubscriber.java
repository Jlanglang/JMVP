package com.baozi.mvpdemo.location.rxandroid;

import com.baozi.mvpdemo.location.APIException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author jlanglang  2016/11/14 17:32
  * Subscriber,这个是用来处理Observable的结果的.
 */
public abstract class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {//这个是请求完成时调用.如果走了onError()就不会走这个方法.

    }

    @Override
    public void onError(Throwable e) {//这里通常就处理异常
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
           // ToastUtil.showToast(exception.message);
        } else if (e instanceof UnknownHostException) {
           // ToastUtil.showToast("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
           // ToastUtil.showToast( "请求超时");
        } else if (e instanceof ConnectException) {
           // ToastUtil.showToast("连接失败");
        } else if (e instanceof HttpException) {
           // ToastUtil.showToast("请求超时");
        }else {
          //  ToastUtil.showToast("请求失败");
        }
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {//这里的是获得了数据,方法意思很明显,下一步干啥
        if (t != null) {//这里最好判断一下是否为null.
            call(t);
        } else {
           // ToastUtil.showToast("连接失败");
        }
    }
    /**
    *因为具体的处理这里无法得知,所以抽象.
    */
    public abstract void call(T t);
}
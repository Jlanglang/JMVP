package com.baozi.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baozi.mvp.view.UIView;


/**
 * @author jlanglang  2016/11/11 15:10
 * @版本 2.0
 * @Change
 */
public abstract class BasePresenter<T extends UIView> implements LifecycleObserver {


    protected T mView;

    /**
     * 绑定View
     */
    public void onAttach(T view) {
        this.mView = view;
    }

    /**
     * 解除绑定
     */
    public void onDetach() {

    }

    public T getView() {
        return mView;
    }

    /**
     * 运行在onCreate()之后,可能在onStart()之后调用.
     * 建议初始化数据,刷新的网络请求
     */
    public void onRefreshData() {
        Log.d("lifecycle", "onRefreshData");
    }

    /**
     * 在这里取消网络请求回调
     */
    public abstract void cancelNetWork();

    /**
     * 本地网络异常
     */
    public abstract void netWorkError(Throwable throwable);


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 做初始化的操作,需要在view的视图初始化完成之后才能调用
     * 建议只初始化一些对象,而不要去做耗时操作.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.d("lifecycle", "onCreate");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    public void onStart() {
        Log.d("lifecycle", "onStart");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d("lifecycle", "onResume");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d("lifecycle", "onPause");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.d("lifecycle", "onStop");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        cancelNetWork();
        Log.d("lifecycle", "onDestroy");
    }
}

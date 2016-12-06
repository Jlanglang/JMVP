package com.baozi.mvpdemo.presenter;

import android.os.Bundle;

import com.baozi.mvpdemo.ui.view.BaseView;

/**
 * @author jlanglang  2016/11/11 15:10
 */
public abstract class BasePresenter<T extends BaseView> {
    protected T mView;

    /**
     * 绑定View
     */
    public void onAttch(T view) {
        this.mView = view;

    }
    /**
     * 做初始化的操作,需要在V的视图初始化完成之后才能调用
     * presenter进行初始化.
     */
    public abstract void onCreate();
    /**
     * 在这里结束异步操作
     */
    public void onDestroy(){

    }
    /**
     * 在V销毁的时候调用,解除绑定
     */
    public void onDetach() {
        mView = null;
    }
    /**
     * 容易被回收掉时保存数据
     */
    public abstract void onSaveInstanceState(Bundle outState);
}
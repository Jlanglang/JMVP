package com.baozi.mvpdemo.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.ui.view.BaseView;

import rx.subscriptions.CompositeSubscription;

/**
 * @author jlanglang  2016/11/11 15:10
 * @版本 2.0
 * @Change
 */
public abstract class BasePresenter<T extends BaseView> {
    protected T mView;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;

    /**
     * 绑定View
     */
    public void onAttch(T view) {
        //创建管理rx请求生命周期
        this.mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    /**
     * @param contenetView
     * @param preant
     */
    public void initContentView(ViewGroup preant, View contenetView) {
        preant.addView(contenetView);
    }

    /**
     * 做初始化的操作,需要在view的视图初始化完成之后才能调用
     * 建议只初始化一些对象,而不要去做耗时操作.
     */
    public abstract void onCreate();

    /**
     * 运行在onCreate()之后,可能在onStart()之后调用.
     * 建议加载数据,处理数据刷新页面的操作放在这里
     */
    public abstract void LoadData();

    /**
     * 在这里结束异步操作
     */
    public void onDestroy() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 解除绑定
     */
    public void onDetach() {
        mView = null;
    }

    public CompositeSubscription getCompositeSubscription() {
        return mCompositeSubscription;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onRestart() {

    }

    public void onPause() {

    }

    public void onStop() {

    }
}

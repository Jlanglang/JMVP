package com.baozi.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baozi.mvp.view.UIView;


/**
 * @author jlanglang  2016/11/11 15:10
 * @版本 2.0
 * @Change
 */
public abstract class BasePresenter<T extends UIView> {


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
//        mView = null;
    }

    public T getView() {
        return mView;
    }

    public View getContentView() {
        return mView.getContentView();
    }

    /**
     * 做初始化的操作,需要在view的视图初始化完成之后才能调用
     * 建议只初始化一些对象,而不要去做耗时操作.
     */
    public abstract void onCreate();

    /**
     * 运行在onCreate()之后,可能在onStart()之后调用.
     * 建议初始化数据,刷新的网络请求
     */
    public abstract void onRefreshData();

    /**
     * 取消网络请求回调
     */
    public abstract void cancelNetWork();

    /**
     * 本地网络异常
     */
    public abstract void netWorkError(Throwable throwable);

    public void onDestroy() {
        cancelNetWork();
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

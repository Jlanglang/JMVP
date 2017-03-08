package com.baozi.mvpdemo.presenter;


import com.baozi.mvpdemo.contract.BaseWebViewContract;
import com.gzsll.jsbridge.WVJBWebView;
import com.gzsll.jsbridge.WVJBWebViewClient;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/18
 */

public abstract class BaseWebViewPresenterImpl<T extends BaseWebViewContract.View> extends BasePresenter<T>
        implements BaseWebViewContract.Presenter {
    protected T mView;
    /**
     * 绑定View
     */
    public void onAttch(T view) {
        //创建管理rx请求生命周期
        this.mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onCreate() {
        initWebView();
        RegisterHandler();
    }

    @Override
    public void initWebView() {
        WVJBWebView wvjbWebView = mView.getWVJBWebView();
        wvjbWebView.setWebViewClient(new WVJBWebViewClient(wvjbWebView));
        wvjbWebView.loadUrl(mView.getUrl());
    }

}
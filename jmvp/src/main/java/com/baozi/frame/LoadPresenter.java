package com.baozi.frame;

import android.view.View;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.ui.UIView;

/**
 * Created by baozi on 2017/3/28.
 */

public abstract class LoadPresenter<T extends UIView> extends JBasePresenter<T> {
    private BasePresenter mBasePresenter;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    public LoadPresenter(BasePresenter basePresenter) {
        mBasePresenter = basePresenter;
        mLoadingView = loadingView();
        mErrorView = errorView();
        mEmptyView = emptyView();
    }

    @Override
    public void onCreate() {
        mBasePresenter.onCreate();
    }

    @Override
    public void loadData() {
        mBasePresenter.loadData();
    }

    abstract View loadingView();

    abstract View emptyView();

    abstract View errorView();

}

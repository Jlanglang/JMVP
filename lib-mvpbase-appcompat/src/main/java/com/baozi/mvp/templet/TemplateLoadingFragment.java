package com.baozi.mvp.templet;

import android.view.View;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.templet.options.ContentOptions;
import com.baozi.mvp.templet.weight.LoadingPager;
import com.baozi.mvp.view.LoadView;

/**
 * Created by baozi on 2017/12/20.
 */

public abstract class TemplateLoadingFragment<T extends BasePresenter> extends TemplateFragment<T>
        implements LoadView {
    private LoadingPager mLoadingPager;

    @Override
    protected View wrapperContentView(View view) {
        mLoadingPager = getContentOptions().buildLoadingPager(mContext, view);
        mLoadingPager.setRefreshListener(new LoadingPager.OnRefreshListener() {
            @Override
            public void onRefresh() {
                triggerInit();
            }
        });
        return mLoadingPager;
    }

    @Override
    public void showEmpty() {
        mLoadingPager.showEmpty();
    }

    @Override
    public void showError(Throwable throwable, boolean isShowError) {
        if (isShowError) {
            mLoadingPager.showError(throwable);
        }
    }

    @Override
    public void showError(Throwable throwable) {
        showError(throwable, true);
    }

    @Override
    public void onNewThrowable(Throwable throwable) {
        super.onNewThrowable(throwable);
        showError(throwable);
    }


    @Override
    public void showLoading() {
        mLoadingPager.showLoading();
    }

    @Override
    public void showSuccess() {
        mLoadingPager.showSuccess();
    }

    @Override
    public void triggerInit() {
        showLoading();
        mPresenter.onRefreshData();
    }

    @Override
    public LoadingPager getLoadPager() {
        return mLoadingPager;
    }

}

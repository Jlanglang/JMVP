package com.baozi.mvp.tempalet.helper.load;

import android.view.View;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.tempalet.options.ContentOptions;
import com.baozi.mvp.tempalet.weight.LoadingPager;

public class LoadHelper {
    private LoadingPager mLoadingPager;

    public View wrapperLoad(View view, LoadingPager.OnRefreshListener onRefreshListener) {
        mLoadingPager = getContentOptions().buildLoadingPager(view.getContext(), view);
        mLoadingPager.setRefreshListener(onRefreshListener);
        return mLoadingPager;
    }

    public LoadingPager getLoadPager() {
        return mLoadingPager;
    }

    public void showEmpty() {
        mLoadingPager.showEmpty();
    }

    public void showError(Throwable throwable, boolean isShowError) {
        if (isShowError) {
            mLoadingPager.showError(throwable);
        }
    }

    public void showError(Throwable throwable) {
        showError(throwable, true);
    }

    public void onNewThrowable(Throwable throwable) {
        showError(throwable);
    }

    public void showLoading() {
        mLoadingPager.showLoading();
    }

    public void showSuccess() {
        mLoadingPager.showSuccess();
    }

    protected ContentOptions getContentOptions() {
        return MVPManager.getContentOptions();
    }
}

package com.baozi.mvp.tempalet.helper.load;

import android.view.View;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.tempalet.options.ContentOptions;
import com.baozi.mvp.tempalet.weight.LoadingPager;

public class LoadHelper {
    private LoadingPager mLoadingPager;

    public LoadHelper() {
    }

    public View wrapperLoad(View view, LoadingPager.OnRefreshListener onRefreshListener) {
        return wrapperLoad(view, MVPManager.getContentOptions(), onRefreshListener);
    }

    public View wrapperLoad(View view, ContentOptions options, LoadingPager.OnRefreshListener onRefreshListener) {
        if (options == null) return view;
        mLoadingPager = options.buildLoadingPager(view.getContext(), view);
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

    public void showError() {
        showError(null, true);
    }

    public void showError(Throwable throwable) {
        showError(throwable, true);
    }

    public void showLoading() {
        mLoadingPager.showLoading();
    }

    public void showSuccess() {
        mLoadingPager.showSuccess();
    }

}

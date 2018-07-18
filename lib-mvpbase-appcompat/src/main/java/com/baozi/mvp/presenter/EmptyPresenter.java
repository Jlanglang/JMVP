package com.baozi.mvp.presenter;

import android.view.View;

/**
 * Created by baozi on 2017/12/20.
 */

public final class EmptyPresenter extends BasePresenter {

    @Override
    public void onCreate() {
    }

    @Override
    public void onRefreshData() {

    }

    @Override
    public View getContentView() {
        return null;
    }

    @Override
    public void cancelNetWork() {

    }

    @Override
    public void netWorkError(Throwable throwable) {

    }
}

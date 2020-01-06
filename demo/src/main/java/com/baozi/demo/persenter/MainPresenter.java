package com.baozi.demo.persenter;

import com.baozi.demo.ui.at.MainAt;
import com.baozi.mvp.StartFactory;
import com.baozi.mvp.presenter.BasePresenter;


public class MainPresenter extends BasePresenter<MainAt> {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void cancelNetWork() {

    }

    @Override
    public void netWorkError(Throwable throwable) {

    }

    public void toDemoAt(Class activityClass) {
        StartFactory.startActivity(mView, activityClass);
    }
}

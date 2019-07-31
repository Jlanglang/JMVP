package com.baozi.demo.persenter;

import com.baozi.demo.ui.at.BaseAt;
import com.baozi.mvp.presenter.BasePresenter;

public class DemoPresenter extends BasePresenter<BaseAt> {


    @Override
    public void cancelNetWork() {

    }

    @Override
    public void netWorkError(Throwable throwable) {
        mView.getLoadHelper().showError(throwable);
    }
}

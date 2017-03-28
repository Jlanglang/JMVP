package com.baozi.frame;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.ui.BaseView;

import rx.subscriptions.CompositeSubscription;

/**
 *
 */


public abstract class JBasePresenter<T extends BaseView> extends BasePresenter<T> {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    public void cancleNetWork() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}

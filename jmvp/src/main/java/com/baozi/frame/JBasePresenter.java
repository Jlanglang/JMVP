package com.baozi.frame;

import com.baozi.jmvp.presenter.TempletPresenter;
import com.baozi.jmvp.ui.BaseView;

import rx.subscriptions.CompositeSubscription;

/**
 *
 */


public abstract class JBasePresenter<T extends BaseView> extends TempletPresenter<T> {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    public void cancleNetWork() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}

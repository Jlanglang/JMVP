package com.baozi.frame;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.ui.BaseView;

import rx.subscriptions.CompositeSubscription;

/**
 * 根据依赖库,二次封装
 */
public abstract class JBasePresenter<T extends BaseView> extends BasePresenter<T> {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    public void cancelNetWork() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}

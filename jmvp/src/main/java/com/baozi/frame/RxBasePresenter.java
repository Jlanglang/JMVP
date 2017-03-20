package com.baozi.frame;

import com.baozi.jmvp.presenter.JBasePresenter;
import com.baozi.jmvp.presenter.TempletPresenter;
import com.baozi.jmvp.ui.BaseView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by baozi on 2017/3/20.
 */

public abstract class RxBasePresenter<T extends BaseView> extends TempletPresenter<T> {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    public void cancleNetWork() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}

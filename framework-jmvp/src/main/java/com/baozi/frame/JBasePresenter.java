package com.baozi.frame;

import android.content.Intent;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.view.BaseView;
import io.reactivex.disposables.CompositeDisposable;

/**
 * 根据依赖库,二次封装
 */
public abstract class JBasePresenter<T extends BaseView> extends BasePresenter<T> {

    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    @Override
    public void cancelNetWork() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.dispose();
        }
    }
}

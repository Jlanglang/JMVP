package com.baozi.frame;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.view.UIView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 根据依赖库,二次封装
 */
public abstract class JBasePresenter<T extends UIView> extends BasePresenter<T> {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void cancelNetWork() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}

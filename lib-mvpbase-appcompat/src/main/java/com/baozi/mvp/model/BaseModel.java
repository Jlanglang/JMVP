package com.baozi.mvp.model;

import com.baozi.mvp.presenter.BasePresenter;

/**
 * Created by baozi on 2017/10/23.
 */

public class BaseModel<T extends BasePresenter> {
    protected T mPresenter;

    public BaseModel(T presenter) {
        mPresenter = presenter;
    }
}

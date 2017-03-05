package com.baozi.mvpdemo.presenter;

import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.base.TempletActivity;
import com.baozi.mvpdemo.ui.view.BaseView;

/**
 * Created by baozi on 2017/3/5.
 */

public abstract class TempletPresenter<T extends BaseView> extends BasePresenter<T> {
    /**
     * @param preant
     */
    public void wapperContentView(ViewGroup preant) {

    }
}

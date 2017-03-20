package com.baozi.jmvp.presenter;

import android.view.ViewGroup;

import com.baozi.jmvp.ui.BaseView;

/**
 * Created by baozi on 2017/3/5.
 */

public abstract class TempletPresenter<T extends BaseView> extends JBasePresenter<T> {
    /**
     * @param preant
     */
    public void wapperContentView(ViewGroup preant) {

    }

}

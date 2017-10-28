package com.baozi.mvp.templet.helper;

import android.view.View;

import com.baozi.mvp.templet.options.ToolbarOptions;
import com.baozi.mvp.view.ToolbarView;

/**
 * @author jlanglang  2017/3/7 16:29
 * @版本 2.0
 * @Change
 */
public class MDToolBarHelperImpl extends BaseToolBarHelperImpl {

    public MDToolBarHelperImpl(ToolbarView uiView, View rootView, int toolbarLayout) {
        super(uiView, rootView, toolbarLayout);
    }

    @Override
    public void initToolbar() {
        ToolbarHelper.SimpleInitToolbar(mToolbarView.getContext(), mToolbar, true);
    }

    @Override
    public void setToolbarOptions(ToolbarOptions options) {
        super.setToolbarOptions(options);

    }

    @Override
    public void setTextSize(int size) {

    }

    @Override
    public void setTitleSize(int size) {

    }
}

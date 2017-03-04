package com.baozi.mvpdemo.helper;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/3/1 16:44
 * @版本 2.0
 * @Change
 */

public class MaterialDesignToolBarHelperImplV1 extends BaseToolBarHelperImpl {

    public MaterialDesignToolBarHelperImplV1(@NonNull UIView uiView, View rootView, int toolbarLayout) {
        super(uiView, rootView, toolbarLayout);
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        super.setMaterialDesignEnabled(isMaterialDesign);
        ActionBar supportActionBar = mUIView.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayUseLogoEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowHomeEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowCustomEnabled(isMaterialDesign);
            supportActionBar.setDisplayHomeAsUpEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowTitleEnabled(isMaterialDesign);
        }
    }
}

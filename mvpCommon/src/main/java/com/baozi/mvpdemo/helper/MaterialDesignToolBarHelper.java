package com.baozi.mvpdemo.helper;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/3/1 16:44
 * @版本 2.0
 * @Change
 */

public class MaterialDesignToolBarHelper extends BaseToolBarHelperImpl {

    public MaterialDesignToolBarHelper(@NonNull UIView uiView, int toolbarLayout) {
        super(uiView, toolbarLayout);
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

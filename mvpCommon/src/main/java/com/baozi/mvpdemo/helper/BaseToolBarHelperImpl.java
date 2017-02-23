package com.baozi.mvpdemo.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/2/22 16:58
 * @版本 2.0
 * @Change
 */
 class BaseToolBarHelperImpl extends ToolbarHelper {
     int mToolbarLayout;
     Toolbar mToolbar;
     UIView mUIView;
     Context mContext;
     boolean isMaterialDesign;

    public BaseToolBarHelperImpl(@NonNull UIView uiView, int toolbarLayout) {
        this.mUIView = uiView;
        this.mContext = uiView.getContext();
        this.mToolbarLayout = toolbarLayout;
        ViewStub vs_toolbar = uiView.findView(R.id.vs_toolbar);
        if (vs_toolbar != null) {
            vs_toolbar.setLayoutResource(mToolbarLayout);
            mToolbar = (Toolbar) vs_toolbar.inflate();
        } else {
            mToolbar = uiView.findView(toolbarLayout);
        }
        uiView.setSupportActionBar(mToolbar);
        initToolbar();
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        this.isMaterialDesign = isMaterialDesign;
        ActionBar supportActionBar = mUIView.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayUseLogoEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowHomeEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowCustomEnabled(isMaterialDesign);
            supportActionBar.setDisplayHomeAsUpEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowTitleEnabled(isMaterialDesign);
        }
    }

    @Override
    public void setTitle(@NonNull String str) {

    }

    @Override
    public void setTitle(@StringRes int str) {

    }

    @Override
    public void setLeftText(@StringRes int strId, View.OnClickListener clickListener) {

    }

    @Override
    public void setLeftText(@NonNull String str, View.OnClickListener clickListener) {

    }

    @Override
    public void setLeftButton(Drawable drawable, View.OnClickListener clickListener) {

    }

    @Override
    public void setLeftButton(@DrawableRes int drawableId, View.OnClickListener clickListener) {

    }

    @Override
    public void setRightText(@NonNull String str, View.OnClickListener clickListener) {

    }

    @Override
    public void setRightText(@StringRes int strId, View.OnClickListener clickListener) {

    }

    @Override
    public void setRightButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {

    }

    @Override
    public void setRightButton(@DrawableRes int drawableId, View.OnClickListener clickListener) {

    }


}

package com.baozi.mvp.templet.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.mvp.templet.options.ToolbarOptions;
import com.baozi.mvp.view.ToolbarView;

/**
 */
class EmptyToolbarHelperImpl extends BaseToolBarHelperImpl {

    EmptyToolbarHelperImpl(@NonNull ToolbarView uiView, View rootView, int toolbarLayout) {
        super(uiView, rootView, toolbarLayout);
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void setTextSize(int size) {

    }

    @Override
    public void setTitleSize(int size) {

    }

    @Override
    public void setToolbarOptions(ToolbarOptions options) {
        super.setToolbarOptions(options);
    }
    //    @Override
//    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
//
//    }

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

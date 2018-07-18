package com.baozi.mvp.templet.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.mvp.templet.options.ToolbarOptions;

/**
 */
class EmptyToolbarHelperImpl extends ToolbarHelper {

    @Override
    public boolean setScrollFlag(int viewId, int flag) {
        return false;
    }

    @Override
    public <V extends View> V findViewFromAppBar(int viewId) {
        return null;
    }

    @Override
    public AppBarLayout getAppBarLayout() {
        return null;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void setToolbarOptions(ToolbarOptions toolbarOptions) {

    }


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

    @Override
    public void setCanBack(boolean canback) {

    }
}

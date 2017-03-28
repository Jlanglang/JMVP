package com.baozi.mvp.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.LinearLayout;

import com.baozi.mvp.ui.view.IUIView;

/**
 * @author jlanglang  2017/2/22 16:58
 * @版本 2.0
 * @Change
 */
abstract class BaseToolBarHelperImpl extends ToolbarHelper {
    int toolbarLayout;
    LinearLayout mToolbar;
    IUIView mUIView;
    View mRootView;
    boolean isMaterialDesign;

    public BaseToolBarHelperImpl(@NonNull IUIView uiView, View rootView, int toolbarLayout) {
        this.mUIView = uiView;
        this.mRootView = rootView;
        this.toolbarLayout = toolbarLayout;
        initToolbar();
    }

    public abstract void initToolbar();

    @Override
    public LinearLayout getToolbar() {
        return mToolbar;
    }

    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        this.isMaterialDesign = isMaterialDesign;
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
}

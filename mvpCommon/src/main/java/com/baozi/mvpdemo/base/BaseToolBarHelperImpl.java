package com.baozi.mvpdemo.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.baozi.mvpdemo.R;

/**
 * @author jlanglang  2017/2/22 16:58
 * @版本 2.0
 * @Change
 */
public class BaseToolBarHelperImpl extends ToolbarHelper {
    protected int mToolbarLayout;
    protected Toolbar mToolbar;
    protected Context mContext;
    protected boolean isMaterialDesign;

    public BaseToolBarHelperImpl(Context context, int toolbarLayout) {
        this.mContext = context;
        this.mToolbarLayout = toolbarLayout;
        View decorView = ((Activity) mContext).getWindow().getDecorView();
        ViewStub vs_toolbar = (ViewStub) decorView.findViewById(R.id.vs_toolbar);
        vs_toolbar.setLayoutResource(mToolbarLayout);
        mToolbar = (Toolbar) vs_toolbar.inflate();
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
    }

    @Override
    public void setTitle(@Nullable String str) {

    }

    @Override
    public void setTitle(@StringRes int str) {

    }

    @Override
    public void setLeft(@StringRes int strId) {

    }

    @Override
    public void setLeft(@Nullable String str) {

    }

    @Override
    public void setLeft(Drawable drawable, View.OnClickListener clickListener) {

    }

    @Override
    public void setLeft(@DrawableRes int drawableId, View.OnClickListener clickListener) {

    }

    @Override
    public void setRight(@Nullable String str) {

    }

    @Override
    public void setRight(@StringRes int strId) {

    }

    @Override
    public void setRight(@Nullable Drawable drawable, View.OnClickListener clickListener) {

    }

    @Override
    public void setRight(@DrawableRes int drawableId, View.OnClickListener clickListener) {

    }
}

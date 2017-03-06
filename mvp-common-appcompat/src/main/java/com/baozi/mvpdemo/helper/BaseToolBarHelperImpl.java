package com.baozi.mvpdemo.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/2/22 16:58
 * @版本 2.0
 * @Change
 */
abstract class BaseToolBarHelperImpl extends ToolbarHelper {
    int mToolbarLayout;
    Toolbar mToolbar;
    UIView mUIView;
    View mRootView;
    SparseArray<View> mViews;
    AppBarLayout mAppBarLayout;
    boolean isMaterialDesign;

    public BaseToolBarHelperImpl(@NonNull UIView uiView, View rootView, int toolbarLayout) {
        mUIView = uiView;
        mRootView = rootView;
        mToolbarLayout = toolbarLayout;
        mViews = new SparseArray<>();
        initToolbar();
    }

    public abstract void initToolbar();

    @Override
    public <V extends View> V appBarFindView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = getAppBarLayout().findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    @Override
    public boolean setScrollFlag(@IdRes int viewId, @AppBarLayout.LayoutParams.ScrollFlags int flag) {
        View view = appBarFindView(viewId);
        if (view != null) {
            try {
                AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) view.getLayoutParams();
                layoutParams.setScrollFlags(flag);
            } catch (ClassCastException e) {
                e.printStackTrace();
                return false;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Toolbar getToolbar() {
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
    @Override
    public View getAppBarLayout() {
        return mAppBarLayout;
    }
}

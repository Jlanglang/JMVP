package com.baozi.mvp.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.R;
import com.baozi.mvp.ui.UIView;

/**
 * @author jlanglang  2017/2/22 16:58
 */
public abstract class BaseToolBarHelperImpl extends ToolbarHelper {
    Toolbar mToolbar;
    UIView mUIView;
    //    private View mRootView;
    private AppBarLayout mAppBarLayout;
    private SparseArray<View> mViews;

    public BaseToolBarHelperImpl(@NonNull UIView uiView, View rootView, int toolbarLayout) {
        mUIView = uiView;
//        mRootView = rootView;
//        mToolbarLayout = toolbarLayout;
        mViews = new SparseArray<>();
        //初始化AppBarLayout
        mAppBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        mAppBarLayout.removeAllViews();
        //将toolbarLayout添加到AppBarLayout中
        View inflate = LayoutInflater.from(mUIView.getContext()).inflate(toolbarLayout, mAppBarLayout, true);
        //如果find不为null,则设置toolbar
        mToolbar = (Toolbar) inflate.findViewById(R.id.tl_costom);
        if (mToolbar != null) {
            initToolbar();
        } else {
            //说明为自定义toolbar
        }
    }


    public abstract void initToolbar();

    /**
     * 从AppBarLayout中获取控件
     *
     * @param viewId 控件Id
     * @param <V>    返回泛型,减少强转操作
     * @return 可能为null
     */
    @Nullable
    @Override
    public <V extends View> V findViewFromAppBar(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null && mAppBarLayout != null) {
            view = mAppBarLayout.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    /**
     * 设置控件滑动效果
     *
     * @param viewId view id
     * @param flag   设置的滑动flag
     * @return 设置成功返回true, 设置失败返回false
     */
    @Override
    public boolean setScrollFlag(@IdRes int viewId, @AppBarLayout.LayoutParams.ScrollFlags int flag) {
        View view = findViewFromAppBar(viewId);
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
        return true;
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {

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
    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }
}

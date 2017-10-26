package com.baozi.mvp.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.R;
import com.baozi.mvp.view.ToolbarView;

/**
 * @author jlanglang  2017/2/22 16:58
 */
public abstract class BaseToolBarHelperImpl extends ToolbarHelper {
    Toolbar mToolbar;
    ToolbarView mToolbarView;
    Context mContext;
    private AppBarLayout mAppBarLayout;
    private SparseArray<View> mViews;

    public BaseToolBarHelperImpl(@NonNull ToolbarView toolbarView, View rootView, int toolbarLayout) {
        mToolbarView = toolbarView;
        mContext = toolbarView.getContext();
        mViews = new SparseArray<>();

        //初始化AppBarLayout
        mAppBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        mAppBarLayout.removeAllViews();
        //将toolbarLayout添加到AppBarLayout中
        View inflate = LayoutInflater.from(toolbarView.getContext()).inflate(toolbarLayout, mAppBarLayout, true);
        //如果find不为null,则设置toolbar
        mToolbar = (Toolbar) inflate.findViewById(R.id.tl_custom);
        if (mToolbar != null) {
            initToolbar();
            ToolbarOptions mToolbarOptions = toolbarView.getToolbarOptions();
            if (mToolbarOptions == null) {
                mToolbarOptions = MVPManager.getToolbarOptions();
            }
            setToolbarOptions(mToolbarOptions);
        }
    }

    public void setToolbarOptions(ToolbarOptions options) {
        if (options == null || mToolbar == null) {
            return;
        }
        int toolbarColor = options.getToolbarColor();
        int toolbarDrawable = options.getToolbarDrawable();
        int toolbarHeight = options.getToolbarHeight();
        if (toolbarColor != 0) {
            mToolbar.setBackgroundColor(toolbarColor);
        }
        if (options.getToolbarDrawable() != 0) {
            mToolbar.setBackgroundResource(toolbarDrawable);
        }
        if (toolbarHeight > 0) {
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, toolbarHeight,
                    mContext.getResources().getDisplayMetrics());
            mToolbar.getLayoutParams().height = Math.round(px);
            mToolbar.requestLayout();
        }
    }

    @Override
    public void setCanBack(boolean canback) {

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

    public abstract void setTextSize(int size);

    public abstract void setTitleSize(int size);

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

    @Override
    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }
}

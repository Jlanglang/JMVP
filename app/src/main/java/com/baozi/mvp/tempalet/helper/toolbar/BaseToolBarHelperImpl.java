package com.baozi.mvp.tempalet.helper.toolbar;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.R;
import com.baozi.mvp.tempalet.options.ToolbarOptions;
import com.baozi.mvp.view.ToolbarView;

/**
 * @author jlanglang  2017/2/22 16:58
 */
public class BaseToolBarHelperImpl extends ToolbarHelper {
    protected Toolbar mToolbar;
    protected ToolbarView mToolbarView;
    private Context mContext;
    private AppBarLayout mAppBarLayout;
    private SparseArray<View> mViews;

    public BaseToolBarHelperImpl(@NonNull ToolbarView toolbarView) {
        mToolbarView = toolbarView;
        mContext = toolbarView.getContext();
        mViews = new SparseArray<>();

        //初始化AppBarLayout
        mAppBarLayout = toolbarView.getContentView().findViewById(R.id.app_bar);
        if (mAppBarLayout != null) {
            mAppBarLayout.removeAllViews();
        }
        //将toolbarLayout添加到AppBarLayout中
        View inflate = LayoutInflater.from(mContext).inflate(toolbarView.getToolbarLayout(), mAppBarLayout, true);
        //如果find不为null,则设置toolbar
        mToolbar = (Toolbar) inflate.findViewById(R.id.tl_custom);
        if (mToolbar != null) {
            initToolbar();
            ToolbarOptions mToolbarOptions = mToolbarView.getToolbarOptions();
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAppBarLayout.setElevation(options.getElevation());
            mAppBarLayout.setTranslationZ(options.getElevation());
            mAppBarLayout.invalidate();
        }
        if (toolbarHeight > 0) {
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, toolbarHeight,
                    mContext.getResources().getDisplayMetrics());
            mToolbar.getLayoutParams().height = Math.round(px);
            mToolbar.requestLayout();
        }
    }

    public void initToolbar() {

    }

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
    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public <T extends View> T findView(int id) {
        return mToolbar.findViewById(id);
    }


}

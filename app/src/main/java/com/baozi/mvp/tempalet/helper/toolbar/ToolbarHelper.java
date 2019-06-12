package com.baozi.mvp.tempalet.helper.toolbar;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.mvp.R;
import com.baozi.mvp.tempalet.options.ToolbarOptions;
import com.baozi.mvp.view.ToolbarView;

/**
 * @author jlanglang  2017/2/21 16:31
 * @版本 2.0
 * @Change
 */
public abstract class ToolbarHelper {
    public static final int TOOLBAR_TEMPLATE_DEFAULT = R.layout.toolbar_template_default;

    public ToolbarHelper() {

    }

    public static ToolbarHelper Create(@NonNull ToolbarView toolbarView) {
        int toolbarLayout = toolbarView.getToolbarLayout();
        if (toolbarLayout <= 0) {
            return new EmptyToolbarHelperImpl();
        } else if (toolbarLayout == TOOLBAR_TEMPLATE_DEFAULT) {
            return new SimpleToolbarHelperImpl(toolbarView);
        } else {
            return new BaseToolBarHelperImpl(toolbarView);
        }
    }

    /**
     * 快速设置Toolbar,取消边距,隐藏所有默认的显示.
     * setDisplayShowCustomEnabled(),setDisplayHomeAsUpEnabled()
     * setDisplayShowTitleEnabled(),setDisplayShowHomeEnable()
     * 都将设置为false
     *
     * @param context 继承Appcompat的activity的上下文
     * @param toolbar 将要设置的Toolbar
     */

    public static void SimpleInitToolbar(Context context, @NonNull Toolbar toolbar, boolean isMaterialDesign) {
        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            toolbar.setContentInsetsAbsolute(0, 0);
            activity.setSupportActionBar(toolbar);
            ActionBar supportActionBar = activity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayShowCustomEnabled(isMaterialDesign);
                supportActionBar.setDisplayHomeAsUpEnabled(isMaterialDesign);
                supportActionBar.setDisplayShowTitleEnabled(isMaterialDesign);
                supportActionBar.setDisplayShowHomeEnabled(isMaterialDesign);
            }
        }
    }


    /**
     * 获取AppBarLayout
     *
     * @return
     */
    public AppBarLayout getAppBarLayout() {
        return null;
    }

    /**
     * 获取Toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return null;
    }

    /**
     * 设置滑动Flag
     *
     * @param viewId
     * @param flag
     * @return
     */
    public abstract boolean setScrollFlag(@IdRes int viewId, @AppBarLayout.LayoutParams.ScrollFlags int flag);

    /**
     * 获取AppBarLayout中的View
     *
     * @param viewId
     * @param <V>
     * @return
     */
    public abstract <V extends View> V findViewFromAppBar(@IdRes int viewId);

    /**
     * 获取Toolbar配置
     *
     * @return
     */
    public abstract void setToolbarOptions(ToolbarOptions toolbarOptions);

    /**
     * 设置title
     *
     * @param str
     */
    public abstract void setTitle(@NonNull String str);

    public abstract void setTitle(@StringRes int str);

    public abstract void setCanBack(boolean canBack);

    public abstract void setLeading(String leading);

    public abstract void setLeading(@DrawableRes int leadRes);

    public abstract void addActions(View view);

}

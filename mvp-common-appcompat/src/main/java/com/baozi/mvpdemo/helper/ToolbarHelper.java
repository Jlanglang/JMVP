package com.baozi.mvpdemo.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/2/21 16:31
 * @版本 2.0
 * @Change
 */
public abstract class ToolbarHelper {
    public static final int TOOLBAR_TEMPLET_DEFUATL = R.layout.toolbar_templet_defuatl;
    public static final int TOOLBAR_MD_COLLAPSING = R.layout.toolbar_md_collapsing;
    public static final int TOOLBAR_MD_DEFUATL = R.layout.toolbar_md_defuatl;
    public static final int TOOLBAR_MD_TABLAYOUT = R.layout.toolbar_md_tablayout;

    public ToolbarHelper() {

    }

    public static ToolbarHelper Create(@NonNull UIView uiView, View rootView, @LayoutRes int toolbarLayout) {
        if (toolbarLayout == TOOLBAR_TEMPLET_DEFUATL) {
            return new TempletDefuatlToolbarHelperImpl(uiView, rootView, toolbarLayout);
        } else if (toolbarLayout == TOOLBAR_MD_DEFUATL) {
            return new MDDefuatlToolBarHelperImpl(uiView, rootView, toolbarLayout);
        } else if (toolbarLayout == TOOLBAR_MD_COLLAPSING) {
            return new MDCollapsingToolBarHelperImpl(uiView, rootView, toolbarLayout);
        } else if (toolbarLayout == TOOLBAR_MD_TABLAYOUT) {
            return new MDTablayoutToolBarHelperImpl(uiView, rootView, toolbarLayout);
        } else if (toolbarLayout <= 0) {
            return new EmptyToolbarHelperImpl(uiView, rootView, toolbarLayout);
        } else {
            return new EmptyToolbarHelperImpl(uiView, rootView, toolbarLayout);
        }
    }

    public abstract boolean setScrollFlag(@IdRes int viewId, @AppBarLayout.LayoutParams.ScrollFlags int flag);

    public abstract Toolbar getToolbar();

    public abstract void setMaterialDesignEnabled(boolean isMaterialDesign);

    /**
     * 设置title
     *
     * @param str
     */

    public abstract void setTitle(@NonNull String str);

    public abstract void setTitle(@StringRes int str);

    /**
     * 设置左边
     *
     * @param strId
     */
    public abstract void setLeftText(@StringRes int strId, View.OnClickListener clickListener);

    public abstract void setLeftText(@NonNull String str, View.OnClickListener clickListener);

    public abstract void setLeftButton(Drawable drawable, View.OnClickListener clickListener);

    public abstract void setLeftButton(@DrawableRes int drawableId, View.OnClickListener clickListener);


    /**
     * 设置右边
     *
     * @param str
     */
    public abstract void setRightText(@NonNull String str, View.OnClickListener clickListener);

    public abstract void setRightText(@StringRes int strId, View.OnClickListener clickListener);

    public abstract void setRightButton(@NonNull Drawable drawable, View.OnClickListener clickListener);

    public abstract void setRightButton(@DrawableRes int drawableId, View.OnClickListener clickListener);

    public abstract <V extends View> V findAppBarView(@IdRes int viewId);

    public abstract View getAppBarLayout();
}

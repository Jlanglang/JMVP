package com.baozi.mvpdemo.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.mvpdemo.R;

/**
 * @author jlanglang  2017/2/21 16:31
 * @版本 2.0
 * @Change
 */
public abstract class ToolbarHelper {
    public static final int DEFUATL_BASE_TOOLBAR_V1 = R.layout.base_toolbar;

    public ToolbarHelper() {

    }

    public static ToolbarHelper Create(Context context, @LayoutRes int toolbarLayout) {
        return new DefuatlToolbarHelperImplV1(context, toolbarLayout);
    }

    public static ToolbarHelper Create(Activity activity, @LayoutRes int toolbarLayout) {
        if (toolbarLayout == DEFUATL_BASE_TOOLBAR_V1) {
            return new DefuatlToolbarHelperImplV1(activity, toolbarLayout);
        } else {
            return new BaseToolBarHelperImpl(activity, toolbarLayout);
        }
    }

    public static ToolbarHelper Create(Dialog dialog, @LayoutRes int toolbarLayout) {
        return new DefuatlToolbarHelperImplV1(dialog.getContext(), toolbarLayout);
    }

    public abstract void initToolbar();

    public abstract Toolbar getToolbar();

    public abstract void setMaterialDesignEnabled(boolean isMaterialDesign);

    /**
     * 设置title
     *
     * @param str
     */

    public abstract void setTitle(@Nullable String str);

    public abstract void setTitle(@StringRes int str);

    /**
     * 设置左边
     *
     * @param strId
     */
    public abstract void setLeft(@StringRes int strId);

    public abstract void setLeft(@Nullable String str);

    public abstract void setLeft(Drawable drawable, View.OnClickListener clickListener);

    public abstract void setLeft(@DrawableRes int drawableId, View.OnClickListener clickListener);


    /**
     * 设置右边
     *
     * @param str
     */
    public abstract void setRight(@Nullable String str);

    public abstract void setRight(@StringRes int strId);

    public abstract void setRight(@Nullable Drawable drawable, View.OnClickListener clickListener);

    public abstract void setRight(@DrawableRes int drawableId, View.OnClickListener clickListener);
}

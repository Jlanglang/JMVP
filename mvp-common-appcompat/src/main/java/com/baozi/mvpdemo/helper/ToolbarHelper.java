package com.baozi.mvpdemo.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
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
    public static final int TOOLBAR_DEFUATL_V1 = R.layout.toolbar_defuatl_v1;
    public static final int TOOLBAR_MATERIAL_DESIGN_V1 = R.layout.toolbar_material_design_v1;

    public ToolbarHelper() {

    }

    public static ToolbarHelper Create(@NonNull UIView uiView, View rootView, @LayoutRes int toolbarLayout) {
        if (toolbarLayout == TOOLBAR_DEFUATL_V1) {
            return new DefuatlToolbarHelperImplV1(uiView, rootView, toolbarLayout);
        } else if (toolbarLayout == TOOLBAR_MATERIAL_DESIGN_V1) {
            return new MaterialDesignToolBarHelperImplV1(uiView, rootView, toolbarLayout);
        } else if (toolbarLayout <= 0) {
            return new EmptyToolbarHelperImpl(uiView, rootView, toolbarLayout);
        } else {
            return new EmptyToolbarHelperImpl(uiView, rootView, toolbarLayout);
        }
    }


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


}

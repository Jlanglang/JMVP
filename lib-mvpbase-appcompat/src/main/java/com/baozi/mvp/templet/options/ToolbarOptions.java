package com.baozi.mvp.templet.options;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;

import com.baozi.mvp.templet.helper.ToolbarHelper;

/**
 * Created by baozi on 2017/10/25.
 */

public class ToolbarOptions implements Cloneable {
    //其他字体大小,颜色
    private int otherTextSize;
    @ColorInt
    private int otherTextColor;
    //标题字体大小,颜色
    private int titleSize;
    @ColorInt
    private int titleColor;
    //toolbar配置
    private int toolbarHeight;
    @ColorInt
    private int toolbarColor;
    @LayoutRes
    private int toolbarLayout = ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL;
    @DrawableRes
    private int toolbarDrawable;
    @DrawableRes
    private int statusDrawable;
    //返回图标
    @DrawableRes
    private int backDrawable;

    private boolean noBack;
    private float elevation;

    public static ToolbarOptions Create() {
        return new ToolbarOptions();
    }

    private ToolbarOptions() {

    }

    @DrawableRes
    public int getStatusDrawable() {
        return statusDrawable;
    }

    public ToolbarOptions setStatusDrawable(@DrawableRes int statusDrawable) {
        this.statusDrawable = statusDrawable;
        return this;
    }

    public int getOtherTextSize() {
        return otherTextSize;
    }

    public ToolbarOptions setOtherTextSize(int otherTextSize) {
        this.otherTextSize = otherTextSize;
        return this;
    }

    @ColorInt
    public int getOtherTextColor() {
        return otherTextColor;
    }

    public ToolbarOptions setOtherTextColor(@ColorInt int otherTextColor) {
        this.otherTextColor = otherTextColor;
        return this;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public ToolbarOptions setTitleSize(int titleSize) {
        this.titleSize = titleSize;
        return this;
    }

    @ColorInt
    public int getTitleColor() {
        return titleColor;
    }

    public ToolbarOptions setTitleColor(@ColorInt int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public int getToolbarHeight() {
        return toolbarHeight;
    }

    public ToolbarOptions setToolbarHeight(int toolbarHeight) {
        this.toolbarHeight = toolbarHeight;
        return this;
    }

    @ColorInt
    public int getToolbarColor() {
        return toolbarColor;
    }

    public ToolbarOptions setToolbarColor(@ColorInt int toolbarColor) {
        this.toolbarColor = toolbarColor;
        return this;
    }

    @DrawableRes
    public int getToolbarDrawable() {
        return toolbarDrawable;
    }

    public ToolbarOptions setToolbarDrawable(@DrawableRes int toolbarDrawable) {
        this.toolbarDrawable = toolbarDrawable;
        return this;
    }

    @DrawableRes
    public int getBackDrawable() {
        return backDrawable;
    }

    public ToolbarOptions setBackDrawable(@DrawableRes int backDrawable) {
        this.backDrawable = backDrawable;
        return this;
    }

    public boolean isNoBack() {
        return noBack;
    }

    public ToolbarOptions setNoBack(boolean noBack) {
        this.noBack = noBack;
        return this;
    }

    public int getToolbarLayout() {
        return toolbarLayout;
    }

    public ToolbarOptions setToolbarLayout(@LayoutRes int toolbarLayout) {
        this.toolbarLayout = toolbarLayout;
        return this;
    }

    public ToolbarOptions clone() {
        try {
            return (ToolbarOptions) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ToolbarOptions setElevation(float elevation) {
        this.elevation = elevation;
        return this;
    }

    public float getElevation() {
        return elevation;
    }
}

package com.baozi.mvp.helper;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * Created by baozi on 2017/10/25.
 */

public class ToolbarOptions {
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
    //    private int toolbarLayout;
    @DrawableRes
    private int toolbarDrawable;
    @DrawableRes
    private int statusDrawable;
    //返回图标
    @DrawableRes
    private int backDrawable;

    private boolean noBack;

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

    public ToolbarOptions setBackDrawable(@DrawableRes @ColorInt int backDrawable) {
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

    public ToolbarOptions clone() {
        try {
            return (ToolbarOptions) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return ToolbarOptions.Create()
                .setBackDrawable(backDrawable)
                .setOtherTextColor(otherTextColor)
                .setOtherTextSize(otherTextSize)
                .setTitleColor(titleColor)
                .setTitleSize(titleSize)
                .setToolbarColor(toolbarColor)
                .setToolbarHeight(toolbarHeight)
                .setToolbarDrawable(toolbarDrawable)
                .setNoBack(noBack);
//                .setToolbarLayout(toolbarLayout)
    }

}

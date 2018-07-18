package com.linfeng.mvp.templet.options

import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import com.linfeng.mvp.templet.helper.ToolbarHelper


/**
 * Created by baozi on 2017/10/25.
 */

class ToolbarOptions private constructor() : Cloneable {
    //其他字体大小,颜色
    private var otherTextSize: Int = 0
    @ColorInt
    private var otherTextColor: Int = 0
    //标题字体大小,颜色
    private var titleSize: Int = 0
    @ColorInt
    private var titleColor: Int = 0
    //toolbar配置
    private var toolbarHeight: Int = 0
    @ColorInt
    private var toolbarColor: Int = 0
    @LayoutRes
    private var toolbarLayout = ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL
    @DrawableRes
    private var toolbarDrawable: Int = 0
    @DrawableRes
    private var statusDrawable: Int = 0
    //返回图标
    @DrawableRes
    private var backDrawable: Int = 0

    private var noBack: Boolean = false
    private var elevation: Float = 0.toFloat()

    @DrawableRes
    fun getStatusDrawable(): Int {
        return statusDrawable
    }

    fun setStatusDrawable(@DrawableRes statusDrawable: Int): ToolbarOptions {
        this.statusDrawable = statusDrawable
        return this
    }

    fun getOtherTextSize(): Int {
        return otherTextSize
    }

    fun setOtherTextSize(otherTextSize: Int): ToolbarOptions {
        this.otherTextSize = otherTextSize
        return this
    }

    @ColorInt
    fun getOtherTextColor(): Int {
        return otherTextColor
    }

    fun setOtherTextColor(@ColorInt otherTextColor: Int): ToolbarOptions {
        this.otherTextColor = otherTextColor
        return this
    }

    fun getTitleSize(): Int {
        return titleSize
    }

    fun setTitleSize(titleSize: Int): ToolbarOptions {
        this.titleSize = titleSize
        return this
    }

    @ColorInt
    fun getTitleColor(): Int {
        return titleColor
    }

    fun setTitleColor(@ColorInt titleColor: Int): ToolbarOptions {
        this.titleColor = titleColor
        return this
    }

    fun getToolbarHeight(): Int {
        return toolbarHeight
    }

    fun setToolbarHeight(toolbarHeight: Int): ToolbarOptions {
        this.toolbarHeight = toolbarHeight
        return this
    }

    @ColorInt
    fun getToolbarColor(): Int {
        return toolbarColor
    }

    fun setToolbarColor(@ColorInt toolbarColor: Int): ToolbarOptions {
        this.toolbarColor = toolbarColor
        return this
    }

    @DrawableRes
    fun getToolbarDrawable(): Int {
        return toolbarDrawable
    }

    fun setToolbarDrawable(@DrawableRes toolbarDrawable: Int): ToolbarOptions {
        this.toolbarDrawable = toolbarDrawable
        return this
    }

    @DrawableRes
    fun getBackDrawable(): Int {
        return backDrawable
    }

    fun setBackDrawable(@DrawableRes backDrawable: Int): ToolbarOptions {
        this.backDrawable = backDrawable
        return this
    }

    fun isNoBack(): Boolean {
        return noBack
    }

    fun setNoBack(noBack: Boolean): ToolbarOptions {
        this.noBack = noBack
        return this
    }

    fun getToolbarLayout(): Int {
        return toolbarLayout
    }

    fun setToolbarLayout(@LayoutRes toolbarLayout: Int): ToolbarOptions {
        this.toolbarLayout = toolbarLayout
        return this
    }

    public override fun clone(): ToolbarOptions {
        try {
            return super.clone() as ToolbarOptions
        } catch (e: CloneNotSupportedException) {
            throw RuntimeException(e)
        }

    }

    fun setElevation(elevation: Float): ToolbarOptions {
        this.elevation = elevation
        return this
    }

    fun getElevation(): Float {
        return elevation
    }

    companion object {

        fun Create(): ToolbarOptions {
            return ToolbarOptions()
        }
    }
}

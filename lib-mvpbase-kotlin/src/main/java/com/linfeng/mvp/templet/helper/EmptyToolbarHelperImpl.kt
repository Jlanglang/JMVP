package com.linfeng.mvp.templet.helper

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.Toolbar
import android.view.View

import com.linfeng.mvp.templet.options.ToolbarOptions

/**
 */
internal class EmptyToolbarHelperImpl : ToolbarHelper() {

    override val appBarLayout: AppBarLayout?
        get() = null

    override val toolbar: Toolbar?
        get() = null

    override fun setScrollFlag(viewId: Int, flag: Int): Boolean {
        return false
    }

    override fun <V : View> findViewFromAppBar(viewId: Int): V? {
        return null
    }

    override fun setToolbarOptions(toolbarOptions: ToolbarOptions) {

    }


    override fun setTitle(str: String) {

    }

    override fun setTitle(@StringRes str: Int) {

    }

    override fun setLeftText(@StringRes strId: Int, clickListener: View.OnClickListener) {

    }

    override fun setLeftText(str: String, clickListener: View.OnClickListener) {

    }

    override fun setLeftButton(drawable: Drawable, clickListener: View.OnClickListener) {

    }

    override fun setLeftButton(@DrawableRes drawableId: Int, clickListener: View.OnClickListener) {

    }

    override fun setRightText(str: String, clickListener: View.OnClickListener) {

    }

    override fun setRightText(@StringRes strId: Int, clickListener: View.OnClickListener) {

    }

    override fun setRightButton(drawable: Drawable, clickListener: View.OnClickListener) {

    }

    override fun setRightButton(@DrawableRes drawableId: Int, clickListener: View.OnClickListener) {

    }

    override fun setCanBack(canback: Boolean) {

    }
}

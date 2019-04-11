package com.linfeng.mvp.templet.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.linfeng.mvp.R
import com.linfeng.mvp.templet.options.ToolbarOptions
import com.linfeng.mvp.view.ToolbarView

/**
 * @author jlanglang  2017/2/21 16:31
 * @版本 2.0
 * @Change
 */
abstract class ToolbarHelper {

    /**
     * 获取AppBarLayout
     *
     * @return
     */
    abstract val appBarLayout: AppBarLayout?

    /**
     * 获取Toolbar
     *
     * @return
     */
    abstract val toolbar: Toolbar?

    /**
     * 设置滑动Flag
     *
     * @param viewId
     * @param flag
     * @return
     */
    abstract fun setScrollFlag(@IdRes viewId: Int, @AppBarLayout.LayoutParams.ScrollFlags flag: Int): Boolean

    /**
     * 获取AppBarLayout中的View
     *
     * @param viewId
     * @param <V>
     * @return
    </V> */
    abstract fun <V : View> findViewFromAppBar(@IdRes viewId: Int): V?

    /**
     * 获取Toolbar配置
     *
     * @return
     */
    abstract fun setToolbarOptions(toolbarOptions: ToolbarOptions)

    //    public abstract void setMaterialDesignEnabled(boolean isMaterialDesign);

    /**
     * 设置title
     *
     * @param str
     */

    abstract fun setTitle(str: String)

    abstract fun setTitle(@StringRes str: Int)

    /**
     * 设置左边
     *
     * @param strId
     */
    abstract fun setLeftText(@StringRes strId: Int, clickListener: View.OnClickListener)

    abstract fun setLeftText(str: String, clickListener: View.OnClickListener)

    abstract fun setLeftButton(drawable: Drawable, clickListener: View.OnClickListener)

    abstract fun setLeftButton(@DrawableRes drawableId: Int, clickListener: View.OnClickListener)


    /**
     * 设置右边
     *
     * @param str
     */
    abstract fun setRightText(str: String, clickListener: View.OnClickListener)

    abstract fun setRightText(@StringRes strId: Int, clickListener: View.OnClickListener)

    abstract fun setRightButton(drawable: Drawable, clickListener: View.OnClickListener)

    abstract fun setRightButton(@DrawableRes drawableId: Int, clickListener: View.OnClickListener)


    abstract fun setCanBack(canback: Boolean)

    companion object {
        val TOOLBAR_TEMPLET_DEFUATL = R.layout.toolbar_template_default
        val TOOLBAR_MD_DEFUATL = R.layout.toolbar_md_defuatl
        val TOOLBAR_MD_TABLAYOUT = R.layout.toolbar_md_tablayout

        fun create(uiView: ToolbarView, rootView: View): ToolbarHelper {
            val toolbarLayout = uiView.toolbarLayout
            return if (toolbarLayout == TOOLBAR_TEMPLET_DEFUATL) {
                ToolbarHelperImpl(uiView, rootView, toolbarLayout)
            } else if (toolbarLayout == TOOLBAR_MD_DEFUATL || toolbarLayout == TOOLBAR_MD_TABLAYOUT) {
                MDToolBarHelperImpl(uiView, rootView, toolbarLayout)
            } else {
                EmptyToolbarHelperImpl()
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

        fun simpleInitToolbar(context: Context, toolbar: Toolbar?, isMaterialDesign: Boolean) {
            if (context is AppCompatActivity) {
                toolbar?.setContentInsetsAbsolute(0, 0)
                context.setSupportActionBar(toolbar)
                val supportActionBar = context.supportActionBar ?: return
                supportActionBar.setDisplayShowCustomEnabled(isMaterialDesign)
                supportActionBar.setDisplayHomeAsUpEnabled(isMaterialDesign)
                supportActionBar.setDisplayShowTitleEnabled(isMaterialDesign)
                supportActionBar.setDisplayShowHomeEnabled(isMaterialDesign)
            }
        }
    }

}

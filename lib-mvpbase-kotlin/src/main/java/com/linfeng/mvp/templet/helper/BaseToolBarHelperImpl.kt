package com.linfeng.mvp.templet.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.Toolbar
import android.util.SparseArray
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View

import com.baozi.mvp.MVPManager
import com.baozi.mvp.R
import com.baozi.mvp.templet.options.ToolbarOptions
import com.baozi.mvp.view.ToolbarView

/**
 * @author jlanglang  2017/2/22 16:58
 */
abstract class BaseToolBarHelperImpl(internal var mToolbarView: ToolbarView, rootView: View, toolbarLayout: Int) : ToolbarHelper() {
    override var toolbar: Toolbar? = null
        internal set
    internal var mContext: Context
    override val appBarLayout: AppBarLayout?
    private val mViews: SparseArray<View>

    init {
        mContext = mToolbarView.getContext()
        mViews = SparseArray()

        //初始化AppBarLayout
        appBarLayout = rootView.findViewById(R.id.app_bar) as AppBarLayout
        appBarLayout!!.removeAllViews()

        //将toolbarLayout添加到AppBarLayout中
        val inflate = LayoutInflater.from(mContext).inflate(toolbarLayout, appBarLayout, true)
        //如果find不为null,则设置toolbar
        toolbar = inflate.findViewById(R.id.tl_custom) as Toolbar
        if (toolbar != null) {
            initToolbar()
            var mToolbarOptions = mToolbarView.getToolbarOptions()
            if (mToolbarOptions == null) {
                mToolbarOptions = MVPManager.getToolbarOptions()
            }
            setToolbarOptions(mToolbarOptions)
        }
    }

    override fun setToolbarOptions(options: ToolbarOptions?) {
        if (options == null || toolbar == null) {
            return
        }
        val toolbarColor = options!!.getToolbarColor()
        val toolbarDrawable = options!!.getToolbarDrawable()
        val toolbarHeight = options!!.getToolbarHeight()
        if (toolbarColor != 0) {
            toolbar!!.setBackgroundColor(toolbarColor)
        }
        if (options!!.getToolbarDrawable() !== 0) {
            toolbar!!.setBackgroundResource(toolbarDrawable)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout!!.elevation = options!!.getElevation()
            appBarLayout!!.translationZ = options!!.getElevation()
            appBarLayout!!.invalidate()
        }
        if (toolbarHeight > 0) {
            val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, toolbarHeight.toFloat(),
                    mContext.resources.displayMetrics)
            toolbar!!.layoutParams.height = Math.round(px)
            toolbar!!.requestLayout()
        }
    }

    override fun setCanBack(canback: Boolean) {

    }

    abstract fun initToolbar()

    /**
     * 从AppBarLayout中获取控件
     *
     * @param viewId 控件Id
     * @param <V>    返回泛型,减少强转操作
     * @return 可能为null
    </V> */
    override fun <V : View> findViewFromAppBar(@IdRes viewId: Int): V? {
        var view: View? = mViews.get(viewId)
        if (view == null && appBarLayout != null) {
            view = appBarLayout!!.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as V?
    }

    /**
     * 设置控件滑动效果
     *
     * @param viewId view id
     * @param flag   设置的滑动flag
     * @return 设置成功返回true, 设置失败返回false
     */
    override fun setScrollFlag(@IdRes viewId: Int, @AppBarLayout.LayoutParams.ScrollFlags flag: Int): Boolean {
        val view = findViewFromAppBar<View>(viewId)
        try {
            val layoutParams = view!!.layoutParams as AppBarLayout.LayoutParams
            layoutParams.scrollFlags = flag
        } catch (e: ClassCastException) {
            e.printStackTrace()
            return false
        } catch (e: NullPointerException) {
            e.printStackTrace()
            return false
        }

        return true
    }

    open fun setTextSize(size: Int) {}


    open fun setTitleSize(size: Int) {}


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
}

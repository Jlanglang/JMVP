package com.linfeng.mvp.templet

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup

import com.linfeng.mvp.MVPManager
import com.linfeng.mvp.R
import com.linfeng.mvp.base.BaseActivity
import com.linfeng.mvp.presenter.BasePresenter
import com.linfeng.mvp.templet.helper.ToolbarHelper
import com.linfeng.mvp.templet.options.ToolbarOptions
import com.linfeng.mvp.view.ToolbarView

/**
 * 模版Activity
 *
 * @param <T>
</T> */
abstract class TemplateActivity<T : BasePresenter<*>> : BaseActivity<T>(), ToolbarView {

    private lateinit var mRootView: ViewGroup

    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    override val toolbarLayout: Int = toolbarOptions.getToolbarLayout()

    override val statusBarDrawable: Int = toolbarOptions.getStatusDrawable()

    override val toolbarOptions: ToolbarOptions
        get() = MVPManager.toolbarOptions


    override val isMaterialDesign: Boolean
        get() = false

    override fun appcompatActivity(): TemplateActivity<*> {
        return this
    }

    /**
     * 如果设置的主题不是NoActionBar或者initToolbar()返回是0,则返回null.
     *
     * @return mToolbar 可能为null.
     */
    val toolbar: Toolbar?
        get() = toolbarHelper.toolbar

    /**
     * 请在ui线程中调用
     *
     * @return
     */
    override val toolbarHelper: ToolbarHelper by lazy {
        ToolbarHelper.create(this, mRootView)
    }

    override fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            throw IllegalStateException("please extends BaseActivity.TemplateActivity Theme must be NoActionbar")
        }
        mRootView = inflater.inflate(R.layout.templet_layout, null) as ViewGroup
        //初始化一次
        val baseView = super.initView(inflater, savedInstanceState)
        val templateView = wrapperContentView(baseView)
        mRootView.addView(templateView, 1)

        val layoutParams = templateView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
        templateView.requestLayout()
        return mRootView
    }

    open fun wrapperContentView(view: View): View {
        return view
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return isMaterialDesign && super.onCreateOptionsMenu(menu)
    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
     */
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    /**
     * 显示menu的icon
     *
     * @param view
     * @param menu
     * @return
     */
    @SuppressLint("RestrictedApi")
    override fun onPrepareOptionsPanel(view: View, menu: Menu?): Boolean {
        if (menu != null) {
            if (menu.javaClass.simpleName == "MenuBuilder") {
                try {
                    val menuBuilder = menu as MenuBuilder?
                    menuBuilder!!.setOptionalIconsVisible(true)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        return super.onPrepareOptionsPanel(view, menu)
    }

}

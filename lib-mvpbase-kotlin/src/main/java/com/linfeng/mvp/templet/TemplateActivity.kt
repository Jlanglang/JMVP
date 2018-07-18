package com.linfeng.mvp.templet

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.ActionBar
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.baozi.mvp.MVPManager
import com.baozi.mvp.R
import com.baozi.mvp.base.BaseActivity
import com.baozi.mvp.presenter.BasePresenter
import com.baozi.mvp.templet.helper.ToolbarHelper
import com.baozi.mvp.templet.options.ToolbarOptions
import com.baozi.mvp.view.ToolbarView
import com.linfeng.mvp.base.BaseActivity
import com.linfeng.mvp.presenter.BasePresenter
import com.linfeng.mvp.templet.helper.ToolbarHelper
import com.linfeng.mvp.view.ToolbarView

/**
 * 模版Activity
 *
 * @param <T>
</T> */
abstract class TemplateActivity<T : BasePresenter<*>> : BaseActivity<T>(), ToolbarView {
    private var mToolbarHelper: ToolbarHelper? = null
    private var mRootView: ViewGroup? = null

    /**
     * 如果调用在initView()之前,可能为null
     *
     * @return
     */
    val contentView: View?
        get() = mRootView

    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    val toolbarLayout: Int
        get() = toolbarOptions.getToolbarLayout()

    protected val statusBarDrawable: Int
        get() = toolbarOptions.getStatusDrawable()

    val toolbarOptions: ToolbarOptions
        get() = MVPManager.getToolbarOptions()


    val appcompatActivity: TemplateActivity<*>
        get() = this

    val isMaterialDesign: Boolean
        get() = false

    /**
     * 如果设置的主题不是NoActionBar或者initToolbar()返回是0,则返回null.
     *
     * @return mToolbar 可能为null.
     */
    val toolbar: Toolbar
        get() = toolbarHelper.getToolbar()

    /**
     * 请在ui线程中调用
     *
     * @return
     */
    val toolbarHelper: ToolbarHelper
        get() {
            if (mToolbarHelper == null) {
                mToolbarHelper = ToolbarHelper.Create(this, mRootView)
            }
            return mToolbarHelper
        }

    fun initView(inflater: LayoutInflater, savedInstanceState: Bundle): View {
        val supportActionBar = getSupportActionBar()
        if (supportActionBar != null) {
            throw IllegalStateException("please extends BaseActivity.TemplateActivity Theme must be NoActionbar")
        }
        mRootView = inflater.inflate(R.layout.templet_layout, null) as ViewGroup
        //初始化一次
        mToolbarHelper = toolbarHelper
        val baseView = super.initView(inflater, savedInstanceState)
        val templateView = wrapperContentView(baseView)
        mRootView!!.addView(templateView, 1)

        val layoutParams = templateView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
        templateView.requestLayout()
        return mRootView
    }

    protected fun wrapperContentView(view: View): View {
        return view
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        return isMaterialDesign && super.onCreateOptionsMenu(menu)
    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
     */
    fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    /**
     * 显示menu的icon
     *
     * @param view
     * @param menu
     * @return
     */
    protected fun onPrepareOptionsPanel(view: View, menu: Menu?): Boolean {
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

    /**
     * 每次菜单被关闭时调用.（菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项）
     */
    fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象.
     */
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    protected abstract fun initPresenter(): T

}

package com.linfeng.mvp.templet

import android.os.Bundle
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.linfeng.mvp.MVPManager
import com.linfeng.mvp.R
import com.linfeng.mvp.base.BaseActivity
import com.linfeng.mvp.presenter.BasePresenter
import com.linfeng.mvp.presenter.EmptyPresenter
import com.linfeng.mvp.templet.helper.ToolbarHelper
import com.linfeng.mvp.templet.options.ToolbarOptions
import com.linfeng.mvp.templet.weight.LoadingPager
import com.linfeng.mvp.view.ToolbarView

/**
 * 模版Activity
 *
 * @param <T>
</T> */
@Deprecated("")
abstract class TempletActivity<T : BasePresenter<*>> : BaseActivity<T>(), ToolbarView {
    //    private var mToolbarHelper: ToolbarHelper? = null
    private lateinit var mRootView: ViewGroup
//    private lateinit var mContentView: View

    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    override val toolbarLayout: Int
        get() = ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL

    override val statusBarDrawable: Int
        get() = toolbarOptions!!.getStatusDrawable()

    override val toolbarOptions: ToolbarOptions?
        get() = MVPManager.toolbarOptions


    val appcompatActivity: TempletActivity<*>
        get() = this

    //    /**
    //     * 切换MaterialDesign风格.
    //     *
    //     * @param isMaterialDesign
    //     */
    //    @Override
    //    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
    //        getToolbarHelper().setMaterialDesignEnabled(isMaterialDesign);
    //    }

    override val isMaterialDesign: Boolean
        get() = false

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
        ToolbarHelper.Create(this, mRootView)
    }

    public override fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            throw IllegalStateException("please extends BaseActivity.TempletActivity Theme must be NoActionbar")
        }
        mRootView = inflater.inflate(R.layout.templet_layout, null) as ViewGroup
        //初始化一次
//        mToolbarHelper = toolbarHelper
        //        mRootView.addView();
        //        //ContentView容器
        val loadingPager = mRootView.findViewById<View>(R.id.templet_content) as LoadingPager
        mContentView = super.initView(inflater, savedInstanceState)
        loadingPager.successPage = mContentView
        return mRootView
    }

//    /**
//     * 如果调用在initView()之前,可能为null
//     *
//     * @return
//     */
//    override fun getContentView(): View {
//        return mContentView
//    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return if (!isMaterialDesign) {
            false
        } else super.onCreateOptionsMenu(menu)
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

    /**
     * 每次菜单被关闭时调用.（菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项）
     */
    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun initPresenter(): T {
        return EmptyPresenter() as T
    }

}

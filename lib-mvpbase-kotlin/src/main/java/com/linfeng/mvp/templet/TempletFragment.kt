package com.linfeng.mvp.templet

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View

import com.baozi.mvp.R
import com.baozi.mvp.base.BaseFragment
import com.baozi.mvp.presenter.BasePresenter
import com.baozi.mvp.templet.helper.ToolbarHelper
import com.baozi.mvp.templet.weight.LoadingPager
import com.baozi.mvp.view.ToolbarView

/**
 * 模版Fragment
 *
 * @param <T>
</T> */
@Deprecated("")
abstract class TempletFragment<T : BasePresenter> : BaseFragment<T>(), ToolbarView {
    private var mToolbarHelper: ToolbarHelper? = null
    private var rootView: View? = null

    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    val toolbarLayout: Int
        get() = ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL


    //    /**
    //     * 切换MaterialDesign风格.
    //     *
    //     * @param isMaterialDesign
    //     */
    //    @Override
    //    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
    //        getToolbarHelper().setMaterialDesignEnabled(isMaterialDesign);
    //    }

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
     * @return
     */
    val toolbarHelper: ToolbarHelper
        get() {
            if (mToolbarHelper == null) {
                mToolbarHelper = ToolbarHelper.Create(this, rootView)
            }
            return mToolbarHelper
        }

    /**
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    fun initView(inflater: LayoutInflater, savedInstanceState: Bundle): View {
        rootView = inflater.inflate(R.layout.templet_layout, null)
        //初始化一次
        mToolbarHelper = toolbarHelper
        //        //ContentView容器
        val loadingPager = rootView!!.findViewById(R.id.templet_content) as LoadingPager
        //        //真正的创建contentView
        val contentView = super.initView(inflater, savedInstanceState)
        //        contentGroup.removeAllViews();
        loadingPager.setSuccessPage(contentView)
        return rootView
    }

    fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
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

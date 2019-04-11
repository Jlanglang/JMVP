package com.linfeng.mvp.templet

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linfeng.mvp.MVPManager
import com.linfeng.mvp.R
import com.linfeng.mvp.base.BaseFragment
import com.linfeng.mvp.presenter.BasePresenter
import com.linfeng.mvp.templet.helper.ToolbarHelper
import com.linfeng.mvp.templet.options.ContentOptions
import com.linfeng.mvp.templet.options.ToolbarOptions
import com.linfeng.mvp.view.ToolbarView

/**
 * 模版Fragment
 *
 * @param <T>
</T> */
abstract class TemplateFragment<T : BasePresenter<*>> : BaseFragment<T>(), ToolbarView {
    private lateinit var rootView: ViewGroup


    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    override val toolbarLayout: Int
        get() = ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL

    override val toolbarOptions: ToolbarOptions
        get() = MVPManager.toolbarOptions

    protected val contentOptions: ContentOptions
        get() = MVPManager.contentOptions

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
     * @return
     */
    override val toolbarHelper: ToolbarHelper by lazy {
        ToolbarHelper.create(this, rootView)
    }

    /**
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    override fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.templet_layout, null) as ViewGroup
//        //初始化一次
//        mToolbarHelper = toolbarHelper

        val initView = super.initView(inflater, savedInstanceState) ?: return rootView
        val view = wrapperContentView(initView)
        rootView.addView(view, 1)

        val layoutParams = view.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
        view.requestLayout()
        return rootView
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象.
     */
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }

//    protected abstract fun initPresenter(): T

    protected open fun wrapperContentView(view: View): View {
        return view
    }

}

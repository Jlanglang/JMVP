package com.linfeng.mvp.view

import android.app.ActionBar
import android.content.res.Resources
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window

/**
 * Created by baozi on 2017/2/20.
 * 用户页面,操作页面，对应Activity,frgament...
 */

interface UIView : BaseView {


    /**
     * 主要视图View

     * @return
     */
    fun getContentView(): View

    /**
     * res资源获取

     * @return
     */

    fun getActionBar(): ActionBar

    fun getWindow(): Window

    fun getResources(): Resources
    /**
     * 根据id获得控件
     * 需要调用在initView()之后,否则会出现NullPointerException

     * @param viewId
     * *
     * @param <V>
     * *
     * @return
    </V> */
    fun <V : View> findView(@IdRes viewId: Int): V

    /**
     * 回退
     */

    fun onBack()


    /**
     * Frgament跳转.

     * @param tofragment
     */
    fun startFragment(tofragment: Fragment)

    /**
     * Frgament跳转.

     * @param tofragment
     */
    fun startFragment(tofragment: Fragment, tag: String?)

    fun setSupportActionBar(toolbar: Toolbar?)
}

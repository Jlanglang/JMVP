package com.linfeng.mvp.view

import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window

/**
 * Created by baozi on 2017/2/20.
 * 用户页面,操作页面，对应Activity,frgament...
 */

interface UIView : BaseView {
    /**
     * res资源获取
     *
     * @return
     */
    fun resources(): Resources

    /**
     * 获取Activity
     *
     * @return
     */
    fun appcompatActivity(): AppCompatActivity

    /**
     * 主要视图View
     *
     * @return
     */
    fun getContentView(): View

    fun window(): Window

    fun supportActionBar(): ActionBar?

    /**
     * 回退
     */

    fun onBack()

    /**
     * 处理异常
     *
     * @param throwable
     */
    fun onNewThrowable(throwable: Throwable)

    /**
     * Frgament跳转.
     *
     * @param fragment
     */
    fun startFragment(fragment: Fragment)

    /**
     * Frgament跳转.
     *
     * @param fragment
     */
    fun startFragment(fragment: Fragment, tag: String? = null, enterAnim: Int = 0, exitAnim: Int = 0, popEnter: Int = 0
                      , popExit: Int = 0, isAddBack: Boolean = true)

    fun setSupportActionBar(toolbar: Toolbar?)

    fun finishActivity()
}

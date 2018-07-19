package com.linfeng.mvp.view

import android.support.annotation.LayoutRes

import com.linfeng.mvp.templet.helper.ToolbarHelper
import com.linfeng.mvp.templet.options.ToolbarOptions

/**
 * @author jlanglang  2017/3/4 17:44
 * @版本 2.0
 * @Change
 */

interface ToolbarView : BaseView {
    /**
     * 获得ToolbarHelper,Presenter可以通过ToolbarHelper的来控制toolbar
     */
    val toolbarHelper: ToolbarHelper

    /**
     * 是否使用MaterialDesign风格
     *
     * @return
     */
    val isMaterialDesign: Boolean

    /**
     * 通过这个修改toolbar的样式layout,不需要可以传0或者-1;
     *
     * @return
     */
    @get:LayoutRes
    val toolbarLayout: Int

    val toolbarOptions: ToolbarOptions?


    /**
     * 回退
     */
    fun onBackPressed()
}

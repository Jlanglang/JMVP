package com.linfeng.mvp.templet.helper

import android.view.View

import com.linfeng.mvp.templet.options.ToolbarOptions
import com.linfeng.mvp.view.ToolbarView

/**
 * @author jlanglang  2017/3/7 16:29
 * @版本 2.0
 * @Change
 */
class MDToolBarHelperImpl(uiView: ToolbarView, rootView: View, toolbarLayout: Int) : BaseToolBarHelperImpl(uiView, rootView, toolbarLayout) {

    override fun initToolbar() {
        ToolbarHelper.simpleInitToolbar(mToolbarView.mContext, toolbar, true)
    }

    override fun setToolbarOptions(toolbarOptions: ToolbarOptions) {
        super.setToolbarOptions(toolbarOptions)

    }

    override fun setTextSize(size: Int) {

    }

    override fun setTitleSize(size: Int) {

    }
}

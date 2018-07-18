package com.linfeng.mvp.templet.helper

import android.view.View

import com.baozi.mvp.templet.options.ToolbarOptions
import com.baozi.mvp.view.ToolbarView

/**
 * @author jlanglang  2017/3/7 16:29
 * @版本 2.0
 * @Change
 */
class MDToolBarHelperImpl(uiView: ToolbarView, rootView: View, toolbarLayout: Int) : BaseToolBarHelperImpl(uiView, rootView, toolbarLayout) {

    override fun initToolbar() {
        ToolbarHelper.SimpleInitToolbar(mToolbarView.getContext(), toolbar!!, true)
    }

    override fun setToolbarOptions(options: ToolbarOptions?) {
        super.setToolbarOptions(options)

    }

    override fun setTextSize(size: Int) {

    }

    override fun setTitleSize(size: Int) {

    }
}

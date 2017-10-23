package com.baozi.module_kotlindemo.presenter

import com.baozi.module_kotlindemo.model.DemoView
import com.baozi.mvp.presenter.BasePresenter

/**
 * Created by baozi on 2017/10/20.
 */
class DemoPresenter : BasePresenter<DemoView>() {

    override fun onCreate() {
//        mView.context
//        mView.getIntent()
        mView.A()
        mView.B()
    }

    override fun initData() {
    }

    override fun cancelNetWork() {
    }
}
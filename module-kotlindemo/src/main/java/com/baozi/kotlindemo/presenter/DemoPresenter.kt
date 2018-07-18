package com.baozi.kotlindemo.presenter

import com.baozi.kotlindemo.model.DemoView
import com.linfeng.mvp.presenter.BasePresenter1

/**
 * Created by baozi on 2017/10/20.
 */
class DemoPresenter : BasePresenter1<DemoView>() {
    override fun cancelNetWork() {

    }

    override fun onCreate() {
        mView.A()
        mView.B()
    }
}
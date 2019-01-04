package com.baozi.kotlindemo.presenter

import com.baozi.kotlindemo.DemoActivity
import com.linfeng.mvp.presenter.BasePresenter

/**
 * Created by baozi on 2017/10/20.
 */
class DemoPresenter : BasePresenter<DemoActivity>() {
    override fun onRefreshData() {
    }

    override fun netWorkError(throwable: Throwable) {

    }

    override fun cancelRequest() {

    }

    override fun onCreate() {

    }
}
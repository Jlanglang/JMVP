package com.baozi.kotlindemo.presenter

import com.baozi.kotlindemo.model.DemoView
import com.linfeng.mvp.presenter.BasePresenter

/**
 * Created by baozi on 2017/10/20.
 */
class DemoPresenter : BasePresenter<DemoView>() {
    override fun onRefreshData() {

    }

    override fun netWorkError(throwable: Throwable) {

    }

    override fun cancelNetWork() {

    }

    override fun onCreate() {
    }
}
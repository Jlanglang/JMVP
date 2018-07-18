package com.linfeng.mvp.presenter

import android.view.View

/**
 * Created by baozi on 2017/12/20.
 */

class EmptyPresenter : BasePresenter<*>() {

    override val contentView: View?
        get() = null

    override fun onCreate() {}

    override fun onRefreshData() {

    }

    override fun cancelNetWork() {

    }

    override fun netWorkError(throwable: Throwable) {

    }
}

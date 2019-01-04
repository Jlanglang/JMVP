package com.linfeng.mvp.presenter

import android.view.View
import com.linfeng.mvp.view.BaseView
import com.linfeng.mvp.view.UIView

/**
 * Created by baozi on 2017/12/20.
 */

class EmptyPresenter : BasePresenter<UIView>() {

    override fun onCreate() {}

    override fun onRefreshData() {

    }

    override fun cancelRequest() {

    }

    override fun netWorkError(throwable: Throwable) {

    }
}

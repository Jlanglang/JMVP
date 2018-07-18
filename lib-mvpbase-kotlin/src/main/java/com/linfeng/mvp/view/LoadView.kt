package com.linfeng.mvp.view

import com.linfeng.mvp.templet.weight.LoadingPager


/**
 * Created by baozi on 2017/12/8.
 */

interface LoadView : BaseView {

    val loadPager: LoadingPager
    fun showSuccess()

    fun showEmpty()

    fun showError(throwable: Throwable, isShowError: Boolean)

    fun showError(throwable: Throwable)

    fun showLoading()

    fun triggerInit()


}

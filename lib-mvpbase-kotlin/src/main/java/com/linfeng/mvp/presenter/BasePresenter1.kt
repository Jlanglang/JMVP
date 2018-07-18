package com.linfeng.mvp.presenter

import android.content.Intent
import android.os.Bundle
import com.linfeng.mvp.view.BaseView


/**
 * @author jlanglang  2016/11/11 15:10
 * *
 * @版本 2.0
 * *
 * @Change
 */
abstract class BasePresenter1<T : BaseView> {
    lateinit var mView: T

    fun attach(t: BaseView) {
        mView = t as T
    }

    /**
     * 做初始化的操作,需要在view的视图初始化完成之后才能调用
     * 建议只初始化一些对象,而不要去做耗时操作.
     */
    open fun onCreate() {

    }

    /**
     * 运行在onCreate()之后,可能在onStart()之后调用.
     * 建议加载数据,处理数据刷新页面的操作放在这里
     */
    open fun initData() {

    }

    /**
     * 在这里结束异步操作
     */
    open fun onDestroy() {
        cancelNetWork()
    }

    /**
     * 解除绑定
     */
    open fun onDetach() {

    }

    /**
     * 弄成抽象，是为了提醒你，取消回调
     * 取消网络请求回调
     */
    abstract fun cancelNetWork()

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    open fun onSaveInstanceState(outState: Bundle) {

    }

    open fun onStart() {

    }

    open fun onResume() {

    }

    open fun onRestart() {

    }

    open fun onPause() {

    }

    open fun onStop() {

    }


}


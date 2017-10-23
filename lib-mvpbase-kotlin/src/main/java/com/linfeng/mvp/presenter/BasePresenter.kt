package com.baozi.mvp.presenter

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
abstract class BasePresenter<T : BaseView>() {
    lateinit var mView: T
    fun attach(t: Any) {
        mView = t as T
    }

    /**
     * 做初始化的操作,需要在view的视图初始化完成之后才能调用
     * 建议只初始化一些对象,而不要去做耗时操作.
     */
    abstract fun onCreate()

    /**
     * 运行在onCreate()之后,可能在onStart()之后调用.
     * 建议加载数据,处理数据刷新页面的操作放在这里
     */
    abstract fun initData()

    /**
     * 在这里结束异步操作
     */
    fun onDestroy() {
        cancelNetWork()
    }

    /**
     * 解除绑定
     */
    fun onDetach() {

    }


    /**
     * 取消网络请求回调
     */
    abstract fun cancelNetWork()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun onSaveInstanceState(outState: Bundle) {

    }

    fun onStart() {

    }

    fun onResume() {

    }

    fun onRestart() {

    }

    fun onPause() {

    }

    fun onStop() {

    }


}


package com.linfeng.mvp.presenter

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.baozi.mvp.view.UIView


/**
 * @author jlanglang  2016/11/11 15:10
 * @版本 2.0
 * @Change
 */
abstract class BasePresenter<T : UIView> {


    var view: T
        protected set

    abstract val contentView: View

    /**
     * 绑定View
     */
    fun onAttach(view: T) {
        this.view = view
    }


    /**
     * 解除绑定
     */
    fun onDetach() {
        //        mView = null;
    }

    /**
     * 做初始化的操作,需要在view的视图初始化完成之后才能调用
     * 建议只初始化一些对象,而不要去做耗时操作.
     */
    abstract fun onCreate()

    /**
     * 运行在onCreate()之后,可能在onStart()之后调用.
     * 建议初始化数据,刷新的网络请求
     */
    abstract fun onRefreshData()

    /**
     * 取消网络请求回调
     */
    abstract fun cancelNetWork()

    /**
     * 本地网络异常
     */
    abstract fun netWorkError(throwable: Throwable)

    fun onDestroy() {
        cancelNetWork()
    }

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

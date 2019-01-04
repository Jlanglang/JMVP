package com.linfeng.mvp.presenter

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.linfeng.mvp.view.UIView


/**
 * @author jlanglang  2016/11/11 15:10
 * @版本 2.0
 * @Change
 */
abstract class BasePresenter<T : UIView> {


    lateinit var view: T

    open fun contentView(): View = view.getContentView()

    /**
     * 绑定View
     */
    open fun attach(view: UIView) {
        this.view = view as T
    }

    /**
     * 解除绑定
     */
    open fun onDetach() {

    }

    /**
     * 做初始化的操作,需要在view的视图初始化完成之后才能调用
     * 建议只初始化一些对象,而不要去做耗时操作.
     */
    open fun onCreate() {

    }

    /**
     * 运行在onCreate()之后,可能在onStart()之后调用.
     * 建议初始化数据,刷新的网络请求
     */
    open fun onRefreshData() {

    }

    /**
     * 弄成抽象，是为了提醒你，取消回调
     * 取消网络请求回调
     */
    abstract fun cancelRequest()

    /**
     * 本地网络异常
     */
    open fun netWorkError(throwable: Throwable) {

    }

    open fun onDestroy() {
        cancelRequest()
    }

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

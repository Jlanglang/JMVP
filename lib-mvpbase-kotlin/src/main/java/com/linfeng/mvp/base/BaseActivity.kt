package com.baozi.mvp.base

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import com.baozi.mvp.presenter.BasePresenter
import com.linfeng.mvp.animation.JMvpContract
import com.linfeng.mvp.property.PresenterProperty
import com.linfeng.mvp.view.BaseActivityView

/**
 * @author jlanglang  2016/1/5 9:42
 */
abstract class BaseActivity<T : BasePresenter<*>> : AppCompatActivity(), BaseActivityView {
    override var mContext: Context = this
    protected var mPresenter by PresenterProperty<T>(this)
    private lateinit var mContentView: View
    private val mViews = SparseArray<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = initPresenter()
        //初始化ContentView
        mContentView = initView(layoutInflater, savedInstanceState)
        //初始化Activity
        init(savedInstanceState)
        //初始化presenter
        mPresenter.onCreate()
        //延时加载数据.
        Looper.myQueue().addIdleHandler {
            mPresenter.initData()
            false
        }
        mContext.applicationContext
    }

    override fun getContentView(): View {
        return mContentView
    }

    abstract fun initPresenter(): T

    override fun onStart() {
        mPresenter.onStart()
        super.onStart()
    }

    override fun onPause() {
        mPresenter.onPause()
        super.onPause()
    }

    override fun onRestart() {
        mPresenter.onRestart()
        super.onRestart()
    }

    override fun onStop() {
        mPresenter.onStop()
        super.onStop()
    }

    override fun onResume() {
        mPresenter.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }

    override fun onDetachedFromWindow() {
        mPresenter.onDetach()
        super.onDetachedFromWindow()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mPresenter.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    /**
     * 跳转fragment

     * @param tofragment
     */
    override fun startFragment(tofragment: Fragment) {
        startFragment(tofragment, null)
    }

    /**
     * @param tofragment 跳转的fragment
     * *
     * @param tag        fragment的标签
     */
    override fun startFragment(tofragment: Fragment, tag: String?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(android.R.id.content, tofragment, tag)
        fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    /**
     * 通过viewId获取控件

     * @param viewId 资源id
     * *
     * @return
     */
    override fun <V : View> findView(@IdRes viewId: Int): V {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = super.findViewById(viewId) ?: throw IllegalStateException("this id not find")
            mViews.put(viewId, view)
        }
        return view as V
    }

    /**
     * onBackPressed();
     */
    override fun onBack() {
        onBackPressed()
    }

    fun getAppcompatActivity(): BaseActivity<T> {
        return this
    }


    /**
     * 初始化ContentView
     * 建议不要包含toolbar

     * @param inflater
     * *
     * @param savedInstanceState
     * *
     * @return
     */
    protected fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
        val initView = initView(savedInstanceState)
        return inflater.inflate(initView, null)
    }

    /**
     * 初始化ContentView
     * 建议不要包含toolbar
     * @param savedInstanceState
     * @return
     */
    protected abstract fun initView(savedInstanceState: Bundle?): Int

    /**
     * 运行在initView之后
     * 此时setContentView
     * 可以做一些初始化操作

     * @return
     */
    protected fun init(savedInstanceState: Bundle?) {

    }

}

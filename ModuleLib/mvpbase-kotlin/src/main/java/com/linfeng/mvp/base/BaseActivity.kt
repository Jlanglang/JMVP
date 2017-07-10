package com.baozi.mvp.base

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baozi.mvp.presenter.BasePresenter
import com.linfeng.mvp.view.BaseActivityView

/**
 * @author jlanglang  2016/1/5 9:42
 */
abstract class BaseActivity<out T : BasePresenter> : AppCompatActivity(), BaseActivityView {
    private var mViews: SparseArray<View>? = null
    protected val mPresenter: T by lazy {
        initPresenter()
    }
    override val context: Context
        get() = this
    private var mContentView: View? = null

    init {
        mViews = SparseArray<View>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        //创建presenter
//        mPresenter = initPresenter()
        //绑定Activity
//        mPresenter.onAttch(this)
        //初始化ContentView
        mContentView = initView(LayoutInflater.from(this), savedInstanceState)
        super.setContentView(mContentView)
        //初始化Activity
        init(savedInstanceState)
        //初始化presenter
        mPresenter.onCreate()
        //延时加载数据.
        Looper.myQueue().addIdleHandler {
            mPresenter.initData()
            false
        }

    }

    override fun setContentView(@LayoutRes layoutResID: Int) {

    }

    override fun setContentView(view: View) {

    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {

    }

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

//    /**
//     * 跳转fragment
//
//     * @param tofragment
//     */
//    override fun startFragment(tofragment: Fragment) {
//        startFragment(tofragment, null)
//    }
//
//    /**
//     * @param tofragment 跳转的fragment
//     * *
//     * @param tag        fragment的标签
//     */
//    override fun startFragment(tofragment: Fragment, tag: String?) {
//        val fragmentTransaction = getSupportFragmentManager().beginTransaction()
//        fragmentTransaction.add(android.R.id.content, tofragment, tag)
//        fragmentTransaction.addToBackStack(tag)
//        fragmentTransaction.commitAllowingStateLoss()
//    }


//    /**
//     * onBackPressed();
//     */
//    override fun onBack() {
//        onBackPressed()
//    }
//
//    override fun getAppcompatActivity(): BaseActivity<*> {
//        return this
//    }
//
//    override fun getContext(): Context {
//        return this
//    }

//    /**
//     * 通过viewId获取控件
//
//     * @param viewId 资源id
//     * *
//     * @return
//     */
//    override fun <V : View> findView(@IdRes viewId: Int): V {
//        var view: View? = mViews!!.get(viewId)
//        if (view == null) {
//            view = super.findViewById(viewId)
//            mViews!!.put(viewId, view)
//        }
//        return view as V?
//    }
//
//
//    override fun findViewById(@IdRes id: Int): View {
//        return findView(id)
//    }


    /**
     * 初始化ContentView
     * 建议不要包含toolbar

     * @param inflater
     * *
     * @param savedInstanceState
     * *
     * @return
     */
    protected abstract fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View

    /**
     * 运行在initView之后
     * 此时setContentView
     * 可以做一些初始化操作

     * @return
     */
    protected fun init(savedInstanceState: Bundle?) {

    }

    /**
     * 子类实现Presenter,且必须继承BasePrensenter

     * @return
     */
    protected abstract fun initPresenter(): T

}

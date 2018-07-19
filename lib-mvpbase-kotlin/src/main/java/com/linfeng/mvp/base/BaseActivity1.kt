//package com.linfeng.mvp.base
//
//import android.content.Context
//import android.os.Bundle
//import android.os.Looper
//import android.support.v4.app.Fragment
//import android.support.v7.app.AppCompatActivity
//import android.view.LayoutInflater
//import android.view.View
//import com.linfeng.mvp.annotation.JMvpContract
//import com.linfeng.mvp.presenter.BasePresenter
//import com.linfeng.mvp.presenter.BasePresenter1
//import com.linfeng.mvp.property.PresenterProperty
//import com.linfeng.mvp.view.BaseActivityView
//import java.lang.reflect.ParameterizedType
//
//
///**
// * @author jlanglang  2016/1/5 9:42
// *
// */
//abstract class BaseActivity1<T : BasePresenter<*>> : AppCompatActivity(), BaseActivityView {
//    var mPresenter by PresenterProperty<T>(this)
//    override var mContext: Context = this
//    private lateinit var mContentView: View
////    private val mViews = SparseArray<View>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //初始化ContentView
//        mContentView = initView(layoutInflater, savedInstanceState)
//        //初始化Activity
//        init(savedInstanceState)
//
//        mPresenter = initPresenter()
//        //初始化presenter
//        mPresenter.onCreate()
//        //延时加载数据.
//        Looper.myQueue().addIdleHandler {
//            mPresenter.onRefreshData()
//            false
//        }
//    }
//
//
//
//    protected open fun initPresenter(): T {
//        // 通过反射机制获取子类传递过来的实体类的类型信息
//        val type = this.javaClass.genericSuperclass as ParameterizedType
//        return (type.actualTypeArguments[0] as Class<T>).newInstance()
//    }
//
//    override fun getContentView(): View {
//        return mContentView
//    }
//
//
//    override fun onStart() {
//        mPresenter.onStart()
//        super.onStart()
//    }
//
//    override fun onPause() {
//        mPresenter.onPause()
//        super.onPause()
//    }
//
//    override fun onRestart() {
//        mPresenter.onRestart()
//        super.onRestart()
//    }
//
//    override fun onStop() {
//        mPresenter.onStop()
//        super.onStop()
//    }
//
//    override fun onResume() {
//        mPresenter.onResume()
//        super.onResume()
//    }
//
//    override fun onDestroy() {
//        mPresenter.onDestroy()
//        super.onDestroy()
//    }
//
//    override fun onDetachedFromWindow() {
//        mPresenter.onDetach()
//        super.onDetachedFromWindow()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        mPresenter.onSaveInstanceState(outState)
//        super.onSaveInstanceState(outState)
//    }
//
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
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.add(android.R.id.content, tofragment, tag)
//        fragmentTransaction.addToBackStack(tag)
//        fragmentTransaction.commitAllowingStateLoss()
//    }
//
////    /**
////     * 通过viewId获取控件
////
////     * @param viewId 资源id
////     * *
////     * @return
////     */
////    override fun <V : View> findView(@IdRes viewId: Int): V {
////        var view: View? = mViews.get(viewId)
////        if (view == null) {
////            view = super.findViewById(viewId) ?: throw IllegalStateException("this id not find")
////            mViews.put(viewId, view)
////        }
////        return view as V
////    }
//
//    /**
//     * onBack();
//     */
//    override fun onBack() {
//        onBack()
//    }
//
//    open fun getAppcompatActivity(): BaseActivity1<T> {
//        return this
//    }
//
//
//    /**
//     * 初始化ContentView
//     * 建议不要包含toolbar
//
//     * @param inflater
//     * *
//     * @param savedInstanceState
//     * *
//     * @return
//     */
//    protected open fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
//        val initView = initView(savedInstanceState)
//        return inflater.inflate(initView, null)
//    }
//
//    /**
//     * 初始化ContentView
//     * 建议不要包含toolbar
//     * @param savedInstanceState
//     * @return
//     */
//    protected open fun initView(savedInstanceState: Bundle?): Int {
//        val annotation = this.javaClass.getAnnotation(JMvpContract::class.java)
//        if (annotation != null) {
//            return annotation.layout
//        }
//        return 0
//    }
//
//
//    /**
//     * 运行在initView之后
//     * 此时setContentView
//     * 可以做一些初始化操作
//
//     * @return
//     */
//    protected open fun init(savedInstanceState: Bundle?) {
//
//    }
//
//}

package com.linfeng.mvp.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.linfeng.mvp.annotation.JMvpContract
import com.linfeng.mvp.presenter.BasePresenter
import com.linfeng.mvp.property.PresenterProperty
import java.lang.reflect.ParameterizedType
import kotlin.properties.Delegates

/**
 * @author jlanglang  2016/1/5 9:42
 */
abstract class BaseFragment<T : BasePresenter<*>> : Fragment() {
    override var mContext by Delegates.notNull<Context>()

    protected var mPresenter: T by PresenterProperty(this)
    protected lateinit var mBundle: Bundle
    private val mViews = SparseArray<View>()
    private var mContentView: View? = null
    private var isInit: Boolean = false


    /**
     * 绑定activity

     * @param context
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
        //应该只创建一次Presenter.
        if (!isInit) {
            mPresenter = initPresenter()
        }
    }

    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取bundle,并保存起来
        mBundle = if (savedInstanceState != null) {
            savedInstanceState.getBundle("bundle")
        } else {
            if (arguments == null) Bundle() else arguments!!
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle("bundle", mBundle)
        super.onSaveInstanceState(outState)
    }

    /**
     * 运行在onCreate之后
     * 生成view视图

     * @param inflater
     * *
     * @param container
     * *
     * @param savedInstanceState
     * *
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (null == mContentView) {
            mContentView = initView(inflater, savedInstanceState)
        } else {
            //缓存的ContentView需要判断是否已有parent， 如果有parent需要从parent删除，否则会抛出异常。
            val parent: ViewGroup = mContentView!!.parent as ViewGroup
            parent.removeView(mContentView)
        }
        return mContentView
    }

    /**
     * 运行在onCreateView之后
     * 加载数据,初始化Presenter

     * @param savedInstanceState
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //View做一些初始化操作.
        init(savedInstanceState)
        //初始化Presenter,应该只初始化一次
        if (!isInit) {
            isInit = true
            mPresenter.onCreate()
            //可见时再加载数据刷新视图
            Looper.myQueue().addIdleHandler {
                mPresenter.onRefreshData()
                false
            }
//            onPresentersCreate()
        }
    }

//    /**
//     * 扩展除了默认的presenter的其他Presenter初始化
//     */
//    protected fun onPresentersCreate() {
//
//    }

    override fun onStart() {
        mPresenter.onStart()
        super.onStart()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            mPresenter.onResume()
        } else {
            //相当于Fragment的onPause
            mPresenter.onPause()
        }
    }

    override fun onStop() {
        mPresenter.onStop()
        super.onStop()
    }

    override fun onDetach() {
        mPresenter.onDetach()
        super.onDetach()
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mPresenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun getContentView(): View {
        return mContentView!!
    }


    /**
     * 类似Activity的OnBackgress
     * fragment进行回退
     */
    override fun onBack() {
        fragmentManager?.popBackStackImmediate()
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
    protected fun initView(inflater: LayoutInflater?, savedInstanceState: Bundle?): View? {
        val layout = initView(savedInstanceState)
        return inflater?.inflate(layout, null)
    }

    /**
     * 建议不要包含toolbar

     * @param savedInstanceState
     * *
     * @return 布局layout
     */
    @LayoutRes
    protected fun initView(savedInstanceState: Bundle?): Int {
        val annotation = this.javaClass.getAnnotation(JMvpContract::class.java)
        if (annotation != null) {
            return annotation.layout
        }
        return 0
    }

    /**
     * 运行在initView之后
     * 此时已经setContentView
     * 可以做一些初始化操作
     */
    fun init(savedInstanceState: Bundle?) {

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
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.hide(this)?.add(android.R.id.content, tofragment, tag)
        fragmentTransaction?.addToBackStack(tag)
        fragmentTransaction?.commitAllowingStateLoss()
    }

    /**
     * 跳转Activity
     */
    fun startActivity(zclass: Class<*>) {
        val intent = Intent(mContext, zclass)
        startActivity(intent)
    }

    /**
     * 跳转Activity
     */
    fun startActivity(zclass: Class<*>, bundle: Bundle, flag: Int) {
        val intent = Intent(mContext, zclass)
        intent.putExtras(bundle)
        intent.addFlags(flag)
        startActivity(intent)
    }

    /**
     * 跳转Activity
     */
    fun startActivity(zclass: Class<*>, flag: Int) {
        val intent = Intent(mContext, zclass)
        intent.addFlags(flag)
        startActivity(intent)
    }


//    fun getBundle(): Bundle {
//        return mBundle
//    }
//
//    fun getFragment(): BaseFragment1<*> {
//        return this
//    }

    override fun getWindow(): Window? {
        return (mContext as Activity).window
    }

    fun getAppcompatActivity(): AppCompatActivity {
        return mContext as AppCompatActivity
    }

//    fun getSupportActionBar(): ActionBar? {
//        return getAppcompatActivity().supportActionBar
//    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        getAppcompatActivity().setSupportActionBar(toolbar)
    }

    /**
     * 通过viewId获取控件

     * @param viewId 资源id
     * *
     * @return
     */
    override fun <V : View> findView(viewId: Int): V {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = mContentView!!.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as V
    }

    /**
     * 初始化Presenter

     * @return
     */

    protected fun initPresenter(): T {
        // 通过反射机制获取子类传递过来的实体类的类型信息
        val type = this.javaClass.genericSuperclass as ParameterizedType
        return (type.actualTypeArguments[0] as Class<T>).newInstance()
    }
}

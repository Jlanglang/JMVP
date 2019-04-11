package com.linfeng.mvp.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.linfeng.mvp.MVPManager
import com.linfeng.mvp.annotation.JMvpContract
import com.linfeng.mvp.presenter.BasePresenter
import com.linfeng.mvp.property.PresenterProperty
import com.linfeng.mvp.view.UIView
import java.lang.reflect.ParameterizedType

/**
 * @author jlanglang  2016/1/5 9:42
 */
open class BaseActivity<T : BasePresenter<*>> : AppCompatActivity(), UIView {

    override var mContext: Context = this

    var isFinish: Boolean = false
        get() = isFinishing

    var mPresenter by PresenterProperty<T>(this)
    private var statusBarView: View? = null

    protected open val statusBarDrawable: Int
        @DrawableRes
        @ColorRes
        get() = MVPManager.toolbarOptions.getStatusDrawable()

    lateinit var mContentView: View

    override fun getContentView(): View {
        return mContentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建presenter
        mPresenter = initPresenter()
        //初始化ContentView
        mContentView = initView(layoutInflater, savedInstanceState)
        super.setContentView(mContentView)
    }

    override fun onNewThrowable(throwable: Throwable) {

    }

    @SuppressLint("ResourceType")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        //初始化Activity
        init(savedInstanceState)
        //初始化presenter
        mPresenter.onCreate()
        onPresentersCreate()
        if (statusBarDrawable > 0) {
            initStatusBar()
            window?.decorView?.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
                initStatusBar()
            }
        }
        mPresenter.onRefreshData()
    }

    open fun initStatusBar() {
        if (statusBarView == null) {
            val identifier = resources.getIdentifier("statusBarBackground", "id", "android")
            statusBarView = window?.findViewById(identifier)
        }
        if (statusBarView != null) {
            statusBarView!!.setBackgroundResource(statusBarDrawable)
        }
    }

    /**
     * 扩展除了默认的presenter的其他Presenter初始化
     */
    protected open fun onPresentersCreate() {

    }

    /**
     * 运行在initView之后
     * 已经setContentView
     * 可以做一些初始化操作
     *
     * @return
     */
    protected open fun init(savedInstanceState: Bundle?) {

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

    override fun onBack() {
        onBackPressed()
    }

    override fun resources(): Resources = resources

    override fun appcompatActivity(): AppCompatActivity = this

    override fun window(): Window = window

    override fun supportActionBar(): ActionBar = supportActionBar!!
    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    override fun startFragment(fragment: Fragment, tag: String?, enterAnim: Int, exitAnim: Int, popEnter: Int, popExit: Int, isAddBack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit)
        fragmentTransaction.add(android.R.id.content, fragment, tag)
        if (isAddBack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun startFragment(fragment: Fragment) {
        startFragment(fragment, null)
    }

    /**
     * @param rootFragment Activity内部fragment
     * @param containerId  fragment父容器
     */
    fun replaceFragment(rootFragment: Fragment, containerId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, rootFragment)
        fragmentTransaction.commitAllowingStateLoss()
    }

    /**
     * 跳转Activity
     */
    fun startActivity(aClass: Class<*>) {
        val intent = Intent(this, aClass)
        startActivity(intent)
    }

    /**
     * 跳转Activity
     */
    fun startActivity(aClass: Class<*>, bundle: Bundle) {
        val intent = Intent(this, aClass)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    /**
     * 跳转Activity
     */
    fun startActivity(aClass: Class<*>, bundle: Bundle, flag: Int) {
        val intent = Intent(this, aClass)
        intent.putExtras(bundle)
        intent.addFlags(flag)
        startActivity(intent)
    }

    /**
     * 跳转Activity
     */
    fun startActivity(aClass: Class<*>, flag: Int) {
        val intent = Intent(this, aClass)
        intent.addFlags(flag)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        mPresenter.onActivityResult(requestCode, resultCode, data)
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
    protected open fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
        val initView = initView(savedInstanceState)
        return inflater.inflate(initView, null)
    }

    /**
     * 初始化ContentView
     * 建议不要包含toolbar
     * @param savedInstanceState
     * @return
     */
    protected open fun initView(savedInstanceState: Bundle?): Int {
        val annotation = this.javaClass.getAnnotation(JMvpContract::class.java)
        if (annotation != null) {
            return annotation.layout
        }
        return 0
    }

    override fun finishActivity() {
        finish()
    }

    protected open fun initPresenter(): T {
        // 通过反射机制获取子类传递过来的实体类的类型信息
        val type = this.javaClass.genericSuperclass as ParameterizedType
        return (type.actualTypeArguments[0] as Class<T>).newInstance()
    }
}


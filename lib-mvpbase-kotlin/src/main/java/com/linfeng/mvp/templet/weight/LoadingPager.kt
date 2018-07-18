package com.linfeng.mvp.templet.weight

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.IdRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.linfeng.mvp.MVPManager


/**
 */
class LoadingPager : FrameLayout {
    private var mErrorPage: View? = null
    private var mLoadingPage: View? = null
    private var mEmptyPage: View? = null
    var emptyLayout: Int = 0
    var errorLayout: Int = 0
    var loadingLayout: Int = 0
    private var mSuccessPage: View? = null
    private var mCurrentState: Int = 0
    var isShowLoading: Boolean = false
        set(showLoading) {
            if (!showLoading) {
                removeView(mLoadingPage)
            } else {
                mLoadingPage = initPage(mLoadingPage, loadingLayout)
            }
            field = showLoading
        }

    var refreshListener: OnRefreshListener? = null

    var successPage: View?
        get() = mSuccessPage
        set(successPage) {
            if (successPage == null || mSuccessPage != null) {
                return
            }
            addView(successPage, 0, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            mSuccessPage = successPage
            initView()
        }

    constructor(context: Context) : super(context) {
//        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }


    fun setRefreshClick(@IdRes id: Int) {
        val viewById = findViewById<View>(id)
        viewById?.setOnClickListener { triggerInit() }
    }

    fun setViewClick(@IdRes id: Int, onClickListener: View.OnClickListener) {
        val viewById = findViewById<View>(id)
        viewById?.setOnClickListener(onClickListener)
    }

    /**
     * xml写法
     */
    override fun onFinishInflate() {
        super.onFinishInflate()
        if (mSuccessPage != null) {
            return
        }
        val childCount = childCount
        for (i in 0 until childCount) {
            val childAt = getChildAt(i)
            if (childAt === mEmptyPage || childAt === mLoadingPage || childAt === mErrorPage) {
                continue
            }
            mSuccessPage = childAt
        }
        refreshUIByState()
    }

    override fun addView(child: View?) {
        super.addView(child)
    }

    private fun initView() {
        if (isShowLoading) {
            mLoadingPage = initPage(mLoadingPage, loadingLayout)
        }
        mEmptyPage = initPage(mEmptyPage, emptyLayout)
        mErrorPage = initPage(mErrorPage, errorLayout)
        triggerInit()
    }

    private fun initPage(view: View?, layout: Int): View? {
        var view = view
        //如果已经添加则直接return
        if (indexOfChild(view) != -1) {
            return view
        }
        //如果未添加但view不为null.则添加
        if (view != null) {
            addView(view)
            return view
        }
        //如果未添加且为null,则创建并添加
        if (layout == 0) {
            view = View(context)
        } else {
            view = LayoutInflater.from(context).inflate(layout, this, false)
        }
        addView(view)
        return view
    }

    fun setEmptyPage(emptyPage: View) {
        removeView(mEmptyPage)
        mEmptyPage = emptyPage
        addView(mEmptyPage)
    }

    fun setErrorPage(errorPage: View) {
        removeView(mErrorPage)
        mErrorPage = errorPage
        addView(mErrorPage)
    }

    fun setLoadingPage(loadingPage: View) {
        removeView(mLoadingPage)
        mLoadingPage = loadingPage
        addView(mLoadingPage)
    }

    /**
     * 加载前刷新页面
     */
    private fun refreshUIByState() {
        if (this.isShowLoading) {
            mLoadingPage!!.visibility = if (mCurrentState == STATE_LOADING) View.VISIBLE else View.GONE
        }
        mErrorPage!!.visibility = if (mCurrentState == STATE_ERROR) View.VISIBLE else View.GONE
        mEmptyPage!!.visibility = if (mCurrentState == STATE_EMPTY) View.VISIBLE else View.GONE
    }

    /**
     * 刷新页面状态
     */
    fun triggerInit() {
        if (mCurrentState != STATE_LOADING) {
            mCurrentState = STATE_LOADING
            if (refreshListener != null) {
                refreshListener!!.onRefresh()
            }
            refreshUIByState()
        }
    }


    /**
     * 根据请求结果显示页面
     *
     * @param stateEmpty
     */
    fun show(stateEmpty: Int) {
        if (stateEmpty > STATE_SUCCESS || stateEmpty < STATE_EMPTY) {
            throw IllegalStateException("错误的状态")
        }
        mCurrentState = stateEmpty
        refreshUIByState()
    }

    fun showEmpty() {
        show(STATE_EMPTY)
    }

    fun showError(throwable: Throwable?) {
        if (throwable == null) {
            show(STATE_ERROR)
            return
        }
        val throwables = MVPManager.contentOptions.throwables
        if (throwables != null && throwables.indexOf(throwable.javaClass) != -1) {
            show(STATE_ERROR)
        }
    }

    fun showSuccess() {
        show(STATE_SUCCESS)
    }

    fun showLoading() {
        show(STATE_LOADING)
    }

    interface OnRefreshListener {
        fun onRefresh()
    }

    companion object {
        val STATE_EMPTY = 0
        val STATE_ERROR = 1
        val STATE_LOADING = 2
        val STATE_SUCCESS = 3
    }

}

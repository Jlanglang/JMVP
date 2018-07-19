//package com.linfeng.mvp.templet
//
//import android.view.View
//import com.linfeng.mvp.MVPManager
//import com.linfeng.mvp.presenter.BasePresenter
//import com.linfeng.mvp.templet.options.ContentOptions
//import com.linfeng.mvp.templet.weight.LoadingPager
//import com.linfeng.mvp.view.LoadView
//
//
///**
// * Created by baozi on 2017/12/20.
// */
//
//abstract class TemplateLoadingFragment<T : BasePresenter<*>> : TemplateFragment<T>(), LoadView {
//    var loadPager: LoadingPager? = null
//        private set
//
//    protected override val contentOptions: ContentOptions
//        get() = MVPManager.getContentOptions()
//
//    override fun wrapperContentView(view: View): View {
//        loadPager = contentOptions.buildLoadingPager(mContext, view)
//        loadPager!!.setRefreshListener(object : LoadingPager.OnRefreshListener() {
//            fun onRefresh() {
//                triggerInit()
//            }
//        })
//        return loadPager
//    }
//
//    fun showEmpty() {
//        loadPager!!.showEmpty()
//    }
//
//    @JvmOverloads
//    fun showError(throwable: Throwable, isShowError: Boolean = true) {
//        if (isShowError) {
//            loadPager!!.showError(throwable)
//        }
//        onNewThrowable(throwable)
//    }
//
//    fun showLoading() {
//        loadPager!!.showLoading()
//    }
//
//    fun showSuccess() {
//        loadPager!!.showSuccess()
//    }
//
//    fun triggerInit() {
//        mPresenter.onRefreshData()
//    }
//}

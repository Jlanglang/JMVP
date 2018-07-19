//package com.linfeng.mvp.presenter
//
//import android.support.v4.widget.SwipeRefreshLayout
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//
//
//import java.util.ArrayList
//
///**
// * Created by baozi on 2017/11/20.
// */
//abstract class LoadDataPresenter<D, T>(protected var loadMoreView: LoadMoreView, private val loadDataModel: LoadDataModel<T>) {
//
//    var loadType = LoadType.SINGLE
//
//    var pageNum = 1
//    var pageSize = 10
//    protected var lastVisibleIndex = 5
//    var minPageSize = 10
//        protected set
//    var isCanLoadMore = true
//        private set
//    var isLoading: Boolean = false
//    private var refreshLayout: SwipeRefreshLayout? = null
//
//    private val completeListeners = ArrayList<LoadCompleteListener<D>>()
//
//    enum class LoadType {
//        SINGLE, MIX
//    }
//
//    fun onCreate() {
//        initRefresh()
//        initRecyclerView()
//    }
//
//    protected fun initRecyclerView() {
//        val recyclerView = loadMoreView.getRecyclerView()
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
//                val itemCount = layoutManager.itemCount
//                val lastPosition = layoutManager.findLastVisibleItemPosition()
//                //如果当前不是正在加载更多，并且到了该加载更多的位置，加载更多。
//                if (lastPosition >= itemCount - lastVisibleIndex && itemCount >= minPageSize - lastVisibleIndex) {
//                    loadMore()
//                }
//            }
//        })
//    }
//
//
//    protected fun initRefresh() {
//        refreshLayout = loadMoreView.getSwipeRefreshLayout()
//        if (refreshLayout != null) {
//            refreshLayout!!.setOnRefreshListener(OnRefreshListener { this.refresh() })
//        }
//    }
//
//    fun loadMore() {
//        if (isLoading) {
//            return
//        }
//        isLoading = true
//        loadMoreCallBack(loadDataModel.loadData(pageNum + 1, pageSize, false))
//    }
//
//    fun refresh() {
//        isLoading = false
//        isCanLoadMore = true
//        if (refreshLayout != null) {
//            refreshLayout!!.isRefreshing = true
//        }
//        for (listener in completeListeners) {
//            listener.onRefreshStart()
//        }
//        refreshCallBack(loadDataModel.loadData(1, pageSize, true))
//    }
//
//
//    protected abstract fun loadMoreCallBack(t: T)
//
//    protected abstract fun refreshCallBack(t: T)
//
//    /**
//     * 请下拉刷新完成时调用
//     *
//     * @param list
//     */
//    fun refreshComplete(list: List<D>) {
//        if (loadType == LoadType.SINGLE && !list.isEmpty()) {
//            pageSize = list.size
//        }
//        pageNum = 1
//        if (refreshLayout != null) {
//            refreshLayout!!.isRefreshing = false
//        }
//        for (listener in completeListeners) {
//            val intercept = listener.doOnNext(true, list)
//            if (!intercept) {
//                listener.onNext(true, list)
//            }
//        }
//    }
//
//    /**
//     * 请加载更多完成时调用
//     *
//     * @param list
//     */
//    fun loadMoreComplete(list: List<D>) {
//        isLoading = false
//        if (checkCanLoadMore(list)) {
//            pageNum++
//        } else {
//            isCanLoadMore = false
//        }
//        for (listener in completeListeners) {
//            val intercept = listener.doOnNext(false, list)
//            if (!intercept) {
//                listener.onNext(false, list)
//            }
//        }
//    }
//
//
//    /**
//     * 请刷新失败时调用
//     */
//    fun refreshError(throwable: Throwable) {
//        if (refreshLayout != null) {
//            refreshLayout!!.isRefreshing = false
//        }
//        refreshComplete(emptyList<Any>())
//        for (listener in completeListeners) {
//            listener.onError(true, throwable)
//        }
//    }
//
//    /**
//     * 请加载失败时调用
//     */
//    fun loadMoreError(throwable: Throwable) {
//        loadMoreComplete(emptyList<Any>())
//        for (listener in completeListeners) {
//            listener.onError(false, throwable)
//        }
//    }
//
//
//    /**
//     * loadMore校验规则
//     *
//     * @return 可以加载更多返回true, 不能加载更多返回false
//     */
//    protected fun checkCanLoadMore(list: List<D>?): Boolean {
//        return list != null && !list.isEmpty() && list.size >= pageSize
//    }
//
//
//    fun addLoadCompleteListener(loadCompleteListener: LoadCompleteListener<D>) {
//        completeListeners.add(loadCompleteListener)
//    }
//
//    fun removeLoadCompleteListener(loadCompleteListener: LoadCompleteListener<D>) {
//        completeListeners.remove(loadCompleteListener)
//    }
//
//    /**
//     * 加载更多,刷新回调.
//     * 必须配合loadMoreComplete(),refreshComplete();
//     */
//    abstract class LoadCompleteListener<D> {
//        fun onRefreshStart() {}
//
//        fun onNext(isRefresh: Boolean, list: List<D>) {}
//
//        fun doOnNext(isRefresh: Boolean, list: List<D>): Boolean {
//            return false
//        }
//
//        fun onError(isRefresh: Boolean, throwable: Throwable) {
//
//        }
//    }
//
//    //倒数第几条开始加载更多
//    fun getLastVisibleIndex(): Int {
//        return 0
//    }
//
//    fun setLastVisibleIndex(lastVisibleIndex: Int) {
//        this.lastVisibleIndex = lastVisibleIndex
//    }
//
//    fun setMinPages(minPages: Int) {
//        this.minPageSize = minPages
//    }
//
//}

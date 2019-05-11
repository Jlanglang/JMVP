package com.baozi.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baozi.mvp.model.LoadDataModel;
import com.baozi.mvp.view.LoadMoreView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by baozi on 2017/11/20.
 */
public abstract class LoadMorePresenter<D, M> {
    private LoadDataModel<M> loadDataModel;
    protected LoadMoreView loadMoreView;

    protected int pageNum = 1;
    protected int pageSize = 10;
    protected int lastVisibleIndex = 5;
    protected int minPages = 10;


    private boolean isCanLoadMore = true;
    private boolean isLoading;
    private boolean isRefresh;
    private SwipeRefreshLayout refreshLayout;

    private List<LoadCompleteListener<D>> completeListeners = new ArrayList<>();

    public LoadMorePresenter(LoadMoreView loadMoreView, LoadDataModel<M> loadDataModel) {
        this.loadDataModel = loadDataModel;
        this.loadMoreView = loadMoreView;
    }

    public void onCreate() {
        initRefresh();
        initRecyclerView();
    }

    protected void initRecyclerView() {
        RecyclerView recyclerView = loadMoreView.getRecyclerView();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                //如果当前不是正在加载更多，并且到了该加载更多的位置，加载更多。
                if (lastPosition >= (itemCount - lastVisibleIndex) && itemCount >= (minPages - lastVisibleIndex)) {
                    loadMore();
                }
            }
        });
    }


    private void initRefresh() {
        refreshLayout = loadMoreView.getSwipeRefreshLayout();
        if (refreshLayout != null) {
            refreshLayout.setOnRefreshListener(this::refresh);
        }
    }

    public void loadMore() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        loadMoreCallBack(loadDataModel.loadData(pageNum + 1, pageSize, false));
    }

    public void refresh() {
        if (isRefresh) {
            return;
        }
        isLoading = false;
        isCanLoadMore = true;
        setRefresh(true);
        for (LoadCompleteListener<D> listener : completeListeners) {
            listener.onRefreshStart();
        }
        refreshCallBack(loadDataModel.loadData(1, pageSize, true));
    }


    protected abstract void loadMoreCallBack(M m);

    protected abstract void refreshCallBack(M m);

    /**
     * 请下拉刷新完成时调用
     *
     * @param list
     */
    public void refreshComplete(List<D> list) {
        pageNum = 1;
        setRefresh(false);
        boolean intercept = false;
        for (LoadCompleteListener<D> listener : completeListeners) {
            intercept = listener.doOnNext(true, intercept, list);
        }
        if (intercept) {
            return;
        }
        for (LoadCompleteListener<D> listener : completeListeners) {
            listener.onNext(true, list);
        }
    }

    /**
     * 请加载更多完成时调用
     *
     * @param list
     */
    public void loadMoreComplete(@NonNull List<D> list) {
        isLoading = false;
        if (checkCanLoadMore(list)) {
            pageNum++;
        } else {
            isCanLoadMore = false;
        }
        boolean intercept = false;
        for (LoadCompleteListener<D> listener : completeListeners) {
            intercept = listener.doOnNext(false, intercept, list);
        }
        if (intercept) {
            return;
        }
        for (LoadCompleteListener<D> listener : completeListeners) {
            listener.onNext(false, list);
        }
    }


    /**
     * 请刷新失败时调用
     */
    public void refreshError(Throwable throwable) {
        setRefresh(false);
        refreshComplete(Collections.emptyList());
        for (LoadCompleteListener<D> listener : completeListeners) {
            listener.onError(true, throwable);
        }
    }

    /**
     * 请加载失败时调用
     */
    public void loadMoreError(Throwable throwable) {
        loadMoreComplete(Collections.emptyList());
        for (LoadCompleteListener<D> listener : completeListeners) {
            listener.onError(false, throwable);
        }
    }


    /**
     * loadMore校验规则
     *
     * @return 可以加载更多返回true, 不能加载更多返回false
     */
    protected boolean checkCanLoadMore(List<D> list) {
        return list != null && !list.isEmpty() && list.size() >= pageSize;
    }


    public void addLoadCompleteListener(LoadCompleteListener<D> loadCompleteListener) {
        completeListeners.add(loadCompleteListener);
    }

    public void removeLoadCompleteListener(LoadCompleteListener<D> loadCompleteListener) {
        completeListeners.remove(loadCompleteListener);
    }

    /**
     * 加载更多,刷新回调.
     * 必须配合loadMoreComplete(),refreshComplete();
     */
    public static abstract class LoadCompleteListener<D> {
        public void onRefreshStart() {
        }

        public void onNext(boolean isRefresh, @NonNull List<D> list) {
        }

        /**
         * @param isRefresh 是否刷新
         * @param intercept 上一层监听的拦截结果
         * @param list      返回结果
         * @return
         */
        public boolean doOnNext(boolean isRefresh, boolean intercept, @NonNull List<D> list) {
            return intercept;
        }

        public void onError(boolean isRefresh, Throwable throwable) {

        }
    }

    //倒数第几条开始加载更多
    public int getLastVisibleIndex() {
        return 0;
    }

    public void setLastVisibleIndex(int lastVisibleIndex) {
        this.lastVisibleIndex = lastVisibleIndex;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMinPageSize() {
        return minPages;
    }

    public void setMinPages(int minPages) {
        this.minPages = minPages;
    }

    public boolean isCanLoadMore() {
        return isCanLoadMore;
    }

    protected void setCanLoadMore(boolean canLoadMore) {
        isCanLoadMore = canLoadMore;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(refresh);
        }
    }

    public boolean isRefrsh() {
        return isRefresh;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

}

package com.baozi.mvp.templet.weight;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.baozi.mvp.MVPManager;

import java.util.List;

/**
 */
public class LoadingPager extends FrameLayout {
    private Context context;
    private View mErrorPage;
    private View mLoadingPage;
    private View mEmptyPage;
    private int emptyLayout;
    private int errorLayout;
    private int loadingLayout;
    private View mSuccessPage;
    public static final int STATE_EMPTY = 0;
    public static final int STATE_ERROR = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_SUCCESS = 3;
    private int mCurrentState;
    private boolean isShowLoading;

    private OnRefreshListener refreshListener;

    public LoadingPager(Context context) {
        super(context);
        this.context = context;
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public View getSuccessPage() {
        return mSuccessPage;
    }

    public void setSuccessPage(View successPage) {
        if (successPage == null || mSuccessPage != null) {
            return;
        }
        addView(successPage, 0, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mSuccessPage = successPage;
        initView();
    }


    public void setRefreshClick(@IdRes int id) {
        View viewById = findViewById(id);
        if (viewById != null) {
            viewById.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    triggerInit();
                }
            });
        }
    }

    public void setViewClick(@IdRes int id, OnClickListener onClickListener) {
        View viewById = findViewById(id);
        if (viewById != null) {
            viewById.setOnClickListener(onClickListener);
        }
    }

    /**
     * xml写法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mSuccessPage != null) {
            return;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt == mEmptyPage || childAt == mLoadingPage || childAt == mErrorPage) {
                continue;
            }
            mSuccessPage = childAt;
        }
        refreshUIByState();
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

    private void initView() {
        if (isShowLoading()) {
            mLoadingPage = initPage(mLoadingPage, loadingLayout);
        }
        mEmptyPage = initPage(mEmptyPage, emptyLayout);
        mErrorPage = initPage(mErrorPage, errorLayout);
        triggerInit();
    }

    private View initPage(View view, int layout) {
        //如果已经添加则直接return
        if (indexOfChild(view) != -1) {
            return view;
        }
        //如果未添加但view不为null.则添加
        if (view != null) {
            addView(view);
            return view;
        }
        //如果未添加且为null,则创建并添加
        if (layout == 0) {
            view = new View(context);
        } else {
            view = LayoutInflater.from(context).inflate(layout, this, false);
        }
        addView(view);
        return view;
    }

    public boolean isShowLoading() {
        return isShowLoading;
    }

    public void setShowLoading(boolean showLoading) {
        if (!showLoading) {
            removeView(mLoadingPage);
        } else {
            mLoadingPage = initPage(mLoadingPage, loadingLayout);
        }
        isShowLoading = showLoading;
    }

    public void setEmptyPage(View emptyPage) {
        removeView(mEmptyPage);
        mEmptyPage = emptyPage;
        addView(mEmptyPage);
    }

    public void setErrorPage(View errorPage) {
        removeView(mErrorPage);
        mErrorPage = errorPage;
        addView(mErrorPage);
    }

    public void setLoadingPage(View loadingPage) {
        removeView(mLoadingPage);
        mLoadingPage = loadingPage;
        addView(mLoadingPage);
    }

    public void setEmptyLayout(int emptyLayout) {
        this.emptyLayout = emptyLayout;
    }

    public void setErrorLayout(int errorLayout) {
        this.errorLayout = errorLayout;
    }

    public void setLoadingLayout(int loadingLayout) {
        this.loadingLayout = loadingLayout;
    }

    public int getErrorLayout() {
        return errorLayout;
    }

    public int getLoadingLayout() {
        return loadingLayout;
    }

    public int getEmptyLayout() {
        return emptyLayout;
    }

    public OnRefreshListener getRefreshListener() {
        return refreshListener;
    }

    public void setRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    /**
     * 加载前刷新页面
     */
    private void refreshUIByState() {
        if (isShowLoading) {
            mLoadingPage.setVisibility(mCurrentState == STATE_LOADING ? View.VISIBLE : View.GONE);
        }
        mErrorPage.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
        mEmptyPage.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);
    }

    /**
     * 刷新页面状态
     */
    public void triggerInit() {
        if (mCurrentState != STATE_LOADING) {
            mCurrentState = STATE_LOADING;
            if (refreshListener != null) {
                refreshListener.onRefresh();
            }
            refreshUIByState();
        }
    }


    /**
     * 根据请求结果显示页面
     *
     * @param stateEmpty
     */
    public void show(int stateEmpty) {
        if (stateEmpty > STATE_SUCCESS || stateEmpty < STATE_EMPTY) {
            throw new IllegalStateException("错误的状态");
        }
        mCurrentState = stateEmpty;
        refreshUIByState();
    }

    public void showEmpty() {
        show(STATE_EMPTY);
    }

    public void showError(Throwable throwable) {
        if (throwable == null) {
            show(STATE_ERROR);
            return;
        }
        List<Class> throwables = MVPManager.getContentOptions().getThrowables();
        if (throwables != null && throwables.indexOf(throwable.getClass()) != -1) {
            show(STATE_ERROR);
        }
    }

    public void showSuccess() {
        show(STATE_SUCCESS);
    }

    public void showLoading() {
        show(STATE_LOADING);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

}

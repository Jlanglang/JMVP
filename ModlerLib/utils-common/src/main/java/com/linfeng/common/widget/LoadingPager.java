package com.linfeng.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.linfeng.common.utils.HLog;


public class LoadingPager extends FrameLayout {
    private LoadingInterface mLoadingInterface;
    private Context context;
    private View mErrorPage;
    private View mLoadingPage;
    private View mSuccessPage;
    private View mEmptyPage;
    public static final int STATE_DEFAULT = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    private int mCurrentState;

    public View getSuccessPage() {
        return mSuccessPage;
    }

    public void setSuccessPage(View successPage) {
        if (successPage == null || mSuccessPage != null) {
            return;
        }
        mSuccessPage = successPage;
        if (getChildCount() < 4) {
            addView(mSuccessPage);
        }
    }


    public LoadingPager(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setLoadingInterface(LoadingInterface loadingInterface) {
        mLoadingInterface = loadingInterface;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (mSuccessPage != null) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt == mEmptyPage || childAt == mLoadingPage || childAt == mErrorPage) {
                continue;
            }
            mSuccessPage = childAt;
        }
        HLog.i("loadpage", "1");
        refreshUIByState();
    }

    @Override
    public void addView(View child) {
        if (getChildCount() > 4) {//不能有2个mSuccessPage
            throw new IllegalStateException("LoadingPager can host only one direct child");
        }
        super.addView(child);
    }

    private void initView() {
//        mEmptyPage = View.inflate(context, R.layout.fragment_search_empty, null);
//        mLoadingPage = View.inflate(context, R.layout.fragment_search_loading, null);
//        mErrorPage = View.inflate(context, R.layout.fragment_search_error, null);
//        addView(mEmptyPage);
//        addView(mErrorPage);
//        addView(mLoadingPage);
//        mErrorPage.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                triggerInit();
//            }
//        });
    }

    /**
     * 加载前刷新页面
     */
    private void refreshUIByState() {
        mLoadingPage.setVisibility((mCurrentState == STATE_LOADING || mCurrentState == STATE_DEFAULT) ? View.VISIBLE : View.GONE);
        mErrorPage.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
        mEmptyPage.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);
        if (mSuccessPage != null && getChildCount() <= 4) {
            mSuccessPage.setVisibility(mCurrentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 刷新页面以及触发加载数据
     */
    public void triggerInit() {
        if (mLoadingInterface != null) {
            if (mCurrentState != STATE_LOADING) {
                mCurrentState = STATE_LOADING;
                refreshUIByState();
                mLoadingInterface.loadingData();
                HLog.i("loadpage", "3");
            }
        } else {
            show(STATE_SUCCESS);
        }
    }

    /**
     * 根据请求结果显示页面
     *
     * @param stateEmpty
     */
    public void show(int stateEmpty) {
        if (stateEmpty>STATE_SUCCESS||stateEmpty<STATE_DEFAULT){
            throw new IllegalStateException("错误的状态");
        }
        mCurrentState = stateEmpty;
        refreshUIByState();
    }
    /**
     * @author jlanglang  2016/10/26 11:13
     * @版本 2.0
     * @Change
     * @des ${TODO}
     */
    public interface LoadingInterface {
        void loadingData();
    }
}

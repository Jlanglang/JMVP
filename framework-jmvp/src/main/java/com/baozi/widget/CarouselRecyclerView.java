package com.baozi.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by baozi on 2017/6/14.
 */

public class CarouselRecyclerView extends FrameLayout {
    private Context mContext;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private CompositeDisposable mSubscribe = new CompositeDisposable();
    private ItemChangeCallBack mItemChangeCallBack;
    private ViewGroup mIndicator;

    public CarouselRecyclerView(Context context) {
        super(context);
    }

    public CarouselRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarouselRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                stop();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                start();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if (visibility == View.GONE) {
            // 停止轮播
            stop();
        } else if (visibility == View.VISIBLE) {
            // 开始轮播
            start();
        }
        super.onWindowVisibilityChanged(visibility);
    }

    private void initRecyclerView() {
        mRecyclerView = new RecyclerView(mContext);
        mRecyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                640));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter);
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);

        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mRecyclerView, mRecyclerView.getLayoutParams());
        addView(mIndicator);
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void setItemChangeCallBack(ItemChangeCallBack itemChangeCallBack) {
        mItemChangeCallBack = itemChangeCallBack;
    }

    public void setIndicator(ViewGroup indicator) {
        mIndicator = indicator;
    }

    public interface ItemChangeCallBack {
        void change(ViewGroup indicator, int index);
    }

    public void start() {
//        if (!mSubscribe.isDisposed()) {
        Disposable subscribe = Observable.interval(2, TimeUnit.SECONDS)  // 5s的延迟，5s的循环时间
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        // 进行轮播操作
                        //向后翻动一页
                        LinearLayoutManager linearManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                        //获取第一个可见view的位置
                        int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                        int newIndex = ++firstItemPosition;
                        Log.i("index", newIndex + "");
                        if (firstItemPosition == mAdapter.getItemCount()) {
                            newIndex = 0;
                        }
                        mRecyclerView.smoothScrollToPosition(newIndex);
                        mItemChangeCallBack.change(mIndicator, newIndex);
                    }
                });
        mSubscribe.add(subscribe);
//        }
    }

    private void stop() {
        if (!mSubscribe.isDisposed()) {
            mSubscribe.clear();
        }
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    public static class Builder {
        private Context mContext;
        private ViewGroup mIndicator;
        private RecyclerView.Adapter mAdapter;
        private CarouselRecyclerView mCarouselRecyclerView;

        private ItemChangeCallBack mItemChangeCallBack;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setItemChangeCallBack(ItemChangeCallBack itemChangeCallBack) {
            mItemChangeCallBack = itemChangeCallBack;
            return this;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            mAdapter = adapter;
            return this;
        }

        public Builder setIndicator(LinearLayout indicator) {
            mIndicator = indicator;
            return this;
        }

        public CarouselRecyclerView create() {
            if (mCarouselRecyclerView == null) {
                mCarouselRecyclerView = new CarouselRecyclerView(mContext);
                if (mAdapter != null) {
                    mCarouselRecyclerView.setAdapter(mAdapter);
                }
                if (mIndicator == null) {
                    mIndicator = new LinearLayout(mContext);
                }
                mCarouselRecyclerView.setIndicator(mIndicator);
                if (mItemChangeCallBack != null) {
                    mCarouselRecyclerView.setItemChangeCallBack(mItemChangeCallBack);
                }
                mCarouselRecyclerView.setContext(mContext);
                mCarouselRecyclerView.initRecyclerView();
            }
            return mCarouselRecyclerView;
        }
    }

    private class CarouselWapper<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

        private RecyclerView.Adapter<VH> mAdapter;

        public CarouselWapper(RecyclerView.Adapter<VH> adapter) {
            mAdapter = adapter;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            mAdapter.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }
    }
}

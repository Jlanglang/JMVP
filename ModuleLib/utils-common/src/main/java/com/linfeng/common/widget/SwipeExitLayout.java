package com.linfeng.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


/**
 * @author jlanglang  2016/11/3 10:46
 * @版本 2.0
 * @Change
 * @des ${TODO}
 */
public class SwipeExitLayout extends LinearLayout {

    private final Context context;
    private ViewDragHelper mViewDragHelper;
    private View mDragView;
    private Point mAutoBackOriginPos = new Point();
    private ScrollListener mScrollListener;

    public ScrollListener getScrollListener() {
        return mScrollListener;
    }

    /**
     * 如果需要滑动后退出,需要设置该监听
     * @param scrollListener
     */
    public void setScrollListener(ScrollListener scrollListener) {
        mScrollListener = scrollListener;
    }

    //用于记录正常的布局位置
    public SwipeExitLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mViewDragHelper = ViewDragHelper.create(this, new myCallBack());
        setBackgroundColor(Color.argb(128,0,0,0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mDragView == null) return;
        //ScrollView中的唯一子控件的位置信息, 这个位置信息在整个控件的生命周期中保持不变,用来取消退出后回弹到原位
        mAutoBackOriginPos.x = mDragView.getLeft();
        mAutoBackOriginPos.y = mDragView.getTop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>1){
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        mDragView = getChildAt(0);
    }

    class myCallBack extends ViewDragHelper.Callback {

        private int mDx1;

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragView;
        }

        /**
         * 控制水平滑动的最大值
         *
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            final int leftBound = getPaddingLeft();
            final int minLeftBound = -leftBound;
            final int newLeft = Math.min(Math.max(minLeftBound, left), getMeasuredWidth());//水平可以滑动整个view的宽度,所以直接设置宽度就行了
            Log.i("PositionHorizontal", newLeft + "");
            int min = 128-(int)(128*(newLeft/(float)getMeasuredWidth()));
            setBackgroundColor(Color.argb(min,0,0,0));
            return newLeft;
        }

        @Override
        public int getOrderedChildIndex(int index) {
            return super.getOrderedChildIndex(index);
        }

        /**
         * 控制垂直滑动的边界坐标
         *
         * @param child
         * @param top
         * @param dy
         * @return
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottonBound = getHeight() - mDragView.getHeight() - topBound;
            final int newTop = Math.min(Math.max(top, topBound), bottonBound);//不让垂直滑动
            return newTop;
        }
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mDx1 = left;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //手指释放时可以自动回去
            if (releasedChild == mDragView) {
                //一定得重写computeScroll()方法，不然没有效果
                //如果移动的距离大于或等于当前界面的1/5，则触发关闭
                if (mDx1 >= (getMeasuredWidth() / 5)) {
                    if (mScrollListener!=null){
                        mScrollListener.onScrollExit();
                    }
                } else {
                    //设置滑动的View移动位置，即恢复原来的位置
                    mViewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate();
                }
            }
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return getMeasuredWidth();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getX()>(getMeasuredWidth() / 5)){//触摸到的值大于指定的值让ViewDragHelper去处理
            return false;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
    public interface ScrollListener {
        public void onScrollExit();
    }
}

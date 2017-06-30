//package com.linfeng.common.widget;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.os.Build;
//import android.support.v7.widget.LinearLayoutCompat;
//import android.util.AttributeSet;
//import android.view.ViewGroup;
//
//import com.zhy.autolayout.AutoLayoutInfo;
//import com.zhy.autolayout.utils.AutoLayoutHelper;
//
///**
// * @author jlanglang  2016/12/1 10:42
// * @版本 2.0
// * @Change
// * @des ${TODO}
// */
//
//public class AutoLinearLayoutCompat extends LinearLayoutCompat {
//    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
//
//    public AutoLinearLayoutCompat(Context context) {
//        super(context);
//    }
//
//    public AutoLinearLayoutCompat(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public AutoLinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        if (!isInEditMode())
//            mHelper.adjustChildren();
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b)
//    {
//        super.onLayout(changed, l, t, r, b);
//    }
//
//
//    @Override
//    public AutoLinearLayoutCompat.LayoutParams generateLayoutParams(AttributeSet attrs)
//    {
//        return new LayoutParams(getContext(), attrs);
//    }
//
//
//    public static class LayoutParams extends LinearLayoutCompat.LayoutParams
//            implements AutoLayoutHelper.AutoLayoutParams
//    {
//        private AutoLayoutInfo mAutoLayoutInfo;
//
//        public LayoutParams(Context c, AttributeSet attrs)
//        {
//            super(c, attrs);
//            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
//        }
//
//        @Override
//        public AutoLayoutInfo getAutoLayoutInfo()
//        {
//            return mAutoLayoutInfo;
//        }
//
//
//        public LayoutParams(int width, int height)
//        {
//            super(width, height);
//        }
//
//
//        public LayoutParams(ViewGroup.LayoutParams source)
//        {
//            super(source);
//        }
//
//        public LayoutParams(MarginLayoutParams source)
//        {
//            super(source);
//        }
//
//    }
//
//}

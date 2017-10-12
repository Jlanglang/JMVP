package com.linfeng.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/5/13.
 */
public class MyMarqueeTextView extends AppCompatTextView implements Runnable {
    public int currentScrollX;// 当前滚动的位置
    public static int SPEED = 2;
    private boolean isStop = false;
    private int textWidth;
    private boolean isMeasure = false;

    public MyMarqueeTextView(Context context) {
        super(context);
    }

    public MyMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isMeasure) {
            getTextWidth();
            isMeasure = true;
        }
    }

    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        this.isMeasure = false;
    }

    @Override
    public void run() {
        currentScrollX += SPEED;// 滚动速度
        scrollTo(currentScrollX, 0);
        if (isStop) {
            return;
        }
        if (getScrollX() >= textWidth) {
            scrollTo(-this.getWidth(), 0);
            currentScrollX = -this.getWidth();
        }
        postDelayed(this, 10);
    }

    public void startScroll() {
        isStop = false;
        this.removeCallbacks(this);
        post(this);
    }

    public void stopScroll() {
        currentScrollX = 0;
        isStop = true;
    }

    public void startFor0() {
        currentScrollX = 0;
        startScroll();
    }
}

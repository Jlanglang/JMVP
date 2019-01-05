package com.baozi.homemodle.ui.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("onLayout", System.currentTimeMillis() + "");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("onMeasure", System.currentTimeMillis() + "");
    }

    Paint paint = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    Paint paint4 = new Paint();

    {
        paint.setColor(Color.RED);
        paint2.setColor(Color.WHITE);

        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(20);
        paint3.setColor(Color.BLACK);

        paint4.setAntiAlias(true);
        paint4.setStrokeWidth(20);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX, centerY;
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        int width = 400;
        RectF rectF = new RectF(centerX - width, centerY - width, centerX + width, centerY + width);
        int width2 = 300;
        RectF rectF2 = new RectF(centerX - width2, centerY - width2, centerX + width2, centerY + width2);
        int width3 = 200;
        RectF rectF3 = new RectF(centerX - width3, centerY - width3, centerX + width3, centerY + width3);
        int width4 = 100;
        RectF rectF4 = new RectF(centerX - width4, centerY - width4, centerX + width4, centerY + width4);
        canvas.drawRoundRect(rectF, width, width, paint);
        canvas.drawRoundRect(rectF2, width2, width2, paint2);
        for (int i = 0; i <= 360; i++) {
            if (i % 5 == 0) {
                if (i / 5 % 2 == 0) {
                    canvas.drawArc(rectF3, i, 1f, false, paint3);
                } else {
                    canvas.drawArc(rectF3, i, 1f, false, paint4);
                }
            }
        }
        canvas.drawRoundRect(rectF4, width, width, paint);
        Log.i("ondraw", canvas.getClass().getName());
        canvas.drawCircle(moveX, moveY, 50f, paint3);
    }

    float moveX, moveY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                moveY = event.getY();
                invalidate();
                return true;

        }
        return super.onTouchEvent(event);
    }
}

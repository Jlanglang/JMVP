package com.linfeng.common.utils;

import android.view.View;

import java.util.Calendar;

/**
 * 防止多次点击的onclicklistener
 * Created by 123456 on 2016-1-12.
 */
public abstract class IntervalOnClickListener implements View.OnClickListener {
    private static final int DEFAULT_CLICK_DELAY_TIME = 1000;
    private int min_click_delay_time;
    private long lastClickTime = 0;

    public IntervalOnClickListener() {
        min_click_delay_time = DEFAULT_CLICK_DELAY_TIME;
    }

    public IntervalOnClickListener(int time) {
        this.min_click_delay_time = time;
    }

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > min_click_delay_time) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    /**
     * 点击事就会调用onClick方法,将这个方法抽象化,相当于,自定义了onClickListener
     *
     * @param v
     */
    protected abstract void onNoDoubleClick(View v);


}

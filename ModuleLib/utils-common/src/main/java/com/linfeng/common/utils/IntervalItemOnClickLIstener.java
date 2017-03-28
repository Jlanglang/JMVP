package com.linfeng.common.utils;

import android.view.View;
import android.widget.AdapterView;

import java.util.Calendar;

/**
 * 适用于listview,gridview条目点击事件
 */
public abstract class IntervalItemOnClickLIstener implements AdapterView.OnItemClickListener {
    private static final int DEFAULT_CLICK_DELAY_TIME = 1000;
    private int min_click_delay_time;
    private long lastClickTime = 0;

    public IntervalItemOnClickLIstener() {
        this.min_click_delay_time = DEFAULT_CLICK_DELAY_TIME;
    }

    public IntervalItemOnClickLIstener(int time) {
        this.min_click_delay_time = time;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > min_click_delay_time) {
            lastClickTime = currentTime;
            onNoDoubleItemClick(parent, view, position, id);
        }
    }

    protected abstract void onNoDoubleItemClick(AdapterView<?> parent, View view, int position, long id);
}

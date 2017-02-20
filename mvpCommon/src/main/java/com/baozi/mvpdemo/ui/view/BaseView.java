package com.baozi.mvpdemo.ui.view;

import android.content.Context;
import android.view.View;

/**
 * Created by baozi on 2016/11/24.
 */
public interface BaseView {
    /**
     * 切换夜间模式
     *
     * @param isNight 是否切换为夜间模式
     */
    void isNightMode(boolean isNight);

    Context getContext();

    <V extends View> V findView(int viewId);
}

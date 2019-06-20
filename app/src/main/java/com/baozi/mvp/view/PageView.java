package com.baozi.mvp.view;

import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public interface PageView<T> extends BaseView {

    ViewPager getViewPage();

    List<T> getPage();

    /**
     * 是否需要切换动画
     *
     * @return
     */
    boolean isAnimation();
}

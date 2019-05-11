package com.baozi.mvp.view;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public interface PagerView extends BaseView {

    ViewPager getViewPager();

    List<View> getPager();

    TabLayout getTablayout();

    String[] getTabString();

    @DrawableRes
    int[] getTabDrawables();

    @LayoutRes
    int getTabLayoutItem();

    /**
     * 是否需要切换动画
     *
     * @return
     */
    boolean isAnimation();
}

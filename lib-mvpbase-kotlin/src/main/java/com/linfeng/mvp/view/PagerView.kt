package com.linfeng.mvp.view

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by Administrator on 2017/8/8 0008.
 */

interface PagerView : BaseView {

    val viewPager: ViewPager

    val pager: List<View>

    val tablayout: TabLayout?

    val tabString: Array<String>?

    @get:DrawableRes
    val tabDrawables: IntArray?

    @get:LayoutRes
    val tabLayoutItem: Int?

    /**
     * 是否需要切换动画
     *
     * @return
     */
    val isAnimation: Boolean
}

package com.linfeng.mvp.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager

/**
 * Created by Administrator on 2017/8/8 0008.
 */

interface PagerFragmentView : PagerView {

    override val viewPager: ViewPager

    val fgManager: FragmentManager

    val fragments: Array<Class<Fragment>>

}

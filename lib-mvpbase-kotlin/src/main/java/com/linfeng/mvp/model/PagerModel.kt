package com.linfeng.mvp.model

import android.support.annotation.DrawableRes

/**
 * Created by Administrator on 2017/8/16 0016.
 */

interface PagerModel {

    val fragments: Array<Class<*>>

    val tabString: Array<String>

    @get:DrawableRes
    val tabDrawables: IntArray

    val isAnimation: Boolean
}

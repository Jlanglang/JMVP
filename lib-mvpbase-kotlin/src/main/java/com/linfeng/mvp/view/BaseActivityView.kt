package com.linfeng.mvp.view

import android.content.Intent

/**
 * @author jlanglang  2017/7/7 16:39
 * @版本 2.0
 * @Change
 */
interface BaseActivityView : UIView {
    fun getIntent(): Intent
}
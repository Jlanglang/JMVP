package com.baozi.kotlindemo.model

import com.linfeng.mvp.view.BaseActivityView
import com.linfeng.mvp.view.UIView

/**
 * Created by baozi on 2017/10/20.
 */
interface DemoView : BaseActivityView, UIView {
    fun A()
    fun B()
}
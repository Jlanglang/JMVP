package com.linfeng.mvp

import android.support.annotation.AnimRes
import android.support.annotation.AnimatorRes
import android.support.annotation.StyleRes
import com.linfeng.mvp.templet.options.ContentOptions
import com.linfeng.mvp.templet.options.ToolbarOptions

/**
 * Created by Administrator on 2017/8/15 0015.
 */

object MVPManager {
    val toolbarOptions: ToolbarOptions = ToolbarOptions.Create()
        get() = field.clone()
    val contentOptions: ContentOptions = ContentOptions.create()
        get() = field.clone()
    @AnimRes
    @AnimatorRes
    var enterAnim: Int = 0
        private set
    @AnimRes
    @AnimatorRes
    var exitAnim: Int = 0
        private set
    @AnimRes
    @AnimatorRes
    var enterPopAnim: Int = 0
        private set
    @AnimRes
    @AnimatorRes
    var exitPopAnim: Int = 0
        private set
    var transactionStyle: Int = 0
        private set

    fun setFragmentAnim(@AnimatorRes @AnimRes enter: Int,
                        @AnimatorRes @AnimRes exit: Int,
                        @AnimatorRes @AnimRes popEnter: Int,
                        @AnimatorRes @AnimRes popExit: Int) {
        enterAnim = enter
        exitAnim = exit
        enterPopAnim = popEnter
        exitPopAnim = popExit
    }

    fun setFragmentTransaction(@StyleRes styleRes: Int) {
        transactionStyle = styleRes
    }

}

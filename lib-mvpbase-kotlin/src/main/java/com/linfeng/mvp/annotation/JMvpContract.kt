package com.linfeng.mvp.annotation

import android.support.annotation.LayoutRes

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class JMvpContract(
        @LayoutRes val layout: Int = 0
)
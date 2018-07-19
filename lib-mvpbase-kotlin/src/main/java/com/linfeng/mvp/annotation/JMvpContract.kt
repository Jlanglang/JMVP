package com.linfeng.mvp.annotation

import android.support.annotation.LayoutRes
import java.lang.annotation.Inherited

@Inherited
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class JMvpContract(
        @LayoutRes val layout: Int = 0
)
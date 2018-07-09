package com.linfeng.mvp.animation

import android.support.annotation.IntDef
import android.support.annotation.LayoutRes
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class JMvpContract(
        @LayoutRes val layout: Int = 0,
        val p: KClass<*>
)
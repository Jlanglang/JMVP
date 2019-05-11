package com.linfeng.demo

import android.content.Context

class Toast(private val context: Context, val str: String, valtoastLong: Int) {
    fun show() {
        //弹出dialog
        context.also {

        }.apply {

        }.apply {

        }
    }

    companion object {
        fun makeText(context: Context, str: String, toastLong: Int) =
                Toast(context, str, toastLong)
    }
}

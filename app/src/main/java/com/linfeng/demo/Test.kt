package com.linfeng.demo

import com.linfeng.common.utils.HLog
import java.util.*

/**
 * Created by baozi on 2017/6/14.
 */
data class Test(var data: Any) {
//    var data: Any = Any()
    fun test() {
        if (data == null) {
            data = Any()
        }
        var a = data
//        a?.add(Any())
        HLog.i("a", a.toString())
        HLog.i("data", data.toString())
        data = Any()
        HLog.i("data", data.toString())
    }
}
package com.linfeng.mvp.factory

import android.support.v4.app.Fragment

/**
 * Created by Administrator on 2017/8/8 0008.
 */

object FragmentFactory {
    fun getFragment(zClass: Class<Fragment>): Fragment? {
        try {
            return zClass.newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return null
    }
}

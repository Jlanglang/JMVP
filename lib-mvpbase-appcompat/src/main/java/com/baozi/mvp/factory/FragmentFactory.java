package com.baozi.mvp.factory;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class FragmentFactory {
    public static Fragment getFragment(Class<Fragment> zClass) {
        try {
            return zClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

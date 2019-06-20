package com.baozi.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public interface PageFragmentView extends PageView<Fragment> {
    FragmentManager getFgManager();
}

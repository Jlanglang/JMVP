package com.baozi.mvp.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public interface PageFragmentView extends PageView<Fragment> {
    FragmentManager getFgManager();
}

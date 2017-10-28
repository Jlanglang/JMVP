package com.baozi.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public interface PagerFragmentView extends PagerView {

    ViewPager getViewPager();

    FragmentManager getFgManager();

    Class<Fragment>[] getFragments();

}

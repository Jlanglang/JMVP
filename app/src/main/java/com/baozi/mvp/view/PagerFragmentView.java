package com.baozi.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public interface PagerFragmentView extends PagerView {

    FragmentManager getFgManager();

    Class<? extends Fragment>[] getFragments();
}

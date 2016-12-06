package com.baozi.mvpdemp.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.baozi.mvpdemp.base.BaseFragment;

/**
 * Created by baozi on 2016/11/24.
 */
public interface BaseFragmentView extends BaseView {
    FragmentManager getFragmentManager();

    Context getContext();

    Bundle getBundle();

    void startFragment(Fragment tofragment);

    void startFragment(Fragment tofragment, String tag);

    BaseFragment getFragment();
}

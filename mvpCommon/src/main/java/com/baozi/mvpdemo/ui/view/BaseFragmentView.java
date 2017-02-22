package com.baozi.mvpdemo.ui.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.baozi.mvpdemo.base.BaseFragment;

/**
 * Created by baozi on 2016/11/24.
 */
public interface BaseFragmentView extends UIView {
    FragmentManager getFragmentManager();

    Bundle getBundle();

    BaseFragment getFragment();
}

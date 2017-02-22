package com.baozi.mvpdemo.ui.view;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Window;

import com.baozi.mvpdemo.base.BaseActivity;

/**
 * Created by baozi on 2016/11/24.
 */
public interface BaseActivityView extends UIView {
    FragmentManager getFragmentManager();

    android.support.v4.app.FragmentManager getSupportFragmentManager();

    Window getWindow();

    void startFragment(Fragment tofragment);

    void startFragment(Fragment tofragment, String tag);

    BaseActivity getActivity();

    Intent getIntent();
}

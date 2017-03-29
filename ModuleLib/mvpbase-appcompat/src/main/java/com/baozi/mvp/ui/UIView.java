package com.baozi.mvp.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

/**
 * Created by baozi on 2017/2/20.
 * 用户页面,操作页面，对应Activity,frgament...
 */

public interface UIView extends BaseView {


    /**
     * res资源获取
     *
     * @return
     */
    Resources getResources();

    /**
     * 回退
     */
    void onBack();

    /**
     * 获取Acitivity
     *
     * @return
     */
    Activity getActivity();

    /**
     * 主要视图View
     *
     * @return
     */
    View getContentView();

    /**
     * Frgament跳转.
     *
     * @param tofragment
     */
    void startFragment(Fragment tofragment);

    /**
     * Frgament跳转.
     *
     * @param tofragment
     */
    void startFragment(Fragment tofragment, String tag);

    FragmentManager getFragmentManager();

    Window getWindow();

    ActionBar getSupportActionBar();

    void setSupportActionBar(@Nullable Toolbar toolbar);
}

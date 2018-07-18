package com.baozi.homemodle.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.frame.JBasePresenter;
import com.baozi.homemodle.R;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.templet.TempletActivity;
import com.linfeng.common.utils.AutoUtils;


public class MainActivity extends TempletActivity<BasePresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AutoUtils.init(this,false,720,1280);
        AutoUtils.auto(this);
    }

    @Override
    protected int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_main;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new JBasePresenter() {
            @Override
            public void onCreate() {
                getSupportActionBar().setTitle("首页");
                getToolbarHelper().setScrollFlag(R.id.collapsing_layout, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getToolbarHelper().findViewFromAppBar(R.id.tab_layout).setBackgroundResource(R.color.home_colorPrimary);

            }

            @Override
            public void initData() {

            }
        };
    }

}

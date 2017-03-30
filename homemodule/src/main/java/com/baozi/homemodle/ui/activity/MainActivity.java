package com.baozi.homemodle.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.homemodle.R;
import com.baozi.mvp.base.TempletActivity;
import com.baozi.mvp.helper.ToolbarHelper;
import com.baozi.mvp.presenter.BasePresenter;
import com.zhy.autolayout.utils.AutoUtils;


public class MainActivity extends TempletActivity<BasePresenter> {

    @NonNull
    @Override
    public View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_activity_main, null);
        AutoUtils.auto(inflate);
        return inflate;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter() {
            @Override
            public void onCreate() {
                getSupportActionBar().setTitle("首页");
                getToolbarHelper().setScrollFlag(R.id.collapsing_layout, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getToolbarHelper().findViewFromAppBar(R.id.tab_layout).setBackgroundResource(R.color.home_colorPrimary);

            }

            @Override
            public void loadData() {
            }

            @Override
            public void cancleNetWork() {

            }
        };
    }

    @Override
    public int initToolbarLayout() {
        return ToolbarHelper.TOOLBAR_MD_TABLAYOUT;
    }
}

package com.linfeng.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.base.TempletActivity;
import com.baozi.mvpdemo.helper.ToolbarHelper;
import com.baozi.mvpdemo.presenter.TempletPresenter;
import com.baozi.mvpdemo.ui.view.ToolbarView;

public class MainAcitivity extends TempletActivity {


    @NonNull
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getToolbarHelper().setTitle("首页");
        getToolbarHelper().setRightText("213", null);
//        setMaterialDesignEnabled(true);
        return inflater.inflate(R.layout.activity_main, null);
    }


    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean isMaterialDesign() {
        return true;
    }

    @Override
    protected TempletPresenter initPresenter() {
        return new TempletPresenter<ToolbarView>() {
            @Override
            public void onCreate() {
//                ViewGroup.LayoutParams layoutParams = getToolbarHelper().getAppBarLayout().getLayoutParams();
//                layoutParams.height = 400;
                getToolbarHelper().setScrollFlag(R.id.tl_costom, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            }

            @Override
            public void loadData() {

            }
        };
    }

    @Override
    public int initToolbarLayout() {
        return ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL;
    }
}

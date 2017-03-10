package com.linfeng.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.jmvp.base.TempletActivity;
import com.baozi.jmvp.helper.ToolbarHelper;
import com.baozi.jmvp.presenter.TempletPresenter;
import com.baozi.jmvp.ui.ToolbarView;

public class DefatulAcitivity extends TempletActivity {


    @NonNull
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setMaterialDesignEnabled(true);
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
    protected TempletPresenter initPresenter() {
        return new TempletPresenter<ToolbarView>() {
            @Override
            public void onCreate() {
                mView.getToolbarHelper().setTitle("首页");
                mView.getToolbarHelper().setRightText("213", null);
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
    public boolean isMaterialDesign() {
        return true;
    }

    @Override
    public int initToolbarLayout() {
        return ToolbarHelper.TOOLBAR_MD_COLLAPSING;
    }
}

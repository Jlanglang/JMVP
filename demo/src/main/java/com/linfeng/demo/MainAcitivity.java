package com.linfeng.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.base.TempletActivity;
import com.baozi.mvpdemo.presenter.TempletPresenter;

public class MainAcitivity extends TempletActivity {


    @NonNull
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getToolbarHelper().setTitle("首页");
        getToolbarHelper().setRightText("213", null);
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
        return new TempletPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void loadData() {

            }
        };
    }

    @Override
    public boolean isMaterialDesign() {
        return true;
    }
}

package com.linfeng.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.base.BaseToolbarActivity;
import com.baozi.mvpdemo.presenter.BasePresenter;

public class MainActivity extends BaseToolbarActivity {


    @NonNull
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getToolbarHelper().setTitle("首页");
        getToolbarHelper().setRightText("213", null);
        setMaterialDesignEnabled(true);
        return inflater.inflate(R.layout.activity_main, null);
    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter<ToolbarView>() {
            @Override
            public void onCreate() {
//                TabLayout tabLayout = mView.findView(R.id.tabLayout);
//                for (int i = 0; i < 4; i++) {
//                    TabLayout.Tab tab = tabLayout.newTab();
//                    tab.setText(i+"");
//                    tabLayout.addTab(tab);
//                }
                getToolbarHelper().getToolbar();
            }

            @Override
            public void LoadData() {

            }
        };
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
}

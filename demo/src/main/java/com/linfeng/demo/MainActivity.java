package com.linfeng.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.baozi.mvpdemo.base.BaseActivity;
import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.UIView;

public class MainActivity extends BaseActivity {


    @Override
    protected View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        getToolbarHelper().setTitle("首页");
        getToolbarHelper().setRightText("213",null);
        setMaterialDesignEnabled(true);

        return inflater.inflate(R.layout.activity_main, null);
    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter<UIView>() {
            @Override
            public void onCreate() {
//                TabLayout tabLayout = mView.findView(R.id.tabLayout);
//                for (int i = 0; i < 4; i++) {
//                    TabLayout.Tab tab = tabLayout.newTab();
//                    tab.setText(i+"");
//                    tabLayout.addTab(tab);
//                }
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
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean isMaterialDesign() {
        return true;
    }
}

package com.linfeng.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvpdemo.base.BaseActivity;
import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.BaseActivityView;

public class MainActivity extends BaseActivity {


    @Override
    protected View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, null);
    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void LoadData() {

            }
        };
    }
    @Override
    public void isNightMode(boolean isNight) {

    }
}

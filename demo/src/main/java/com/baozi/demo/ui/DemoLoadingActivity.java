package com.baozi.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.demo.persenter.MainPresenter;
import com.baozi.mvp.tempalet.TemplateLoadingActivity;

public class DemoLoadingActivity extends TemplateLoadingActivity<MainPresenter> {

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }
}

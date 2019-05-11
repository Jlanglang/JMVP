package com.baozi.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.demo.persenter.MainPresenter;
import com.baozi.mvp.tempalet.TemplateActivity;

public class DemoTemplateActivity extends TemplateActivity<MainPresenter> {

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getToolbarHelper().setTitle("我是模板Activity");
        getToolbarHelper().setLeading("关闭");
        getToolbarHelper().setCanBack(false);
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }
}

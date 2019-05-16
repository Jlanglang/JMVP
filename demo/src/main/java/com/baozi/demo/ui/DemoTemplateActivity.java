package com.baozi.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.tempalet.TemplateActivity;

public class DemoTemplateActivity extends TemplateActivity<DemoPresenter> {

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_template;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getToolbarHelper().setTitle("我是模板Activity");
        getToolbarHelper().setLeading("关闭");
        getToolbarHelper().setCanBack(false);
    }

    @Override
    protected DemoPresenter initPresenter() {
        return new DemoPresenter();
    }
}

package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.base.BaseActivity;

public class BaseAt extends BaseActivity<DemoPresenter> {

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }

    @Override
    protected DemoPresenter initPresenter() {
        return new DemoPresenter();
    }
}

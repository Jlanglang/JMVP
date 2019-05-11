package com.baozi.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.persenter.MainPresenter;
import com.baozi.mvp.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        findView(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login();
            }
        });

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }
}

package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
        findView(R.id.bt_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toDemoAt(BaseAt.class);
            }
        });
        findView(R.id.bt_template).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toDemoAt(TemplateAt.class);
            }
        });
        findView(R.id.bt_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toDemoAt(LoadingAt.class);
            }
        });
        findView(R.id.bt_fg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toDemoAt(PageAt.class);
            }
        });
    }

    @Override
    public void onNewThrowable(Throwable throwable) {
        super.onNewThrowable(throwable);
    }

    @NonNull
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

}

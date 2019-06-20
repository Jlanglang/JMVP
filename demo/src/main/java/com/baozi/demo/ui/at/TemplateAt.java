package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.tempalet.TemplateActivity;

public class TemplateAt extends TemplateActivity<DemoPresenter> {
    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_template;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getToolbarHelper().setTitle("我是模板Activity")
                .setLeading("关闭")
                .setCanBack(true);
    }

    @NonNull
    @Override
    protected DemoPresenter initPresenter() {
        return new DemoPresenter();
    }
}

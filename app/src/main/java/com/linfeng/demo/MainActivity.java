package com.linfeng.demo;

import android.os.Bundle;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.templet.TemplateActivity;
import com.baozi.mvp.templet.options.ToolbarOptions;


public class MainActivity extends TemplateActivity<BasePresenter> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected BasePresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        return super.getToolbarOptions().setNoBack(true)
                .setToolbarDrawable(R.drawable.shape)
                .setStatusDrawable(R.drawable.shape);
    }
}

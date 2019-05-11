package com.baozi.homemodle.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.baozi.homemodle.R;
import com.baozi.homemodle.service.CustomTestService;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.tempalet.TemplateActivity;
import com.baozi.mvp.tempalet.helper.SimpleToolbarHelperImpl;


public class MainActivity extends TemplateActivity<BasePresenter> {

    private TextView view;

    @Override
    public SimpleToolbarHelperImpl getToolbarHelper() {
        return (SimpleToolbarHelperImpl) super.getToolbarHelper();
    }

    @Override
    public void onNewThrowable(Throwable throwable) {
        super.onNewThrowable(throwable);
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        view = findView(R.id.tv_content);
        Intent intent = new Intent(this, CustomTestService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }
    }

    @Override
    protected int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_main;
    }

    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected BasePresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    public int getToolbarLayout() {
        return 0;
    }
}

package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baozi.demo.R;
import com.baozi.demo.persenter.MainPresenter;
import com.baozi.mvp.tempalet.TemplateActivity;
import com.bumptech.glide.Glide;

public class LoadingAt extends TemplateActivity<MainPresenter> {

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.at_main;
    }

    @Override
    protected View wrapperContentView(View view) {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((ViewGroup) view).addView(imageView,0);
        Glide.with(this).load("file:///android_asset/a.gif").into(imageView);
        return super.wrapperContentView(view);
    }

    @NonNull
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean isOpenLoading() {
        return true;
    }
}

package com.baozi.homemodle.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.homemodle.R;
import com.baozi.homemodle.presenter.HomeActvityPresenterImpl;
import com.baozi.mvp.base.TempletActivity;
import com.zhy.autolayout.utils.AutoUtils;


public class MainActivity extends TempletActivity<HomeActvityPresenterImpl> {

    @NonNull
    @Override
    public View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_activity_main, null);
        AutoUtils.auto(inflate);
        return inflate;
    }

    @Override
    protected HomeActvityPresenterImpl initPresenter() {
        return new HomeActvityPresenterImpl();
    }

}

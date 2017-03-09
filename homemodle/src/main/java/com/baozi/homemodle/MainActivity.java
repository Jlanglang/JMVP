package com.baozi.homemodle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.base.TempletActivity;
import com.baozi.mvpdemo.presenter.TempletPresenter;
import com.zhy.autolayout.utils.AutoUtils;

public class MainActivity extends TempletActivity {

    @NonNull
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_main, null);
        AutoUtils.auto(inflate);
        return inflate;
    }

    @Override
    protected TempletPresenter initPresenter() {
        return new TempletPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void loadData() {

            }
        };
    }
}

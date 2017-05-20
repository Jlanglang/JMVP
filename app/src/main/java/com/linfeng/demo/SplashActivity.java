package com.linfeng.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.base.BaseActivity;
import com.baozi.mvp.presenter.BasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by baozi on 2017/5/20.
 */

public class SplashActivity extends BaseActivity {
    @NonNull
    @Override
    protected View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_splash,null);
    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter() {
            @Override
            public void onCreate() {
                Observable.timer(3000, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Intent intent = new Intent(mView.getContext(), MainActivity.class);
                        ((Activity) mView.getContext()).startActivity(intent);
                        ((Activity) mView.getContext()).finish();
                    }
                });
            }

            @Override
            public void loadData() {

            }

            @Override
            public void cancleNetWork() {

            }
        };
    }
}

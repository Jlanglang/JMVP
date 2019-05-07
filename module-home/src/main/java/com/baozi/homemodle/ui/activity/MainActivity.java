package com.baozi.homemodle.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.baozi.homemodle.R;
import com.baozi.location.JApiImpl;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.templet.TemplateActivity;
import com.linfeng.rx_retrofit_network.location.SimpleParams;
import com.linfeng.rx_retrofit_network.location.rxandroid.NetWorkTransformer;

import io.reactivex.functions.Consumer;


public class MainActivity extends TemplateActivity<BasePresenter> {
    View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JApiImpl.getJApi()
                .BasePost("Home/UserLoginApp",
                        SimpleParams.create().putP("userName", "admin")
                                .putP("strPwd", 123456)
                )
                .compose(new NetWorkTransformer<String>())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String stringBaseResponse) throws Exception {
                        String data = stringBaseResponse;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
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

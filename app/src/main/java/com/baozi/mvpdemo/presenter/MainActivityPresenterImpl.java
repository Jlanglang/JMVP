package com.baozi.mvpdemo.presenter;

import android.os.Bundle;

import com.baozi.mvpdemo.contract.MainActivityContract;
import com.baozi.mvpdemo.location.rxandroid.SimpleSubscriber;
import com.baozi.mvpdemo.model.MainActivityModelImpl;

import java.util.HashMap;

/**
 * Created by baozi on 2016/11/24
 */

public class MainActivityPresenterImpl extends BasePresenter<MainActivityContract.View> implements MainActivityContract.Presenter {

    @Override
    public void onCreate() {
        MainActivityModelImpl mainActivityModel = new MainActivityModelImpl();
        mainActivityModel.login(new HashMap<String, Object>()).subscribe(new SimpleSubscriber<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
package com.baozi.mvpdemp.presenter;

import android.os.Bundle;

import com.baozi.mvpdemp.contract.MainActivityContract;
import com.baozi.mvpdemp.location.rxandroid.SimpleSubscriber;
import com.baozi.mvpdemp.model.MainActivityModelImpl;

import java.util.HashMap;

import rx.functions.Action1;

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
package com.baozi.demo.persenter;

import android.widget.Toast;

import com.baozi.demo.model.MainModel;
import com.baozi.demo.ui.MainActivity;
import com.baozi.mvp.StartFactory;
import com.baozi.mvp.presenter.BasePresenter;

public class MainPresenter extends BasePresenter<MainActivity> {
    private MainModel mainModel;

    @Override
    public void onCreate() {
        super.onCreate();
        mainModel = new MainModel();
        String login = mainModel.login();
        Toast.makeText(mView, login, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelNetWork() {

    }

    @Override
    public void netWorkError(Throwable throwable) {

    }

    public void toDemoAt(Class activityClass) {
        StartFactory.startActivity(mView, activityClass);
    }
}

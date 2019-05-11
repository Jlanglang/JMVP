package com.baozi.demo.persenter;

import android.widget.Toast;

import com.baozi.demo.model.MainModel;
import com.baozi.demo.ui.DemoBaseActivity;
import com.baozi.demo.ui.DemoLoadingActivity;
import com.baozi.demo.ui.DemoTemplateActivity;
import com.baozi.demo.ui.MainActivity;
import com.baozi.mvp.presenter.BasePresenter;

public class MainPresenter extends BasePresenter<MainActivity> {
    private MainModel mainModel;

    @Override
    public void onCreate() {
        mainModel = new MainModel();
        String login = mainModel.login();
        Toast.makeText(mView, login, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshData() {

    }

    @Override
    public void cancelNetWork() {

    }

    @Override
    public void netWorkError(Throwable throwable) {

    }

    public void toDemoAt(String template) {
        switch (template) {
            case "1":
                mView.startActivity(DemoBaseActivity.class);
                break;
            case "2":
                mView.startActivity(DemoTemplateActivity.class);
                break;
            case "3":
                mView.startActivity(DemoLoadingActivity.class);
                break;
        }
    }
}

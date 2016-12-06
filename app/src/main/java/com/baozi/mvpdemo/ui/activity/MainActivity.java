package com.baozi.mvpdemo.ui.activity;

import android.os.Bundle;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.contract.MainActivityContract;
import com.baozi.mvpdemo.presenter.MainActivityPresenterImpl;
import com.baozi.mvpdemo.base.BaseActivity;

public class MainActivity extends BaseActivity<MainActivityPresenterImpl> implements MainActivityContract.View {

    @Override
    protected MainActivityPresenterImpl initPresenter() {
        return new MainActivityPresenterImpl();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }


}

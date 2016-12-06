package com.baozi.mvpdemp.ui.activity;

import android.os.Bundle;
import android.widget.Toolbar;

import com.baozi.mvpdemp.R;
import com.baozi.mvpdemp.contract.MainActivityContract;
import com.baozi.mvpdemp.presenter.MainActivityPresenterImpl;
import com.baozi.mvpdemp.base.BaseActivity;

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

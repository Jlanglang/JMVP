package com.baozi.homemodle.presenter;

import android.support.v7.app.ActionBar;

import com.baozi.frame.JBasePresenter;
import com.baozi.homemodle.contract.HomeActvityContract;

/**
 * Created by Administrator on 2017/03/28
 */

public class HomeActvityPresenterImpl extends JBasePresenter<HomeActvityContract.View>
        implements HomeActvityContract.Presenter {

    @Override
    public void onCreate() {
        ActionBar supportActionBar = mView.getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayUseLogoEnabled(false);
        supportActionBar.setDisplayShowHomeEnabled(false);
        mView.getToolbarHelper().getToolbar().setContentInsetsAbsolute(0,0);
    }

    @Override
    public void loadData() {

    }
}
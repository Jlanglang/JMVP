package com.baozi.mvpdemp.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.baozi.mvpdemp.contract.IndexFragmentContract;

/**
 * Created by baozi on 2016/11/28
 */

public class IndexFragmentPresenterImpl extends BasePresenter<IndexFragmentContract.View> implements IndexFragmentContract.Presenter {

    @Override
    public void onCreate() {
        FragmentManager fragmentManager = mView.getFragmentManager();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
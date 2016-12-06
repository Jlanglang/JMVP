package com.baozi.mvpdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.base.BaseFragment;
import com.baozi.mvpdemo.contract.IndexFragmentContract;
import com.baozi.mvpdemo.presenter.IndexFragmentPresenterImpl;

/**
 * Created by baozi on 2016/11/28.
 */
public class IndexFragment extends BaseFragment<IndexFragmentPresenterImpl>implements IndexFragmentContract.View {
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public IndexFragmentPresenterImpl initPresenter() {
        return new IndexFragmentPresenterImpl();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}

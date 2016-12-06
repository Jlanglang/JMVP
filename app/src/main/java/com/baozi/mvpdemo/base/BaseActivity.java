package com.baozi.mvpdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.BaseActivityView;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseActivityView {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化acitivity,
        onCreateActivity(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter == null) {
            //创建Presenter
            mPresenter = initPresenter();
            //类似fragment的绑定.拿到引用
            mPresenter.onAttch(this);
            //初始化Presenter
            mPresenter.onCreate();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }

    /**
     * 创建prensenter
     *
     * @return <T extends BasePresenter> 必须是BasePresenter的子类
     */
    protected abstract T initPresenter();

    /**
     * 子类必须实现,并初始化Activity,比如setContentView()
     */
    protected abstract void onCreateActivity(Bundle savedInstanceState);

    @Override
    public void isNightMode(boolean isNight) {

    }
}
package com.baozi.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.BaseActivityView;

/**
 * activity的基类
 *
 * @param <T>
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseActivityView {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = initPresenter();
        mPresenter.onAttch(this);
        onCreateActivity(savedInstanceState);
        mPresenter.onCreate();
    }

    protected abstract void onCreateActivity(Bundle savedInstanceState);

    /**
     * 子类实现Presenter,且必须继承BasePrensenter
     *
     * @return
     */
    protected abstract T initPresenter();


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter.onDetach();
        }
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * 设置toolbar,默认不显示toolbar的title.标题
     *
     * @param toolbar
     */
    @Override
    public void setToolbar(Toolbar toolbar) {
        setToolbar(toolbar, false);
    }

    /**
     * 设置toolbar,默认不显示toolbar的title.标题
     *
     * @param toolbar
     */
    @Override
    public void setToolbar(Toolbar toolbar, boolean showTitle) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(showTitle);
    }

    /**
     * '
     * 跳转fragment
     *
     * @param tofragment
     */
    @Override
    public void startFragment(Fragment tofragment) {
        startFragment(tofragment, null);
    }

    /**
     * @param tofragment 跳转的fragment
     * @param tag        fragment的标签
     */
    @Override
    public void startFragment(Fragment tofragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(
//                R.anim.translate_fade_in_right,
//                R.anim.translate_fade_out_right, R.anim.translate_fade_in_left, R.anim.translate_fade_out_left);
        fragmentTransaction.add(android.R.id.content, tofragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

}

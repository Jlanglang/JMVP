package com.baozi.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.helper.ToolbarHelper;
import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.BaseActivityView;

/**
 * activity的基类
 *
 * @param <T>
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseActivityView {
    protected T mPresenter;
    protected SparseArray<View> mViews;
    private ToolbarHelper mToolbarHelper;
    private boolean isMaterialDesign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        //创建presenter
        mPresenter = initPresenter();
        //绑定Activity
        mPresenter.onAttch(this);
        //是否完全自定义layout
        if (isCustomLayout()) {
            initContentView(LayoutInflater.from(this), savedInstanceState);
        } else {
            super.setContentView(R.layout.activity_base);
            //创建contentView
            View view = initContentView(LayoutInflater.from(this), savedInstanceState);
            //添加contentView
            FrameLayout base_content = findView(R.id.base_content);
            if (view != null) {
                //交给Persenter去扩展
                mPresenter.initContentView(base_content, view);
            }
            //创建toolbar
            createToolbar();
        }
        mPresenter.onCreate();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                mPresenter.LoadData();
                return false;
            }
        });
    }

    /**
     * 创建toolbar
     */
    private void createToolbar() {
        if (getSupportActionBar() != null || initToolbarLayout() <= 0) {
            //有actionbar或者不需要toolbar
            return;
        }
//        ViewStub viewStub = findView(R.id.vs_toolbar);
//        viewStub.setLayoutResource(initToolbarLayout());
//        Toolbar mToolbar = (Toolbar) viewStub.inflate();
        mToolbarHelper = ToolbarHelper.Create(this, initToolbarLayout());
        setSupportActionBar(mToolbarHelper.getToolbar());
//        Toolbar toolbar = getToolbarHelper().getToolbar();
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
    }
//        //是否使用MaterialDesign
//        if (isMaterialDesign()) {
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
//            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowCustomEnabled(true);
//            getSupportActionBar().setDisplayUseLogoEnabled(true);
////            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    onBack();
////                }
////            });
//            return;
//        }
//        //默认的toolbar
//        if (initToolbarLayout() == DEFUATL_BASE_TOOLBAR) {
////            mToolbarHelper = new DefuatlToolbarHelperImplV1(mToolbar, this);
////            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            //设置无边距
////            mToolbar.setContentInsetsAbsolute(0, 0);
//        } else {
//            //自定义的Toolbar,自定义实现．使用gettoolbar()获取并设置．
//
//        }
//    }

    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    protected int initToolbarLayout() {
        return ToolbarHelper.DEFUATL_BASE_TOOLBAR_V1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (isCustomLayout()) {
            super.setContentView(layoutResID);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }

    @Override
    public void setContentView(View view) {
        if (isCustomLayout()) {
            super.setContentView(view);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (isCustomLayout()) {
            super.setContentView(view, params);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }

    /**
     * 初始化ContentView
     * 建议不要包含toolbar
     *
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    protected abstract View initContentView(LayoutInflater inflater, Bundle savedInstanceState);

    /**
     * 子类实现Presenter,且必须继承BasePrensenter
     *
     * @return
     */
    protected abstract T initPresenter();


    @Override
    protected void onStart() {
        mPresenter.onStart();
        super.onStart();
    }

    @Override
    public void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        mPresenter.onRestart();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onResume() {
        mPresenter.onResume();
        super.onResume();
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
        fragmentTransaction.add(android.R.id.content, tofragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * onBackPressed();
     */
    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId 资源id
     * @return
     */
    @Override
    public <V extends View> V findView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }


    /**
     * 是否完全自定义布局
     *
     * @return 返回false使用Base_activity, 返回true则需要在initContView里面使用setContentView.
     * 不推荐复写onCreate(),因为子类Presenter的oncreate(),会调用在子类的
     * @see BaseActivity#onCreate(Bundle) 之前,可能造成NullException异常
     */
    @Override
    public abstract boolean isCustomLayout();


    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        this.isMaterialDesign = isMaterialDesign;
        getToolbarHelper().setMaterialDesignEnabled(isMaterialDesign);
    }

    /**
     * 是否启用MaterialDesign样式
     *
     * @return
     */
    @Override
    public boolean isMaterialDesign() {
        return isMaterialDesign;
    }

    /**
     * 如果设置的主题不是NoActionBar或者initToolbar()返回是0,则返回null.
     *
     * @return mToolbar 可能为null.
     */
    public Toolbar getToolbar() {
        return getToolbarHelper().getToolbar();
    }

    /**
     * 如果修改了initToolbarLayout(),并且<=0的话,该方法将返回null
     *
     * @return
     */
    public ToolbarHelper getToolbarHelper() {
        if (mToolbarHelper == null && initToolbarLayout() >= 0) {
            mToolbarHelper = ToolbarHelper.Create(this, initToolbarLayout());
        }
        return mToolbarHelper;
    }
}

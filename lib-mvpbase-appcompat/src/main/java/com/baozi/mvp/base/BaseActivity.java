package com.baozi.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.view.BaseActivityView;

/**
 * @author jlanglang  2016/1/5 9:42
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseActivityView {
    protected String TAG = this.getClass().getSimpleName();
    protected T mPresenter;
    private SparseArray<View> mViews;
    private View mContentView;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = initPresenter();
        //绑定Activity
        mPresenter.onAttach(this);
        //初始化ContentView
        mContentView = initView(getLayoutInflater(), savedInstanceState);
        super.setContentView(mContentView);
        //初始化Activity
        init(savedInstanceState);
        //初始化presenter
        mPresenter.onCreate();
        onPresentersCreate();
        //延时加载数据.
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                if (getStatusBarDrawable() > 0) {
                    initStatusBar();
                    getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            initStatusBar();
                        }
                    });
                }
                mPresenter.initData();
                return false;
            }
        });
    }

    protected void initStatusBar() {
        if (statusBarView == null) {
            int identifier = getResources().getIdentifier("statusBarBackground", "id", "android");
            statusBarView = getWindow().findViewById(identifier);
        }
        if (statusBarView != null) {
            statusBarView.setBackgroundResource(getStatusBarDrawable());
        }
    }

    @DrawableRes
    @ColorRes
    protected int getStatusBarDrawable() {
        return MVPManager.getToolbarOptions().getStatusDrawable();
    }

    /**
     * 扩展除了默认的presenter的其他Presenter初始化
     */
    protected void onPresentersCreate() {

    }

    /**
     * 运行在initView之后
     * 已经setContentView
     * 可以做一些初始化操作
     *
     * @return
     */
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public View getContentView() {
        return mContentView;
    }

    @Override
    protected void onStart() {
        mPresenter.onStart();
        super.onStart();
    }

    @Override
    protected void onPause() {
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
    protected void onResume() {
        mPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        mPresenter.onDetach();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
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
     * 跳转Activity
     */
    public void startActivity(Class aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, Bundle bundle, int flag) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        intent.addFlags(flag);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, int flag) {
        Intent intent = new Intent(this, aClass);
        intent.addFlags(flag);
        startActivity(intent);
    }

    /**
     * onBackPressed();
     */
    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public BaseActivity getAppcompatActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    private SparseArray<View> getViews() {
        if (mViews == null) {
            mViews = new SparseArray<>();
        }
        return mViews;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId 资源id
     * @return
     */
    @Override
    public <V extends View> V findView(@IdRes int viewId) {
        View view = getViews().get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            getViews().put(viewId, view);
        }
        return (V) view;
    }


    /**
     * 初始化ContentView
     * 建议不要包含toolbar
     *
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    @NonNull
    protected View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
        int layout = initView(savedInstanceState);
        return inflater.inflate(layout, null);
    }

    /**
     * 建议不要包含toolbar
     *
     * @param savedInstanceState
     * @return 布局layout
     */
    @LayoutRes
    protected abstract int initView(Bundle savedInstanceState);

    /**
     * 子类实现Presenter,且必须继承BasePresenter
     *
     * @return
     */
    protected abstract T initPresenter();


}

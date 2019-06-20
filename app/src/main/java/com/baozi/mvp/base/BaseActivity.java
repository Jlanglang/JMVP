package com.baozi.mvp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.MVPManager;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.tempalet.helper.load.LoadHelper;
import com.baozi.mvp.tempalet.weight.LoadingPager;
import com.baozi.mvp.view.UIView;

/**
 * @author jlanglang  2016/1/5 9:42
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements UIView {
    public final String TAG = this.getClass().getSimpleName();//tag不要用反射的形式取
    protected T mPresenter;
    private SparseArray<View> mViews;
    private View mContentView;
    private View statusBarView;
    private LoadHelper loadHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = initPresenter();
        //绑定Activity
        mPresenter.onAttach(this);

        getLifecycle().addObserver(mPresenter);

        //初始化ContentView
        mContentView = initView(getLayoutInflater(), savedInstanceState);

        if (isOpenLoading()) {
            loadHelper = new LoadHelper();
            mContentView = loadHelper.wrapperLoad(mContentView, new LoadingPager.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.onRefreshData();
                }
            });
        }

        if (mContentView != null) {
            super.setContentView(mContentView);
        }

        //初始化Activity
        init(savedInstanceState);
    }

    @Override
    public void onNewThrowable(Throwable throwable) {

    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        onPresentersCreate();
        if (getStatusBarDrawable() > 0) {
            getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    initStatusBar();
                }
            });
        }
        mPresenter.onRefreshData();
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
     */
    protected void init(@Nullable Bundle savedInstanceState) {
        Log.d("lifecycle", "activityInit");
    }

    @Override
    @Nullable
    public View getContentView() {
        return mContentView != null ? mContentView : findViewById(android.R.id.content);
    }

    @Override
    public void onDetachedFromWindow() {
        mPresenter.onDetach();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
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
    protected View initView(@NonNull LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        int layout = initView(savedInstanceState);
        try {
            return inflater.inflate(layout, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isFinish() {
        return isFinishing();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    /**
     * 建议不要包含toolbar
     *
     * @param savedInstanceState
     * @return 布局layout
     */
    @LayoutRes
    protected int initView(@Nullable Bundle savedInstanceState) {
        JView annotation = this.getClass().getAnnotation(JView.class);
        if (annotation != null) {
            return annotation.layout();
        }
        return 0;
    }

    /**
     * 子类实现Presenter,且必须继承BasePresenter
     *
     * @return
     */
    @NonNull
    protected T initPresenter() {
        T t = null;
        JView annotation = this.getClass().getAnnotation(JView.class);
        if (annotation != null) {
            try {
                t = (T) annotation.p().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (t == null) {
            t = (T) new EmptyPresenter();
        }
        return t;
    }

    @Override
    public boolean isOpenLoading() {
        return false;
    }

    @Nullable
    public LoadHelper getLoadHelper() {
        return loadHelper;
    }
}

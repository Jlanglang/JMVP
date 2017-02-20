package com.baozi.mvpdemo.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.BaseActivityView;

/**
 * activity的基类
 *
 * @param <T>
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseActivityView {
    private static final int DEFUATL_BASE_TOOLBAR = R.layout.base_toolbar;
    protected T mPresenter;
    protected SparseArray<View> mViews;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        //创建presenter
        mPresenter = initPresenter();
        //绑定Activity
        mPresenter.onAttch(this);
        //是否完全自定义layout
        if (!mPresenter.isCustomLayout()) {
            super.setContentView(R.layout.activity_base);
            //创建contentview
            View view = initContentView(LayoutInflater.from(this), savedInstanceState);
            //创建toolbar
            createToolbar();
            //真正的添加contentview
            FrameLayout base_content = findView(R.id.base_content);
            //交给Persenter去处理
            mPresenter.initContentView(base_content, view);
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
        if (getSupportActionBar() != null || initToolbar() <= 0) {
            //有actionbar或者不需要toolbar
            return;
        }
        ViewStub viewStub = findView(R.id.vs_toolbar);
        viewStub.setLayoutResource(initToolbar());
        mToolbar = (Toolbar) viewStub.inflate();
        setSupportActionBar(mToolbar);
        if (mPresenter.isMaterialDesign()) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBack();
                }
            });
            return;
        }
        //默认的toolbar
        if (initToolbar() == DEFUATL_BASE_TOOLBAR) {
            //设置无边距
            mToolbar.setContentInsetsAbsolute(0, 0);
            //显示大标题,则隐藏中间的title
            if (!mPresenter.isToolbarShowLeftText()) {
                mToolbar.findViewById(R.id.tv_title).setVisibility(View.VISIBLE);
            }
            if (!mPresenter.isToolbarShowRightText()) {
                mToolbar.findViewById(R.id.tv_left).setVisibility(View.VISIBLE);
            }
        } else {
            //子类修改自定义的actionbar

        }
    }

    protected void setTitile(String title) {
        if (mToolbar == null) {
            return;
        }
        if (mPresenter.isMaterialDesign()) {
            getSupportActionBar().setTitle(title);
        } else {
            ((TextView) mToolbar.findViewById(R.id.tv_title))
                    .setText(title);
        }
    }

    protected void setTitile(int titleId) {
        String title = getResources().getString(titleId);
        setTitle(title);
    }

    protected void setLeftTextString(String str) {
        if (mToolbar == null || mPresenter.isMaterialDesign()) {
            return;
        }
        ((TextView) mToolbar.findViewById(R.id.tv_left))
                .setText(str);
    }

    protected void setLeftTextString(@StringRes int strId) {
        String string = getResources().getString(strId);
        setLeftTextString(string);
    }

    protected void setLeftTextDrawable(Drawable drawable) {
        if (mToolbar == null || mPresenter.isMaterialDesign()) {
            return;
        }
        View tv_left = mToolbar.findViewById(R.id.tv_left);
        tv_left.setBackground(drawable);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });

    }

    protected void setLeftTextDrawable(@DrawableRes int drawableId) {
        setLeftTextDrawable(ContextCompat.getDrawable(this, drawableId));
    }

    protected void setRightTextString(String str) {
        if (mToolbar == null || mPresenter.isMaterialDesign()) {
            return;
        }
        ((TextView) mToolbar.findViewById(R.id.tv_left))
                .setText(str);
    }

    protected void setRightTextString(int strId) {
        String string = getResources().getString(strId);
        setRightTextString(string);
    }

    protected void setRightTextDrawable(Drawable drawable) {
        if (mToolbar == null || mPresenter.isMaterialDesign()) {
            return;
        }
        mToolbar.findViewById(R.id.tv_right)
                .setBackground(drawable);
    }

    protected void setRightTextDrawable(int drawableId) {
        setRightTextDrawable(ContextCompat.getDrawable(this, drawableId));
    }

    /**
     * 默认使用base_toolbar
     *
     * @return
     */
    protected int initToolbar() {
        return DEFUATL_BASE_TOOLBAR;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mPresenter.onCreateOptionsMenu(menu, getMenuInflater())) {
//            mToolbar.setOnMenuItemClickListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (mPresenter.isCustomLayout()) {
            super.setContentView(layoutResID);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }

    @Override
    public void setContentView(View view) {
        if (mPresenter.isCustomLayout()) {
            super.setContentView(view);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (mPresenter.isCustomLayout()) {
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
}

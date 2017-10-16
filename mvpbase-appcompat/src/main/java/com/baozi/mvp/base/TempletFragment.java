package com.baozi.mvp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.baozi.mvp.R;
import com.baozi.mvp.helper.ToolbarHelper;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.view.ToolbarView;
import com.baozi.mvp.view.UIView;

/**
 * 模版Fragment
 *
 * @param <T>
 */
public abstract class TempletFragment<T extends BasePresenter> extends BaseFragment<T>
        implements UIView, ToolbarView {
    private ToolbarHelper mToolbarHelper;
    private View rootView;

    /**
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.templet_content, null);
        //ContentView容器
        FrameLayout contentGroup = (FrameLayout) rootView.findViewById(R.id.templet_content);
        //真正的创建contentView
        View contentView = onCreateContentView(inflater, savedInstanceState);
        contentGroup.removeAllViews();
        contentGroup.addView(contentView);
        return rootView;
    }


    @NonNull
    protected abstract View onCreateContentView(LayoutInflater inflater, Bundle savedInstanceState);

    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    @Override
    public int initToolbarLayout() {
        return ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * 每次菜单被关闭时调用.（菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项）
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    /**
     * 切换MaterialDesign风格.
     *
     * @param isMaterialDesign
     */
    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        getToolbarHelper().setMaterialDesignEnabled(isMaterialDesign);
    }

    @Override
    public boolean isMaterialDesign() {
        return false;
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
     * @return
     */
    @Override
    public ToolbarHelper getToolbarHelper() {
        if (mToolbarHelper == null) {
            mToolbarHelper = ToolbarHelper.Create(this, rootView, initToolbarLayout());
        }
        return mToolbarHelper;
    }

    @Override
    protected abstract T initPresenter();
}

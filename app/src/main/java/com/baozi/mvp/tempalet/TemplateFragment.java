package com.baozi.mvp.tempalet;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.R;
import com.baozi.mvp.base.BaseFragment;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.tempalet.helper.toolbar.ToolbarHelper;
import com.baozi.mvp.tempalet.options.ToolbarOptions;
import com.baozi.mvp.view.ToolbarView;

/**
 * 模版Fragment
 *
 * @param <T>
 */
public abstract class TemplateFragment<T extends BasePresenter> extends BaseFragment<T>
        implements ToolbarView {
    private ToolbarHelper mToolbarHelper;
    private ViewGroup rootView;
    private ToolbarOptions toolbarOptions;

    /**
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.template_layout, null);
        //初始化一次
        mToolbarHelper = getToolbarHelper();
        View view = wrapperContentView(super.initView(inflater, savedInstanceState));
        rootView.addView(view, 1);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(new AppBarLayout.ScrollingViewBehavior());
            view.requestLayout();
        }
        return rootView;
    }

    @Override
    public View getContentView() {
        return rootView;
    }

    /**
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    @Override
    public int getToolbarLayout() {
        return getToolbarOptions().getToolbarLayout();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        if (toolbarOptions == null) {
            toolbarOptions = MVPManager.getToolbarOptions();
        }
        return toolbarOptions;
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
     * @return
     */
    @Override
    public ToolbarHelper getToolbarHelper() {
        if (mToolbarHelper == null) {
            mToolbarHelper = ToolbarHelper.Create(this);
        }
        return mToolbarHelper;
    }

    protected View wrapperContentView(View view) {
        return view;
    }
}

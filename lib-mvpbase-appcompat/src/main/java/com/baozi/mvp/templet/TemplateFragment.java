package com.baozi.mvp.templet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
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
import com.baozi.mvp.templet.helper.ToolbarHelper;
import com.baozi.mvp.templet.options.ContentOptions;
import com.baozi.mvp.templet.options.ToolbarOptions;
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

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        view.requestLayout();
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
        return ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        return MVPManager.getToolbarOptions();
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

    protected ContentOptions getContentOptions() {
        return MVPManager.getContentOptions();
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
            mToolbarHelper = ToolbarHelper.Create(this, rootView);
        }
        return mToolbarHelper;
    }

    @Override
    protected abstract T initPresenter();

    protected View wrapperContentView(View view) {
        return view;
    }

}

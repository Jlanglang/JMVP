package com.baozi.mvp.tempalet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.R;
import com.baozi.mvp.base.BaseActivity;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.tempalet.helper.toolbar.ToolbarHelper;
import com.baozi.mvp.tempalet.options.ToolbarOptions;
import com.baozi.mvp.view.BaseView;
import com.baozi.mvp.view.ToolbarView;

/**
 * 模版Activity
 *
 * @param <T>
 */
public abstract class TemplateActivity<T extends BasePresenter> extends BaseActivity<T>
        implements ToolbarView {
    private ToolbarHelper mToolbarHelper;
    private ViewGroup mRootView;
    private ToolbarOptions toolbarOptions;

    @Override
    public View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            throw new IllegalStateException("请使用NoActionbar的Theme,否则使用该模板无意义");
        }
        mRootView = (ViewGroup) inflater.inflate(R.layout.template_layout, null);
        //初始化一次
        mToolbarHelper = getToolbarHelper();
        View baseView = super.initView(inflater, savedInstanceState);
        View wrapperView = wrapperContentView(baseView);
        mRootView.addView(wrapperView, 1);
        ViewGroup.LayoutParams layoutParams = wrapperView.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(new AppBarLayout.ScrollingViewBehavior());
            wrapperView.requestLayout();
        }
        return mRootView;
    }

    protected View wrapperContentView(View view) {
        return view;
    }

    /**
     * 如果调用在initView()之前,可能为null
     *
     * @return
     */
    @Override
    public View getContentView() {
        return mRootView;
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
    protected int getStatusBarDrawable() {
        return getToolbarOptions().getStatusDrawable();
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        if (toolbarOptions == null) {
            toolbarOptions = MVPManager.getToolbarOptions();
        }
        return toolbarOptions;
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 显示menu的icon
     *
     * @param view
     * @param menu
     * @return
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    MenuBuilder menuBuilder = (MenuBuilder) menu;
                    menuBuilder.setOptionalIconsVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
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


    @Override
    public TemplateActivity getAppcompatActivity() {
        return this;
    }

    /**
     * 如果设置的主题不是NoActionBar,或者initToolbar()是0,则返回null.
     *
     * @return mToolbar 可能为null.
     */
    @Nullable
    public Toolbar getToolbar() {
        return getToolbarHelper().getToolbar();
    }

    /**
     * 请在ui线程中调用
     *
     * @return
     */
    @Override
    public ToolbarHelper getToolbarHelper() {
        if (mToolbarHelper == null) {
            mToolbarHelper = ToolbarHelper.Create(this);
        }
        return mToolbarHelper;
    }

}

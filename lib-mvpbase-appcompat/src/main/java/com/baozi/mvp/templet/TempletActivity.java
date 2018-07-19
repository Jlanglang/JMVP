package com.baozi.mvp.templet;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.baozi.mvp.templet.helper.ToolbarHelper;
import com.baozi.mvp.templet.options.ToolbarOptions;
import com.baozi.mvp.templet.weight.LoadingPager;
import com.baozi.mvp.view.ToolbarView;

/**
 * 模版Activity
 *
 * @param <T>
 */
@Deprecated
public abstract class TempletActivity<T extends BasePresenter> extends BaseActivity<T>
        implements ToolbarView {
    private ToolbarHelper mToolbarHelper;
    private ViewGroup mRootView;
    private View mContentView;

    @NonNull
    @Override
    public View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            throw new IllegalStateException("please extends BaseActivity.TempletActivity Theme must be NoActionbar");
        }
        mRootView = (ViewGroup) inflater.inflate(R.layout.templet_layout, null);
        //初始化一次
        mToolbarHelper = getToolbarHelper();
//        mRootView.addView();
//        //ContentView容器
        LoadingPager loadingPager = (LoadingPager) mRootView.findViewById(R.id.templet_content);
        mContentView = super.initView(inflater, savedInstanceState);
        loadingPager.setSuccessPage(mContentView);
        return mRootView;
    }

    /**
     * 如果调用在initView()之前,可能为null
     *
     * @return
     */
    @Override
    public View getContentView() {
        return mContentView;
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
    protected int getStatusBarDrawable() {
        return getToolbarOptions().getStatusDrawable();
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        return MVPManager.getToolbarOptions();
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isMaterialDesign()) {
            return false;
        }
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
    public TempletActivity getAppcompatActivity() {
        return this;
    }

//    /**
//     * 切换MaterialDesign风格.
//     *
//     * @param isMaterialDesign
//     */
//    @Override
//    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
//        getToolbarHelper().setMaterialDesignEnabled(isMaterialDesign);
//    }

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
     * 请在ui线程中调用
     *
     * @return
     */
    @Override
    public ToolbarHelper getToolbarHelper() {
        if (mToolbarHelper == null) {
            mToolbarHelper = ToolbarHelper.Create(this, mRootView);
        }
        return mToolbarHelper;
    }

    @Override
    protected abstract T initPresenter();

}

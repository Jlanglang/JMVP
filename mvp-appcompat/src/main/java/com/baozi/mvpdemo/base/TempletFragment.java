package com.baozi.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.helper.ToolbarHelper;
import com.baozi.mvpdemo.presenter.TempletPresenter;
import com.baozi.mvpdemo.ui.ToolbarView;

/**
 * 模版Fragment
 *
 * @param <T>
 */
public abstract class TempletFragment<T extends TempletPresenter> extends BaseFragment<T>
        implements ToolbarView {
    private ToolbarHelper mToolbarHelper;
    private View rootView;

    @NonNull
    @Override
    public View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            throw new IllegalStateException("pleace exends BaseFragment,TempletFragmenttheme must Noactionbar");
        }
        rootView = inflater.inflate(R.layout.activity_templet, null);
        //创建toolbar
        mToolbarHelper = getToolbarHelper();
        //ContentView容器
        FrameLayout contentGroup = (FrameLayout) rootView.findViewById(R.id.templet_content);
        //真正的创建contentView
        View contentView = initContentView(inflater, contentGroup, savedInstanceState);
        contentGroup.removeAllViews();
        contentGroup.addView(contentView);
        //交给Persenter去扩展
        mPresenter.wapperContentView(contentGroup);
        return rootView;
    }

    @NonNull
    protected abstract View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState);

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


//    /**
//     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
//     * (只会在第一次初始化菜单时调用)
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!isMaterialDesign() || getToolbarHelper() == null) {
//            return false;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }

//    /**
//     * 显示menu的icon
//     *
//     * @param view
//     * @param menu
//     * @return
//     */
//    @Override
//    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
//        if (menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
//                }
//            }
//        }
//        return super.onPrepareOptionsPanel(view, menu);
//    }

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

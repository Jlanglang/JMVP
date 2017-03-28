package com.baozi.jmvp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.baozi.jmvp.R;
import com.baozi.jmvp.helper.ToolbarHelper;
import com.baozi.jmvp.presenter.TempletPresenter;
import com.baozi.jmvp.ui.view.IToolbarView;

/**
 * activity的基类
 *
 * @param <T>
 */
public abstract class TempletActivity<T extends TempletPresenter> extends BaseActivity<T>
        implements IToolbarView {
    private ToolbarHelper mToolbarHelper;
    private View rootView;

    @NonNull
    @Override
    protected View initView(@NonNull LayoutInflater inflater, Bundle savedInstanceState) {
//        ActionBar supportActionBar = getSupportActionBar();
//        if (supportActionBar != null) {
//            throw new IllegalStateException("pleace exends BaseActivity,TempletActivity theme must Noactionbar");
//        }
//        if (isMaterialDesign()) {
//            rootView = inflater.inflate(R.layout.activity_templet_material_design, null);
//        } else {
        rootView = inflater.inflate(R.layout.activity_templet, null);
//        }
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
        return ToolbarHelper.TOOLBAR_DEFUATL_V1;
    }


    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getToolbarHelper() == null) {
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
    public TempletActivity getActivity() {
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

    /**
     * 如果设置的主题不是NoActionBar或者initToolbar()返回是0,则返回null.
     *
     * @return mToolbar 可能为null.
     */
    public LinearLayout getToolbar() {
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

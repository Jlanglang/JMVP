package com.baozi.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.helper.ToolbarHelper;
import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.BaseActivityView;

import java.lang.reflect.Method;

/**
 * activity的基类
 *
 * @param <T>
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseActivityView {
    protected T mPresenter;
    protected SparseArray<View> mViews;
    private ToolbarHelper mToolbarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        //创建presenter
        mPresenter = initPresenter();
        //绑定Activity
        mPresenter.onAttch(this);
        //是否完全自定义layout
        if (isCustomLayout()) {
            //创建contentView
            View view = initContentView(LayoutInflater.from(this), savedInstanceState);
            super.setContentView(view);
        } else {
            if (isMaterialDesign()) {
                super.setContentView(R.layout.activity_base_material_design);
            } else {
                super.setContentView(R.layout.activity_base);
            }
            //创建toolbar
            mToolbarHelper = ToolbarHelper.Create(this, initToolbarLayout());
            //创建contentView
            View view = initContentView(LayoutInflater.from(this), savedInstanceState);
            //添加contentView
            FrameLayout base_content = findView(R.id.base_content);
            if (view != null) {
                //交给Persenter去扩展
                mPresenter.initContentView(base_content, view);
            }
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
     * 默认使用base_toolbar
     * 如果不需要toolbar,请复写,并返回0.或者-1
     *
     * @return
     */
    protected int initToolbarLayout() {
        return ToolbarHelper.DEFUATL_BASE_TOOLBAR_V1;
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (isCustomLayout()) {
            super.setContentView(layoutResID);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }

    @Override
    public void setContentView(View view) {
        if (isCustomLayout()) {
            super.setContentView(view);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (isCustomLayout()) {
            super.setContentView(view, params);
        } else {
            throw new IllegalStateException("please setting Presenter Method isCustomLyout() return true ");
        }
    }


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
    protected void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isMaterialDesign() || getToolbarHelper() == null) {
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
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
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

    @Override
    public void isNightMode(boolean isNight) {

    }

    /**
     * 是否启用MaterialDesign风格.
     */
    public boolean isMaterialDesign() {
        return false;
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


    /**
     * 如果设置的主题不是NoActionBar或者initToolbar()返回是0,则返回null.
     *
     * @return mToolbar 可能为null.
     */
    public Toolbar getToolbar() {
        return getToolbarHelper().getToolbar();
    }

    /**
     * 如果修改了initToolbarLayout(),并且<=0的话,该方法将返回null
     *
     * @return
     */
    @Override
    public ToolbarHelper getToolbarHelper() {
        if (mToolbarHelper == null) {
            mToolbarHelper = ToolbarHelper.Create(this, initToolbarLayout());
        }
        return mToolbarHelper;
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

    /**
     * 是否完全自定义布局
     *
     * @return 返回false使用Base_activity, 返回true则需要在initContView里面使用setContentView.
     * 不推荐复写onCreate(),因为子类Presenter的oncreate(),会调用在子类的
     * @see BaseActivity#onCreate(Bundle) 之前,可能造成NullException异常
     */
    @Override
    public abstract boolean isCustomLayout();
}

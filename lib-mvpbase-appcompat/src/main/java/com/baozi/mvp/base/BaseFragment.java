package com.baozi.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.view.BaseFragmentView;


/**
 * @author jlanglang  2016/8/5 9:42
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment
        implements BaseFragmentView {
    protected T mPresenter;
    protected Context mContext;//activity的上下文对象
    protected Bundle mBundle;
    private SparseArray<View> mViews;
    private View mContentView;
    private boolean isInit;


    /**
     * 绑定activity
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        //应该只创建一次Presenter.
        if (mPresenter == null || !isInit) {
            mPresenter = initPresenter();
        }
        mPresenter.onAttach(this);
    }

    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * 运行在onCreate之后
     * 生成view视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = initView(inflater, savedInstanceState);
        } else {
            //缓存的ContentView需要判断是否已有parent， 如果有parent需要从parent删除，否则会抛出异常。
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    /**
     * 运行在onCreateView之后
     * 加载数据,初始化Presenter
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //View做一些初始化操作.
        init(savedInstanceState);
        //初始化Presenter,应该只初始化一次
        if (mPresenter != null && !isInit) {
            isInit = true;
            mPresenter.onCreate();
            //可见时再加载数据刷新视图
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                @Override
                public boolean queueIdle() {
                    mPresenter.initData();
                    return false;
                }
            });
            onPresentersCreate();
        }
    }

    /**
     * 扩展除了默认的presenter的其他Presenter初始化
     */
    protected void onPresentersCreate() {

    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        super.onStart();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (null != mPresenter) {
            if (isVisibleToUser) {
                //相当于Fragment的onResume
                mPresenter.onResume();
            } else {
                //相当于Fragment的onPause
                mPresenter.onPause();
            }
        }
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onDetach() {
        mPresenter.onDetach();
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    public View getContentView() {
        return mContentView;
    }


    /**
     * 类似Activity的OnBackgress
     * fragment进行回退
     */
    @Override
    public void onBack() {
        getFragmentManager().popBackStackImmediate();
    }


    /**
     * 创建Fragment视图
     *
     * @return Fragment视图
     */
    protected abstract View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);

    /**
     * 运行在initView之后
     * 此时已经setContentView
     * 可以做一些初始化操作
     */
    public void init(Bundle savedInstanceState) {

    }

    /**
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
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(this).add(android.R.id.content, tofragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class zclass) {
        Intent intent = new Intent(mContext, zclass);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class zclass, Bundle bundle, int flag) {
        Intent intent = new Intent(mContext, zclass);
        intent.putExtras(bundle);
        intent.addFlags(flag);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class zclass, int flag) {
        Intent intent = new Intent(mContext, zclass);
        intent.addFlags(flag);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public Bundle getBundle() {
        return mBundle;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }

    @Override
    public Window getWindow() {
        return getActivity() == null ? ((Activity) mContext).getWindow() : getActivity().getWindow();
    }

    @Override
    public AppCompatActivity getAppcompatActivity() {
        return (AppCompatActivity) mContext;
    }

    @Override
    public ActionBar getSupportActionBar() {
        return getAppcompatActivity().getSupportActionBar();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getAppcompatActivity().setSupportActionBar(toolbar);
    }

    public SparseArray<View> getViews() {
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
    public <V extends View> V findView(int viewId) {
        View view = getViews().get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            getViews().put(viewId, view);
        }
        return (V) view;
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract T initPresenter();


}

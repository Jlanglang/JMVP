package com.baozi.mvp.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.ui.BaseFragmentView;


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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * 绑定activity
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mViews = new SparseArray<>();
        mPresenter = initPresenter();
        mPresenter.onAttch(this);
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = initView(inflater, savedInstanceState);
        return mContentView;
    }
    /**
     * 运行在onCreateView之后
     * 加载数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建presenter
        if (mPresenter != null) {
            mPresenter.onCreate();
            //加载完成再刷新视图
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                @Override
                public boolean queueIdle() {
                    mPresenter.loadData();
                    return false;
                }
            });
        }
        initListener();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        super.onStart();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            mPresenter.onResume();
        } else {
            //相当于Fragment的onPause
            mPresenter.onPause();
        }
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        mPresenter.onDetach();
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    public View getContentView() {
        return findView(android.R.id.content);
    }

    /**
     * 初始化监听器
     */
    public void initListener() {

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
     * 初始化Fragment应有的视图
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);

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
//        fragmentTransaction.setCustomAnimations(
//                R.anim.translate_fade_in_right,
//                R.anim.translate_fade_out_right, R.anim.translate_fade_in_left, R.anim.translate_fade_out_left);
        fragmentTransaction.hide(this).add(android.R.id.content, tofragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void isNightMode(boolean isNight) {

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
    public ActionBar getSupportActionBar() {
        return ((AppCompatActivity) mContext).getSupportActionBar();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        ((AppCompatActivity) mContext).setSupportActionBar(toolbar);
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId 资源id
     * @return
     */
    public <V extends View> V findView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
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

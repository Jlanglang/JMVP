package com.baozi.mvpdemo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.BaseFragmentView;


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
        mPresenter = initPresenter();
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
        mContentView = initContentView(inflater, savedInstanceState);
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
            mPresenter.onAttch(this);
            mPresenter.onCreate();
        }
        initListener();
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
//        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    public void setToolbar(Toolbar toolbar) {
//        setToolbar(toolbar, false);
//    }
//
//    @Override
//    public void setToolbar(Toolbar toolbar, boolean showTitle) {
//        ((AppCompatActivity) mContext).setSupportActionBar(toolbar);
//        ((AppCompatActivity) mContext).getSupportActionBar().setDisplayShowTitleEnabled(showTitle);
//    }

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
    public abstract View initContentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);

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
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(
//                R.anim.translate_fade_in_right,
//                R.anim.translate_fade_out_right, R.anim.translate_fade_in_left, R.anim.translate_fade_out_left);
        fragmentTransaction.hide(this).add(android.R.id.content, tofragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
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

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract T initPresenter();

    /**
     * 通过viewId获取控件
     *
     * @param viewId 资源id
     * @return
     */
    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }
}

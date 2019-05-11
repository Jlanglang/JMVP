package com.baozi.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.view.UIView;


/**
 * @author jlanglang  2016/8/5 9:42
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment
        implements UIView {
    public final String TAG = this.getClass().getSimpleName();
    protected T mPresenter;
    protected Context mContext;//activity的上下文对象
    protected Bundle mBundle;


    private boolean isInit;
    private boolean first = true;

    private SparseArray<View> mViews;
    private View mContentView;


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
        if (mPresenter == null) {
            return;
        }
        //初始化Presenter,应该只初始化一次
        if (!isInit) {
            isInit = true;
            mPresenter.onCreate();
            onPresentersCreate();
            if (!isLazy()) {
                mPresenter.onRefreshData();
            }
        }
    }

    /**
     * 扩展除了默认的presenter的其他Presenter初始化
     */
    protected void onPresentersCreate() {

    }

    @Override
    public void onNewThrowable(Throwable throwable) {

    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        super.onStart();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (null != mPresenter) {
            if (!hidden) {
                //相当于Fragment的onResume
                mPresenter.onResume();
            } else {
                //相当于Fragment的onPause
                mPresenter.onPause();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isInit()) {//视图未加载
            return;
        }
        if (null != mPresenter) {
            if (isVisibleToUser) {
                if (isLazy() && isFirst()) {//懒加载
                    first = false;
                    mPresenter.onRefreshData();
                    return;
                }
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
    public void onDestroyView() {
        mPresenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        mPresenter.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mPresenter.onPause();
        super.onPause();
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
    public void onBackPressed() {
        getFragmentManager().popBackStackImmediate();
    }

    /**
     * 创建Fragment视图
     *
     * @return Fragment视图
     */
    protected View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        int layout = initView(savedInstanceState);
        return inflater.inflate(layout, null);
    }

    /**
     * 创建Fragment视图
     *
     * @return Fragment视图
     */
    protected abstract int initView(@Nullable Bundle savedInstanceState);

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
     * @param fragment
     */
    @Override
    public void startFragment(Fragment fragment) {
        startFragment(fragment, null);
    }

    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag) {
        startFragment(fragment, tag, true);
    }

    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag, boolean isAdd) {
        startFragment(fragment, tag,
                MVPManager.getEnterAnim(),
                MVPManager.getExitAnim(),
                MVPManager.getEnterPopAnim(),
                MVPManager.getExitPopAnim(), isAdd);
    }

    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag, int enter, int popExit) {
        startFragment(fragment, tag, enter, popExit, true);
    }

    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag, int enter, int popExit, boolean isAddBack) {
        startFragment(fragment, tag,
                enter,
                0,
                0,
                popExit, isAddBack);
    }

    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag, int enterAnim, int exitAnim, int popEnter, int popExit, boolean isAddBack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit);
        fragmentTransaction.add(android.R.id.content, fragment, tag).hide(this);
        if (isAddBack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass) {
        Intent intent = new Intent(mContext, aClass);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, Bundle bundle) {
        Intent intent = new Intent(mContext, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, Bundle bundle, int flag) {
        Intent intent = new Intent(mContext, aClass);
        intent.putExtras(bundle);
        intent.addFlags(flag);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, int flag) {
        Intent intent = new Intent(mContext, aClass);
        intent.addFlags(flag);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    public Bundle getBundle() {
        return mBundle;
    }

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

    public boolean isFinish() {
        return !isAdded();
    }

    @Override
    public void finishActivity() {
        if (getActivity() == null) {
            ((Activity) mContext).finish();
        } else {
            getActivity().finish();
        }
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract T initPresenter();

    /**
     * 视图是否加载
     *
     * @return
     */
    public boolean isInit() {
        return isInit;
    }

    /**
     * 是否第一次加载
     *
     * @return
     */
    public boolean isFirst() {
        return first;
    }

    /**
     * 是否懒加载
     *
     * @return
     */
    public boolean isLazy() {
        return false;
    }
}

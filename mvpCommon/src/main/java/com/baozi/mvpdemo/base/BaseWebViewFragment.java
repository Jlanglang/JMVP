package com.baozi.mvpdemo.base;


import com.baozi.mvpdemo.base.BaseFragment;
import com.baozi.mvpdemo.contract.BaseWebViewContract;
import com.baozi.mvpdemo.presenter.BaseWebViewPresenterImpl;

/**
 * @author jlanglang  2017/1/7 17:20
 * @版本 2.0
 * @Change
 */
public abstract class BaseWebViewFragment<T extends BaseWebViewPresenterImpl> extends BaseFragment<T>
        implements BaseWebViewContract.View {

    @Override
    protected abstract T initPresenter();
}

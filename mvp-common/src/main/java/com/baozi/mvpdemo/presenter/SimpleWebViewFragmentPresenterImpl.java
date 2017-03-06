package com.baozi.mvpdemo.presenter;


import com.baozi.mvpdemo.contract.SimpleWebViewFragmentContract;
import com.baozi.mvpdemo.ui.fragment.SimpleWebViewFragment;

/**
 * Created by Administrator on 2017/01/08
 */

public abstract class SimpleWebViewFragmentPresenterImpl extends BaseWebViewPresenterImpl<SimpleWebViewFragment>
        implements SimpleWebViewFragmentContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
    }
}
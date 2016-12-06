package com.baozi.mvpdemo.contract;

import com.baozi.mvpdemo.ui.view.BaseFragmentView;

import java.util.HashMap;

import rx.Observable;

/**
 */
public class IndexFragmentContract {
    public interface View extends BaseFragmentView {
    }

    public interface Presenter  {
    }

    public interface Model {
        Observable login(HashMap<String,Object> parmas);
    }
}
package com.baozi.mvpdemo.contract;

import com.baozi.mvpdemo.ui.view.BaseActivityView;

import java.util.HashMap;

import rx.Observable;

/**
 */
public class MainActivityContract {
    public interface View extends BaseActivityView {
    }

    public interface Presenter {
    }

    public interface Model {
        Observable login(HashMap<String,Object> parmas);
    }

}
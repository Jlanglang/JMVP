package com.baozi.mvpdemp.contract;

import com.baozi.mvpdemp.location.APIService;
import com.baozi.mvpdemp.ui.view.BaseFragmentView;
import com.baozi.mvpdemp.presenter.BasePresenter;

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
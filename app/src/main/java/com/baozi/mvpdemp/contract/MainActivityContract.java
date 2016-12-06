package com.baozi.mvpdemp.contract;

import com.baozi.mvpdemp.bean.BaseResponse;
import com.baozi.mvpdemp.ui.view.BaseActivityView;
import com.baozi.mvpdemp.presenter.BasePresenter;

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
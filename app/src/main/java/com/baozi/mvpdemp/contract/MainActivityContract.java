package com.baozi.mvpdemp.contract;

import com.baozi.mvpdemp.bean.BaseResponse;
import com.baozi.mvpdemp.ui.view.BaseActivityView;
import com.baozi.mvpdemp.presenter.BasePresenter;

import rx.Observable;

/**
 * Created by baozi on 2016/11/24.
 */
public class MainActivityContract {
    public interface View extends BaseActivityView {
    }

    public interface Presenter {
    }

    public interface Model {
        Observable<String> getData();
    }

}
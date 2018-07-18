package com.baozi.mvp.view;

import com.baozi.mvp.templet.weight.LoadingPager;

/**
 * Created by baozi on 2017/12/8.
 */

public interface LoadView extends BaseView {
    void showSuccess();

    void showEmpty();

    void showError(Throwable throwable, boolean isShowError);

    void showError(Throwable throwable);

    void showLoading();

    void triggerInit();

    LoadingPager getLoadPager();


}

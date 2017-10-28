package com.baozi.mvp.templet.options;

import android.content.Context;
import android.view.View;

import com.baozi.mvp.templet.weight.LoadingPager;

/**
 * Created by baozi on 2017/10/25.
 */

public class ContentOptions {
    private int emptyLayout;
    private int errorLayout;
    private int loadingLayout;
    private boolean isOpenLoading;

    public static ContentOptions create() {
        return new ContentOptions();
    }

    public View build(Context context) {
        LoadingPager loadingPager = new LoadingPager(context);
        loadingPager.setEmptyLayout(emptyLayout);
        loadingPager.setErrorLayout(errorLayout);
        loadingPager.setLoadingLayout(loadingLayout);
        return loadingPager;
    }
}


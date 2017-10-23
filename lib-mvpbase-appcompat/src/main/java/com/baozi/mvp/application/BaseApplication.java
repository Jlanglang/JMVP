package com.baozi.mvp.application;

import android.app.Application;

/**
 * Created by baozi on 2017/3/8.
 */
public class BaseApplication extends Application {
    private static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Application getContext() {
        return mContext;
    }
}

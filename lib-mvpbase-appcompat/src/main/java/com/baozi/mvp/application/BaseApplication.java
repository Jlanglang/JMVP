package com.baozi.mvp.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by baozi on 2017/3/8.
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}

package com.baozi.jmvp.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by baozi on 2017/3/8.
 */
public class JBaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }
}

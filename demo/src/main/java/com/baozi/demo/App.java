package com.baozi.demo;

import android.app.Application;

import com.baozi.mvp.MVPManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MVPManager.setToolbarOptions(
                MVPManager.getToolbarOptions()
                        .setStatusDrawable(R.drawable.bg_toolbar)//设置状态栏的颜色
                        .setToolbarDrawable(R.drawable.bg_toolbar)
                        .setToolbarHeight(48)
                        .setOtherTextColor(getResources().getColor(android.R.color.black))
                        .setTitleColor(getResources().getColor(android.R.color.holo_red_dark))
        );

    }
}

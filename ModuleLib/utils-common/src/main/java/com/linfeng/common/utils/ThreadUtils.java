package com.linfeng.common.utils;


import android.os.Handler;

/**
 */
public class ThreadUtils {

    /**
     * 运行在子线程里面的task
     *
     * @param task
     */
    public static void runInThread(Runnable task) {
        new Thread(task).start();
    }

    /**
     * 运行在ui线程里面的task
     */
    private static Handler mHandler = new Handler();

    public static void runInUIThread(Runnable task) {
        mHandler.post(task);
    }

    public static Handler getmHandler() {
        return mHandler;
    }
}

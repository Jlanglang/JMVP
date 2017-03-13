package com.linfeng.common.utils;

/**
 * Created by 123456 on 2016-2-2.
 */

import android.util.Log;

/**
 *
 * 开发阶段将下面LOG_LEVEL 设置为6这样所有的log都能显示，
 * 在发布的时候我们将LOG_LEVEL 设置为0.这样log就非常方便管理了
 *
 */
public class HLog {
    public final static int LOG_LEVEL = 6;
    public final static int ERROR = 1;
    public final static int WARN = 2;
    public final static int INFO = 3;
    public final static int DEBUG = 4;
    public final static int VERBOS = 5;


    public static void e(String tag, String msg){
        if(LOG_LEVEL>ERROR)
            Log.e(tag, msg);
    }

    public static void w(String tag, String msg){
        if(LOG_LEVEL>WARN)
            Log.w(tag, msg);
    }
    public static void i(String tag, String msg){
        if(LOG_LEVEL>INFO)
            Log.i(tag, msg);
    }
    public static void d(String tag, String msg){
        if(LOG_LEVEL>DEBUG)
            Log.d(tag, msg);
    }
    public static void v(String tag, String msg){
        if(LOG_LEVEL>VERBOS)
            Log.v(tag, msg);
    }
}

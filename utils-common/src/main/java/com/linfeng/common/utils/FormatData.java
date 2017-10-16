package com.linfeng.common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/11.
 */
public class FormatData {
    private static final String TAG = "FormatData";
    private static Date date = new Date();
    //转化时间
    public static Date getData(String s){
        if (!TextUtils.isEmpty(s)){
            long l = Long.parseLong(s);
            date.setTime(l);
            return date;
        }
        return null;
    }
    public static long getTime(String date){
        if(!TextUtils.isEmpty(date)){
            SimpleDateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parse = dateInstance.parse(date);
                long time = parse.getTime();
                HLog.d(TAG, "getTime() called with: " + "time = [" + time +"------------"+ System.currentTimeMillis()+ "]");
                return time;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
}

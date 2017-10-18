package com.linfeng.rx_retrofit_network.factory;

import android.text.TextUtils;

import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.location.APIException;

/**
 * Created by baozi on 2017/10/18.
 */

public class NetWorkErrorFactory {
    public static String getError(Throwable throwable) {
        Class<? extends Throwable> throwableClass = throwable.getClass();
        //自定义异常
        if (throwableClass.equals(APIException.class)) {
            return throwable.getMessage();
        }
        //如果该异常未定义.获取默认
        String errorMsg = NetWorkManager.getErrorMsg(throwableClass);
        if (TextUtils.isEmpty(errorMsg)) {
            //获取默认异常
            return NetWorkManager.getErrorMsg(Exception.class);
        }
        return errorMsg;
    }
}

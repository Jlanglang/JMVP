package com.linfeng.rx_retrofit_network.factory;

import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.location.APIException;
import com.linfeng.rx_retrofit_network.location.onExceptionListener;

/**
 * Created by baozi on 2017/10/18.
 */

public class NetWorkErrorFactory {
    /**
     * 异常时处理工厂
     *
     * @param throwable 异常
     * @return 异常提示消息
     */
    public static String disposeError(Throwable throwable) {
        Class<? extends Throwable> throwableClass = throwable.getClass();
        //处理Api自定义异常处理,说明请求成功
        if (throwableClass.equals(APIException.class)) {
            return throwable.getMessage();
        }
        //处理error异常,http异常
        onExceptionListener exceptionListener = NetWorkManager.getExceptionListener();
        if (exceptionListener != null) {
            return exceptionListener.onError(throwable);
        }
        return "";
    }
}

package com.linfeng.rx_retrofit_network.location;

/**
 * Created by baozi on 2017/12/21.
 */

public interface onExceptionListener {
    /**
     * @param throwable 发生的异常
     * @return 返回提示
     */
    String onError(Throwable throwable);
}

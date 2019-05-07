package com.linfeng.rx_retrofit_network.location;

/**
 * Created by baozi on 2017/10/18.
 */

public interface APICallBack {
    /**
     * @param resultData 网络请求数据
     * @return error 消息
     */
    String callback(String code, String resultData);
}

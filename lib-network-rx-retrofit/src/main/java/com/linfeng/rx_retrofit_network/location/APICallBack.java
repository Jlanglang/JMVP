package com.linfeng.rx_retrofit_network.location;

import com.linfeng.rx_retrofit_network.location.model.BaseResponse;

/**
 * Created by baozi on 2017/10/18.
 */

public interface APICallBack {
    /**
     * @param baseResponse 网络请求数据
     * @return error 消息
     */
    String callback(int code, BaseResponse baseResponse);
}

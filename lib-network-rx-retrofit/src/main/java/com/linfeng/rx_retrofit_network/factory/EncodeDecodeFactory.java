package com.linfeng.rx_retrofit_network.factory;

import com.linfeng.rx_retrofit_network.NetWorkConfig;
import com.linfeng.rx_retrofit_network.location.des.Des;

public class EncodeDecodeFactory {
    public static String encode(String data) {
        if (NetWorkConfig.getKey().isNotEncode()) {
            return data;
        }
        if (NetWorkConfig.getKey().isRSA()) {

        }
        return Des.encode(NetWorkConfig.getKey().getPrivateKey(), data);
    }

    public static String decode(String data) {
        if (!NetWorkConfig.getKey().isNotEncode()) {
            return data;
        }
        if (NetWorkConfig.getKey().isRSA()) {

        }
        return Des.decode(NetWorkConfig.getKey().getPrivateKey(), data);
    }
}

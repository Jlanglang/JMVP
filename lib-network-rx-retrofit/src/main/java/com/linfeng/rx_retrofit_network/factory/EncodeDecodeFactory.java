package com.linfeng.rx_retrofit_network.factory;

import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.location.des.Des;

/**
 * 加密解密工厂
 */
public class EncodeDecodeFactory {
    /**
     * 加密
     *
     * @param data 加密内容
     * @return 密文
     */
    public static String encode(String data) {
        //不加密
        if (NetWorkManager.getKey().isNotEncode()) {
            return data;
        }
        //RSA
        if (NetWorkManager.getKey().isRSA()) {

        }
        //DES
        return Des.encode(NetWorkManager.getKey().getPrivateKey(), data);
    }

    /**
     * 解密
     *
     * @param data 密文
     * @return 内容
     */
    public static String decode(String data) {
        if (!NetWorkManager.getKey().isNotEncode()) {
            return data;
        }
        if (NetWorkManager.getKey().isRSA()) {

        }
        return Des.decode(NetWorkManager.getKey().getPrivateKey(), data);
    }
}

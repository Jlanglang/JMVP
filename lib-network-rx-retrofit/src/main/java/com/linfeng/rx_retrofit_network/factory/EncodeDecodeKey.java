package com.linfeng.rx_retrofit_network.factory;

import android.text.TextUtils;

public class EncodeDecodeKey {
    /**
     * 私钥
     */
    private final String privateKey;
    /**
     * 公钥
     */
    private final String publicKey;

    /**
     * 加密方式
     * 当publicKey为null或者"",则为DES加密
     */
    private final boolean isRSA;
    /**
     * 是否加密
     * 当privateKey为null或者"",则不加密
     */
    private final boolean isNotEncode;

    public EncodeDecodeKey(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        isNotEncode = TextUtils.isEmpty(privateKey);
        isRSA = !TextUtils.isEmpty(publicKey);
    }

    /**
     * 限制只能当前包下访问
     *
     * @return
     */
    String getPrivateKey() {
        return privateKey;
    }

    String getPublicKey() {
        return publicKey;
    }

    /**
     * @return 是否为RSA加密, 当公钥为null时, 非RSA加密
     */
    boolean isRSA() {
        return isRSA;
    }

    /**
     * @return 是否加密, 当公钥私钥都为null时,不加密
     */
    boolean isNotEncode() {
        return isNotEncode;
    }
}
package com.linfeng.rx_retrofit_network;


import com.linfeng.rx_retrofit_network.factory.EncodeDecodeKey;

public class NetWorkConfig {
    private static String privateKey;
    private static String publicKey;

    public static void init(String key1, String key2) {
        privateKey = key1;
        publicKey = key2;
    }

    public static EncodeDecodeKey getKey() {
        return Instance.key;
    }

    private static class Instance {
        private static EncodeDecodeKey key = getKey();

        private static EncodeDecodeKey getKey() {
            return new EncodeDecodeKey(privateKey, publicKey);
        }
    }
}

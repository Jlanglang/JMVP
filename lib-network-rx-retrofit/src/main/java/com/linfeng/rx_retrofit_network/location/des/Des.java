package com.linfeng.rx_retrofit_network.location.des;


import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class Des {
    public static final String ALGORITHM_DES = "DES";

    public static String encode(String key, String data){
        return encode(key, data.getBytes());
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String key, byte[] data) {
        try {
             // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes("utf-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,sr);
            byte[] bytes = cipher.doFinal(data);
            return Base64.encode(bytes);
        } catch (Exception e) {
            return "";
        }
    }

    public static String decode(String key, String data) {
        try {
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            byte[] decode = Base64.decode(data);
            DESKeySpec dks = new DESKeySpec(key.getBytes("utf-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey,sr);
            byte bytes[] = cipher.doFinal(decode);
            return new String(bytes);
        } catch (Exception e) {
            return "";
        }
    }
}

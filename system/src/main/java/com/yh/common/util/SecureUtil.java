package com.yh.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * 通用安全工具类
 */
public class SecureUtil {

    //必须保证16个字节
    public static final String AES_KEY = "ltsk@#$123456789";

    public static String aesDecrypt(String data) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AES_KEY.getBytes());
        return aes.decryptStr(data, CharsetUtil.CHARSET_UTF_8);
    }

    public static String aesEncrypt(String data) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AES_KEY.getBytes());
        return aes.encryptBase64(data);
    }


    public static void main(String[] args) {
        String s = aesEncrypt("123456");
        System.out.println(s);
    }
}

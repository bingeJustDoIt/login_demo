package com.example.login_demo.util;


import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author gogo
 */
public class SymmetricCryptoUtil {
    /**
     * 16字节
     */
    private static final String ENCODE_KEY = "1234567812345678";
    private static final String IV_KEY = "0000000000000000";

    public static void main(String[] args) {
        String encryptData = encryptFromString("zdm321123.", Mode.CBC, Padding.ZeroPadding);
        System.out.println("加密：" + encryptData);
        String decryptData = decryptFromString(encryptData, Mode.CBC, Padding.ZeroPadding);
        System.out.println("解密：" + decryptData);
    }


    public static String encrypt(String data){
        return encryptFromString(data, Mode.CBC, Padding.ZeroPadding);
    }
    public static String decrypt(String data){
        return decryptFromString(data, Mode.CBC, Padding.ZeroPadding);
    }


    public static String encryptFromString(String data, Mode mode, Padding padding) {
        AES aes;
        if (Mode.CBC == mode) {
            aes = new AES(mode, padding,
                    new SecretKeySpec(ENCODE_KEY.getBytes(), "AES"),
                    new IvParameterSpec(IV_KEY.getBytes()));
        } else {
            aes = new AES(mode, padding,
                    new SecretKeySpec(ENCODE_KEY.getBytes(), "AES"));
        }
        return aes.encryptBase64(data, StandardCharsets.UTF_8);
    }

    public static String decryptFromString(String data, Mode mode, Padding padding) {
        AES aes;
        if (Mode.CBC == mode) {
            aes = new AES(mode, padding,
                    new SecretKeySpec(ENCODE_KEY.getBytes(), "AES"),
                    new IvParameterSpec(IV_KEY.getBytes()));
        } else {
            aes = new AES(mode, padding,
                    new SecretKeySpec(ENCODE_KEY.getBytes(), "AES"));
        }
        byte[] decryptDataBase64 = aes.decrypt(data);
        return new String(decryptDataBase64, StandardCharsets.UTF_8);
    }

}


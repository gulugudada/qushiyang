package com.nengguoqieguo.utils;

import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用户密码加密类
 */
public class SHA_1 {
    public static String jdkSHA1(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(src.getBytes());
            String s = Hex.encodeHexString(md.digest());
            System.out.println("jdk sha-1 : " + s);
            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

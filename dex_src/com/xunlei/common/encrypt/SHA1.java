package com.xunlei.common.encrypt;

import com.umeng.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
    public static String encrypt(String str) {
        byte[] encrypt = encrypt(str.getBytes());
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (encrypt == null) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder(32);
        for (byte b : encrypt) {
            stringBuilder.append(cArr[(b >> 4) & 15]).append(cArr[b & 15]);
        }
        return stringBuilder.toString();
    }

    public static byte[] encrypt(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

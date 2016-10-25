package com.xunlei.tdlive.util;

import anet.channel.security.ISecurity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// compiled from: Md5.java
public class g {
    public static String a(String str) {
        return a(str.getBytes());
    }

    public static String a(byte[] bArr) {
        try {
            char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(bArr, 0, bArr.length);
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder(32);
            for (byte b : digest) {
                stringBuilder.append(cArr[(b >> 4) & 15]).append(cArr[(b >> 0) & 15]);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new String(bArr);
        }
    }
}

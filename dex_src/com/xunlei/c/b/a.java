package com.xunlei.c.b;

import com.xunlei.xiazaibao.BuildConfig;
import java.security.MessageDigest;

public final class a {
    public static String a(byte[] bArr) {
        try {
            return new String(com.xunlei.c.a.a.a(MessageDigest.getInstance("MD5").digest(bArr)));
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }
}

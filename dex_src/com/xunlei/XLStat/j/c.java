package com.xunlei.XLStat.j;

import com.xunlei.xiazaibao.BuildConfig;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class c {
    private static final String[] a;

    static {
        a = new String[]{"0", com.xunlei.analytics.b.c.f, com.xunlei.analytics.b.c.e, com.xunlei.analytics.b.c.c, com.xunlei.analytics.b.c.d, "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    }

    private static String a(byte b) {
        if (b < null) {
            b += 256;
        }
        return a[b / 16] + a[b % 16];
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(a(b));
        }
        return stringBuffer.toString();
    }

    public static String a(String str) {
        if (str == null) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            String str2 = new String(str);
            try {
                return a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
            } catch (NoSuchAlgorithmException e) {
                NoSuchAlgorithmException noSuchAlgorithmException = e;
                String str3 = str2;
                NoSuchAlgorithmException noSuchAlgorithmException2 = noSuchAlgorithmException;
            }
        } catch (NoSuchAlgorithmException e2) {
            noSuchAlgorithmException2 = e2;
            str3 = null;
            noSuchAlgorithmException2.printStackTrace();
            return str3;
        }
    }

    public static byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        try {
            return MessageDigest.getInstance("MD5").digest(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return bArr2;
        }
    }
}

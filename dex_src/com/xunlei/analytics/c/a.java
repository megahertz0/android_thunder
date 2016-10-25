package com.xunlei.analytics.c;

import com.xunlei.analytics.b.c;
import com.xunlei.common.encrypt.CharsetConvert;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class a {
    private static final String a = "AES";
    private static final String[] b;

    static {
        b = new String[]{"0", c.f, c.e, c.c, c.d, "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    }

    private static String a(byte b) {
        int i;
        if (b < null) {
            i = b + 256;
        }
        return b[(i >>> 4) & 15] + b[i & 15];
    }

    public static String a(Map<String, String> map, String str) {
        int i = 0;
        Vector vector = new Vector();
        for (String str2 : map.keySet()) {
            vector.add(str2 + "=" + ((String) map.get(str2)));
        }
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            for (int length = strArr.length - 1; length > i2; length--) {
                if (strArr[length].compareTo(strArr[length - 1]) < 0) {
                    String str3 = strArr[length];
                    strArr[length] = strArr[length - 1];
                    strArr[length - 1] = str3;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (i < strArr.length) {
            stringBuilder.append(strArr[i]);
            i++;
        }
        stringBuilder.append(str);
        return b(stringBuilder.toString());
    }

    public static String a(byte[] bArr) {
        try {
            return c(MessageDigest.getInstance("MD5").digest(bArr));
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] a(String str) {
        byte[] toByteArray;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(CharsetConvert.UTF_8));
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Exception e) {
                Exception e2 = e;
                e2.printStackTrace();
                return toByteArray;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            toByteArray = null;
            e2 = exception;
            e2.printStackTrace();
            return toByteArray;
        }
        return toByteArray;
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            return a(bArr, MessageDigest.getInstance("MD5").digest(str.getBytes(CharsetConvert.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(b(bArr2).getEncoded(), a);
        Cipher instance = Cipher.getInstance(a);
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static String b(String str) {
        try {
            return c(MessageDigest.getInstance("MD5").digest(str.getBytes(CharsetConvert.UTF_8)));
        } catch (Exception e) {
            return null;
        }
    }

    private static Key b(byte[] bArr) {
        return new SecretKeySpec(bArr, a);
    }

    private static String c(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(a(b));
        }
        return stringBuffer.toString();
    }
}

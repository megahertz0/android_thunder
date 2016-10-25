package com.tencent.map.b;

import anet.channel.security.ISecurity;
import com.umeng.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public final class j {
    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            String str2 = a.d;
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toHexString(b & 255)).append(str2);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return str;
        }
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
        try {
            deflaterOutputStream.write(bArr, 0, bArr.length);
            deflaterOutputStream.finish();
            deflaterOutputStream.flush();
            deflaterOutputStream.close();
            bArr2 = byteArrayOutputStream.toByteArray();
            return bArr2;
        } catch (Exception e) {
            return bArr2;
        }
    }

    public static byte[] b(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream);
        Object obj = new Object[0];
        Object obj2 = new Object[1024];
        while (true) {
            try {
                int read = inflaterInputStream.read(obj2);
                if (read > 0) {
                    i += read;
                    byte[] bArr2 = new byte[i];
                    System.arraycopy(obj, 0, bArr2, 0, obj.length);
                    System.arraycopy(obj2, 0, bArr2, obj.length, read);
                } else {
                    Object obj3 = obj;
                }
                if (read <= 0) {
                    try {
                        byteArrayInputStream.close();
                        inflaterInputStream.close();
                        return obj3;
                    } catch (IOException e) {
                        return null;
                    }
                }
                obj = obj3;
            } catch (Exception e2) {
                return null;
            }
        }
    }
}

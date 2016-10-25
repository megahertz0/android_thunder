package com.umeng.analytics;

import anet.channel.security.ISecurity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import u.aly.v;

// compiled from: DataHelper.java
public class b {
    private static final byte[] a;

    static {
        a = new byte[]{(byte) 10, (byte) 1, (byte) 11, (byte) 5, (byte) 4, (byte) 15, (byte) 7, (byte) 9, (byte) 23, (byte) 3, (byte) 1, (byte) 6, (byte) 8, (byte) 12, (byte) 13, (byte) 91};
    }

    public static byte[] a(String str) {
        byte[] bArr = null;
        if (str != null) {
            int length = str.length();
            if (length % 2 == 0) {
                bArr = new byte[(length / 2)];
                for (int i = 0; i < length; i += 2) {
                    bArr[i / 2] = (byte) Integer.valueOf(str.substring(i, i + 2), R.styleable.Toolbar_titleMarginBottom).intValue();
                }
            }
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(a));
        return instance.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(a));
        return instance.doFinal(bArr);
    }

    public static int a(int i, String str) {
        int i2;
        if (((double) new Random().nextFloat()) < 0.001d) {
            if (str == null) {
                v.c("null signature..");
            }
            i2 = 0;
            try {
                i2 = Integer.parseInt(str.substring(XZBDevice.Pause, XZBDevice.Success), R.styleable.Toolbar_titleMarginBottom);
            } catch (Exception e) {
            }
            return (i2 | 128) * 1000;
        }
        i2 = new Random().nextInt(i);
        return (i2 > 255000 || i2 < 128000) ? i2 : 127000;
    }
}

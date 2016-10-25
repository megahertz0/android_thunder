package com.xiaomi.channel.commonutils.string;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class d {
    public static String a(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(R.styleable.AppCompatTheme_editTextColor)));
        }
        return stringBuffer.toString();
    }

    public static String a(String str) {
        if (str == null) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(c(str));
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            return String.format("%1$032X", new Object[]{bigInteger});
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    public static String a(Collection<?> collection, String str) {
        return collection == null ? null : a(collection.iterator(), str);
    }

    public static String a(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return BuildConfig.VERSION_NAME;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next.toString();
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        if (next != null) {
            stringBuffer.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                stringBuffer.append(str);
            }
            next = it.next();
            if (next != null) {
                stringBuffer.append(next);
            }
        }
        return stringBuffer.toString();
    }

    public static String a(byte[] bArr) {
        String str = BuildConfig.VERSION_NAME;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            str = String.format("%1$032X", new Object[]{bigInteger});
        } catch (Exception e) {
        }
        return str.toLowerCase();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(c(str));
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            return String.format("%1$032X", new Object[]{bigInteger});
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    public static byte[] c(String str) {
        try {
            return str.getBytes(CharsetConvert.UTF_8);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }
}

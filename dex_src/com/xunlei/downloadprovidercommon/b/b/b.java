package com.xunlei.downloadprovidercommon.b.b;

import android.text.TextUtils;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// compiled from: Signature.java
public final class b {
    private static final Random a;
    private final String b;

    static {
        a = new Random();
    }

    public b(String str) {
        this.b = str;
    }

    static long a() {
        return System.currentTimeMillis() / 1000;
    }

    static String b() {
        return String.valueOf(a.nextInt());
    }

    private static String a(String str) {
        try {
            return URLEncoder.encode(str, CharsetConvert.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }

    public static String a(String str, String str2, List<a> list, String str3) {
        String str4 = "%s&%s&%s";
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[1] = a(str2);
        StringBuilder stringBuilder = new StringBuilder();
        if (!(list == null || list.isEmpty())) {
            Collections.sort(list);
            int i = 0;
            for (a aVar : list) {
                if (i > 0) {
                    stringBuilder.append("%26");
                }
                stringBuilder.append(a(aVar.a)).append("%3D").append(a(aVar.b));
                i++;
            }
        }
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append("%26").append(str3);
        }
        objArr[2] = stringBuilder.toString();
        return String.format(str4, objArr);
    }

    public final String b(String str, String str2, List<a> list, String str3) {
        return b(a(str, str2, list, str3));
    }

    private String b(String str) {
        try {
            Key secretKeySpec = new SecretKeySpec(this.b.getBytes(CharsetConvert.UTF_8), "HmacSHA1");
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            return com.xunlei.xllib.b.b.a(instance.doFinal(str.getBytes(CharsetConvert.UTF_8)));
        } catch (Throwable e) {
            throw new IllegalArgumentException("Signature args err", e);
        }
    }
}

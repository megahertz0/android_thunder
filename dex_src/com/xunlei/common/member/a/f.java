package com.xunlei.common.member.a;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: XLDeviceInfo.java
public final class f {
    public static String a() {
        return VERSION.RELEASE;
    }

    public static String b() {
        return Build.MODEL;
    }

    public static String c() {
        String toLowerCase = Build.MANUFACTURER.toLowerCase();
        String toLowerCase2 = Build.MODEL.toLowerCase();
        if (toLowerCase2.startsWith(toLowerCase)) {
            return d.c(toLowerCase2);
        }
        Object c = d.c(toLowerCase);
        return TextUtils.isEmpty(c) ? d.c(toLowerCase2) : c + " " + d.c(toLowerCase2);
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.VERSION_NAME;
        }
        char[] toCharArray = str.toCharArray();
        String str2 = BuildConfig.VERSION_NAME;
        int length = toCharArray.length;
        int i = 1;
        for (int i2 = 0; i2 < length; i2++) {
            char c = toCharArray[i2];
            Object obj;
            if (obj == null || !Character.isLetter(c)) {
                if (Character.isWhitespace(c)) {
                    i = 1;
                }
                str2 = str2 + c;
            } else {
                str2 = str2 + Character.toUpperCase(c);
                obj = null;
            }
        }
        return str2;
    }
}

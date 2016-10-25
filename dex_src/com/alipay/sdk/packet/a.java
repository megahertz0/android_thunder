package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.h;

public final class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.umeng.a.d;
        }
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split.length == 0) {
            return com.umeng.a.d;
        }
        int length = split.length;
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        for (int i = 0; i < length; i++) {
            String str2 = split[i];
            if (TextUtils.isEmpty(obj4)) {
                obj4 = !str2.contains("biz_type") ? null : e(str2);
            }
            if (TextUtils.isEmpty(obj3)) {
                obj3 = !str2.contains("biz_no") ? null : e(str2);
            }
            if (TextUtils.isEmpty(obj2)) {
                obj2 = (!str2.contains(c.F) || str2.startsWith(c.E)) ? null : e(str2);
            }
            if (TextUtils.isEmpty(obj)) {
                if (str2.contains("app_userid")) {
                    obj = e(str2);
                } else {
                    obj = null;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append(new StringBuilder("biz_type=").append(obj4).append(h.b).toString());
        }
        if (!TextUtils.isEmpty(obj3)) {
            stringBuilder.append(new StringBuilder("biz_no=").append(obj3).append(h.b).toString());
        }
        if (!TextUtils.isEmpty(obj2)) {
            stringBuilder.append(new StringBuilder("trade_no=").append(obj2).append(h.b).toString());
        }
        if (!TextUtils.isEmpty(obj)) {
            stringBuilder.append(new StringBuilder("app_userid=").append(obj).append(h.b).toString());
        }
        String toString = stringBuilder.toString();
        return toString.endsWith(h.b) ? toString.substring(0, toString.length() - 1) : toString;
    }

    private static String b(String str) {
        return !str.contains("biz_type") ? null : e(str);
    }

    private static String c(String str) {
        return !str.contains("biz_no") ? null : e(str);
    }

    private static String d(String str) {
        return (!str.contains(c.F) || str.startsWith(c.E)) ? null : e(str);
    }

    private static String e(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        return str2.contains(h.f) ? str2.replaceAll(h.f, com.umeng.a.d) : str2;
    }

    private static String f(String str) {
        return !str.contains("app_userid") ? null : e(str);
    }
}

package com.xunlei.downloadprovider.a;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// compiled from: DateTimeUtil.java
public class f {
    private static final String a;

    static {
        a = f.class.getSimpleName();
    }

    public static String a() {
        return new SimpleDateFormat("yyyy.MM.dd").format(new Date());
    }

    public static String a(long j) {
        return j > 0 ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j)) : null;
    }

    public static String a(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
            if (currentTimeMillis <= 0) {
                return BuildConfig.VERSION_NAME;
            }
            currentTimeMillis /= 1000;
            long j = currentTimeMillis / 60;
            long j2 = j / 60;
            long j3 = j2 / 24;
            new StringBuilder("day = ").append(j3).append("; hour = ").append(j2).append("; minute = ").append(j).append("; second = ").append(currentTimeMillis);
            if (j3 >= 30) {
                return "\u4e00\u4e2a\u6708";
            }
            if (j3 > 0) {
                return j3 + "\u5929";
            }
            if (j2 > 0) {
                return j2 + "\u5c0f\u65f6";
            }
            return j > 0 ? j + "\u5206\u949f" : "1\u5206\u949f";
        } catch (Exception e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    public static String a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = i / 31104000;
        int i3 = i - (31104000 * i2);
        int i4 = i3 / 2592000;
        i3 -= 2592000 * i4;
        int i5 = i3 / 86400;
        i3 -= 86400 * i5;
        int i6 = i3 / 3600;
        i3 -= i6 * 3600;
        int i7 = i3 / 60;
        i3 -= i7 * 60;
        if (i2 > 0) {
            stringBuilder.append(String.valueOf(i2) + "\u5e74");
        }
        if (i4 > 0) {
            stringBuilder.append(String.valueOf(i4) + "\u6708");
        }
        if (i5 > 0) {
            stringBuilder.append(String.valueOf(i5) + "\u65e5");
        }
        if (i6 > 0) {
            stringBuilder.append(String.valueOf(i6) + "\u5c0f\u65f6");
        }
        if (i7 > 0) {
            stringBuilder.append(String.valueOf(i7) + "\u5206");
        }
        stringBuilder.append(String.valueOf(i3) + "\u79d2");
        return stringBuilder.toString();
    }

    public static String b(long j) {
        return j > 0 ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j)) : "--";
    }

    public static long c(long j) {
        return j / 3600;
    }

    public static long d(long j) {
        return (j - ((j / 3600) * 3600)) / 60;
    }

    public static String e(long j) {
        String str = BuildConfig.VERSION_NAME;
        if (j <= 0) {
            return str;
        }
        long j2 = j / 3600;
        long d = d(j);
        long j3 = j % 60;
        if (j2 > 0) {
            str = j2 + "\u5c0f\u65f6";
        }
        if (d > 0) {
            str = str + d + "\u5206\u949f";
        }
        return (j3 <= 0 || !TextUtils.isEmpty(str)) ? str : j3 + "\u79d2";
    }
}

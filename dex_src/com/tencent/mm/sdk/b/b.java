package com.tencent.mm.sdk.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;

public final class b {
    private static a aG;
    private static a aH;
    private static final String aI;
    private static int level;

    public static interface a {
        void f(String str, String str2);

        void g(String str, String str2);

        int getLogLevel();

        void h(String str, String str2);

        void i(String str, String str2);
    }

    static {
        level = 6;
        a cVar = new c();
        aG = cVar;
        aH = cVar;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(new StringBuilder("VERSION.RELEASE:[").append(VERSION.RELEASE).toString());
            stringBuilder.append(new StringBuilder("] VERSION.CODENAME:[").append(VERSION.CODENAME).toString());
            stringBuilder.append(new StringBuilder("] VERSION.INCREMENTAL:[").append(VERSION.INCREMENTAL).toString());
            stringBuilder.append(new StringBuilder("] BOARD:[").append(Build.BOARD).toString());
            stringBuilder.append(new StringBuilder("] DEVICE:[").append(Build.DEVICE).toString());
            stringBuilder.append(new StringBuilder("] DISPLAY:[").append(Build.DISPLAY).toString());
            stringBuilder.append(new StringBuilder("] FINGERPRINT:[").append(Build.FINGERPRINT).toString());
            stringBuilder.append(new StringBuilder("] HOST:[").append(Build.HOST).toString());
            stringBuilder.append(new StringBuilder("] MANUFACTURER:[").append(Build.MANUFACTURER).toString());
            stringBuilder.append(new StringBuilder("] MODEL:[").append(Build.MODEL).toString());
            stringBuilder.append(new StringBuilder("] PRODUCT:[").append(Build.PRODUCT).toString());
            stringBuilder.append(new StringBuilder("] TAGS:[").append(Build.TAGS).toString());
            stringBuilder.append(new StringBuilder("] TYPE:[").append(Build.TYPE).toString());
            stringBuilder.append(new StringBuilder("] USER:[").append(Build.USER).append("]").toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        aI = stringBuilder.toString();
    }

    public static void a(String str, String str2, Object... objArr) {
        if (aH != null && aH.getLogLevel() <= 4) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = com.umeng.a.d;
            }
            a aVar = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.i(str, format);
        }
    }

    public static void b(String str, String str2) {
        a(str, str2, null);
    }

    public static void c(String str, String str2) {
        if (aH != null && aH.getLogLevel() <= 3) {
            a aVar = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.h(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (aH != null && aH.getLogLevel() <= 2) {
            a aVar = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.f(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (aH != null && aH.getLogLevel() <= 1) {
            if (str2 == null) {
                str2 = com.umeng.a.d;
            }
            a aVar = aH;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.g(str, str2);
        }
    }
}

package com.xunlei.analytics.c;

import android.content.Context;
import com.xunlei.xiazaibao.BuildConfig;

public class g {
    private static final String a = "hubble_sdk_setting";
    private static final String b = "delete_log_count";
    private static final String c = "last_upload_time";
    private static final String d = "hubble_xl_guid";
    private static final String e = "last_systemInfo_save_time";

    public static int a(Context context) {
        return context.getSharedPreferences(a, 0).getInt(b, 0);
    }

    public static void a(Context context, int i) {
        context.getSharedPreferences(a, 0).edit().putInt(b, i).commit();
    }

    public static void a(Context context, String str) {
        context.getSharedPreferences(a, 0).edit().putString(d, str).commit();
    }

    public static void b(Context context) {
        context.getSharedPreferences(a, 0).edit().putLong(c, System.currentTimeMillis()).commit();
    }

    public static long c(Context context) {
        return context.getSharedPreferences(a, 0).getLong(c, 0);
    }

    public static String d(Context context) {
        return context.getSharedPreferences(a, 0).getString(d, BuildConfig.VERSION_NAME);
    }

    public static void e(Context context) {
        context.getSharedPreferences(a, 0).edit().putLong(e, System.currentTimeMillis()).commit();
    }

    public static long f(Context context) {
        return context.getSharedPreferences(a, 0).getLong(e, 0);
    }
}

package com.xunlei.downloadprovider.util;

import android.content.Context;
import com.xunlei.downloadprovider.a.b;

// compiled from: UtilSharedPreference.java
public final class aa {

    // compiled from: UtilSharedPreference.java
    public static class a {
        protected static String a;

        static {
            a = null;
        }

        private static String a(Context context) {
            if (a == null) {
                a = new StringBuilder("version_once_shared_preference_").append(b.g(context)).toString().replace('.', '_');
            }
            return a;
        }

        public static void a(Context context, String str, boolean z) {
            context.getSharedPreferences(a(context), 0).edit().putBoolean(str, z).commit();
        }

        public static boolean b(Context context, String str, boolean z) {
            return context.getSharedPreferences(a(context), 0).getBoolean(str, z);
        }
    }

    public static void a(Context context, String str, long j) {
        context.getSharedPreferences("init", 0).edit().putLong(str, j).commit();
    }

    public static long a(Context context, String str) {
        return context.getSharedPreferences("init", 0).getLong(str, 0);
    }

    public static void a(Context context, String str, String str2) {
        context.getSharedPreferences("init", 0).edit().putString(str, str2).commit();
    }

    public static String b(Context context, String str) {
        return context.getSharedPreferences("init", 0).getString(str, com.umeng.a.d);
    }

    public static void a(Context context, String str, boolean z) {
        context.getSharedPreferences("init", 0).edit().putBoolean(str, z).commit();
    }

    public static boolean c(Context context, String str) {
        return context.getSharedPreferences("init", 0).getBoolean(str, false);
    }
}

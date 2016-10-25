package com.tencent.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class p {
    private static SharedPreferences a;

    static {
        a = null;
    }

    public static int a(Context context, String str, int i) {
        return a(context).getInt(k.b(context, str), i);
    }

    public static long a(Context context, String str, long j) {
        return a(context).getLong(k.b(context, str), j);
    }

    static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (p.class) {
            if (a == null) {
                a = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static String a(Context context, String str, String str2) {
        return a(context).getString(k.b(context, str), str2);
    }

    public static void b(Context context, String str, int i) {
        String b = k.b(context, str);
        Editor edit = a(context).edit();
        edit.putInt(b, i);
        edit.commit();
    }

    public static void b(Context context, String str, long j) {
        String b = k.b(context, str);
        Editor edit = a(context).edit();
        edit.putLong(b, j);
        edit.commit();
    }

    public static void b(Context context, String str, String str2) {
        String b = k.b(context, str);
        Editor edit = a(context).edit();
        edit.putString(b, str2);
        edit.commit();
    }
}

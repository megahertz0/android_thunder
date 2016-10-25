package com.tencent.wxop.stat.common;

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
        return a(context).getInt(k.a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString()), i);
    }

    public static long a(Context context, String str, long j) {
        return a(context).getLong(k.a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString()), j);
    }

    static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (p.class) {
            sharedPreferences = context.getSharedPreferences(".mta-wxop", 0);
            a = sharedPreferences;
            if (sharedPreferences == null) {
                a = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static String a(Context context, String str, String str2) {
        return a(context).getString(k.a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString()), str2);
    }

    public static void b(Context context, String str, int i) {
        String a = k.a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString());
        Editor edit = a(context).edit();
        edit.putInt(a, i);
        edit.commit();
    }

    public static void b(Context context, String str, long j) {
        String a = k.a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString());
        Editor edit = a(context).edit();
        edit.putLong(a, j);
        edit.commit();
    }

    public static void b(Context context, String str, String str2) {
        String a = k.a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString());
        Editor edit = a(context).edit();
        edit.putString(a, str2);
        edit.commit();
    }
}

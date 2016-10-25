package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.preference.PreferenceManager;

public abstract class f {
    public static void a(Context context) {
    }

    public static boolean a(Context context, String str, boolean z) {
        a(context);
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }

    public static void b(Context context, String str, boolean z) {
        a(context);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str, z).commit();
    }
}

package com.xunlei.downloadprovider.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PreferenceHelper.java
public final class j {
    public final SharedPreferences a;

    public j(Context context, String str) {
        this.a = context.getSharedPreferences(str, 0);
    }

    public j(Context context, String str, byte b) {
        this.a = context.getSharedPreferences(str, XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public final void a(String str, long j) {
        Editor edit = this.a.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public final long b(String str, long j) {
        return this.a.getLong(str, j);
    }

    public final void a(String str, boolean z) {
        Editor edit = this.a.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public final void a(String str, String str2) {
        Editor edit = this.a.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public final String b(String str, String str2) {
        return this.a.getString(str, str2);
    }

    public static int a(Context context, String str, String str2) {
        return context.getSharedPreferences(str, 0).getInt(str2, 0);
    }

    public static void b(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.remove(str2);
        edit.commit();
    }
}

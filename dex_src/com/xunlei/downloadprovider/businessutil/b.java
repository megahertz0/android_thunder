package com.xunlei.downloadprovider.businessutil;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: SettingStateController.java
public final class b {
    private static b b;
    public Context a;

    private b() {
    }

    public static b a() {
        synchronized ("SettingStateController") {
            if (b == null) {
                b = new b();
            }
        }
        return b;
    }

    public final void a(int i) {
        Editor edit = this.a.getSharedPreferences("settingstate", 0).edit();
        edit.putInt("storage_of_download_dir", i);
        edit.commit();
    }

    public final boolean b() {
        return this.a.getSharedPreferences("settingstate", 0).getBoolean("high_speed_channel", true);
    }

    public final int c() {
        return this.a.getSharedPreferences("settingstate", 0).getInt("storage_of_download_dir", XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public final int d() {
        return this.a.getSharedPreferences("settingstate", 0).getInt("storage_of_download_dir", -1);
    }

    public final boolean e() {
        return this.a.getSharedPreferences("settingstate", 0).getBoolean("install", true);
    }

    public final boolean f() {
        return this.a.getSharedPreferences("settingstate", 0).getBoolean("remove", false);
    }

    public final void g() {
        Editor edit = this.a.getSharedPreferences("settingstate", 0).edit();
        edit.putBoolean("name_first_create_task", false);
        edit.commit();
    }

    public final boolean h() {
        return this.a.getSharedPreferences("settingstate", XZBDevice.DOWNLOAD_LIST_ALL).getBoolean("sound_onoff", true);
    }

    public final void a(boolean z) {
        Editor edit = this.a.getSharedPreferences("settingstate", 0).edit();
        edit.putBoolean("sound_onoff", z);
        edit.commit();
    }

    public final boolean i() {
        return this.a.getSharedPreferences("settingstate", 0).getBoolean("speed_limit", false);
    }

    public final int j() {
        return this.a.getSharedPreferences("settingstate", 0).getInt("downtask_num", XZBDevice.DOWNLOAD_LIST_FAILED);
    }

    public final void b(int i) {
        Editor edit = this.a.getSharedPreferences("settingstate", 0).edit();
        edit.putInt("downtask_num", i);
        edit.commit();
    }

    public final int k() {
        return this.a.getSharedPreferences("settingstate", 0).getInt("speed_limit_value", AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
    }

    public final String a(String str) {
        this.a.getSharedPreferences("settingstate", 0).edit().putString("name_real_slave_download_path", str).commit();
        return str;
    }
}

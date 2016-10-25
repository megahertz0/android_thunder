package com.umeng.fb.model;

import android.content.Context;
import android.content.SharedPreferences;

public class FbSwitch {
    private static FbSwitch a;
    private static Context b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private SharedPreferences i;
    private SharedPreferences j;
    private SharedPreferences k;

    private FbSwitch(Context context) {
        this.c = "fb_push_switch";
        this.d = "fb_welcome_info";
        this.e = "fb_welcome_info_switch";
        this.f = "fb_push_switch_key";
        this.g = "fb_welcome_info_key";
        this.h = "fb_welcome_info_key";
        b = context;
        this.i = b.getSharedPreferences("fb_push_switch", 0);
        this.j = b.getSharedPreferences("fb_welcome_info", 0);
        this.k = b.getSharedPreferences("fb_welcome_info_switch", 0);
    }

    public static FbSwitch getInstance(Context context) {
        if (a == null) {
            a = new FbSwitch(context);
        }
        return a;
    }

    public void setFbPushSwitch(boolean z) {
        this.i.edit().putBoolean("fb_push_switch_key", z).apply();
    }

    public void setWelcomeInfoSwitch(boolean z) {
        this.k.edit().putBoolean("fb_welcome_info_key", z).apply();
    }

    public void setWelcomeInfo(String str) {
        setWelcomeInfoSwitch(true);
        if (str != null) {
            this.j.edit().putString("fb_welcome_info_key", str).apply();
        }
    }

    public boolean getFbPushSwitch() {
        return this.i.getBoolean("fb_push_switch_key", true);
    }

    public boolean getWelcomeInfoSwitch() {
        return this.k.getBoolean("fb_welcome_info_key", true);
    }

    public String getWelcomeInfo() {
        return this.j.getString("fb_welcome_info_key", null);
    }
}

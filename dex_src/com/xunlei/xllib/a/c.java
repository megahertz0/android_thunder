package com.xunlei.xllib.a;

import android.content.Context;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;

public final class c {
    public Context a;
    private boolean b;

    private c(Context context, boolean z) {
        this.a = context;
        this.b = z;
    }

    public static c a(Context context) {
        boolean z = true;
        try {
            if (System.getInt(context.getContentResolver(), "screen_brightness_mode") != 1) {
                z = false;
            }
            return new c(context, z);
        } catch (SettingNotFoundException e) {
            return null;
        }
    }

    public final int a() {
        try {
            return System.getInt(this.a.getContentResolver(), "screen_brightness");
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

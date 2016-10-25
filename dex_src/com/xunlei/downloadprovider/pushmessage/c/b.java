package com.xunlei.downloadprovider.pushmessage.c;

import android.content.SharedPreferences;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: PushSpHelper.java
public final class b {
    private static b b;
    public SharedPreferences a;

    static {
        b = new b();
    }

    private b() {
        this.a = null;
        if (BrothersApplication.a() != null) {
            this.a = BrothersApplication.a().getSharedPreferences("push", 0);
        }
    }

    public static b a() {
        return b;
    }

    public final void a(String str, String str2) {
        if (this.a != null) {
            this.a.edit().putString(str, str2).apply();
        }
    }

    public final String a(String str) {
        return this.a == null ? null : this.a.getString(str, null);
    }
}

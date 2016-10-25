package com.xunlei.downloadprovider.search.a;

import android.content.SharedPreferences;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: SearchSpHelper.java
public final class b {
    private static b b;
    public SharedPreferences a;

    static {
        b = new b();
    }

    private b() {
        this.a = BrothersApplication.a().getSharedPreferences("search", 0);
    }

    public static b a() {
        return b;
    }
}

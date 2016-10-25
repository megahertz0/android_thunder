package com.xunlei.downloadprovider.player.a;

import android.content.SharedPreferences;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AutoPlaySpHelper.java
public class c {
    public static String c;
    private static c d;
    public SharedPreferences a;
    public int b;

    static {
        c = "auto_play_option";
    }

    private c() {
        this.b = 2;
        this.a = BrothersApplication.a().getSharedPreferences("AutoPlay", 0);
        this.b = a(c);
    }

    public static c a() {
        if (d == null) {
            synchronized (c.class) {
                if (d == null) {
                    d = new c();
                }
            }
        }
        return d;
    }

    public final int a(String str) {
        return this.a != null ? this.a.getInt(str, XZBDevice.DOWNLOAD_LIST_RECYCLE) : -1;
    }
}

package com.xunlei.downloadprovider.member.login.a;

import android.content.Context;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.i;

// compiled from: UserCenterHelper.java
public final class a {
    public static final String a;
    public static final String b;
    private static final String e;
    private static final String f;
    private static final String g;
    private static final Object h;
    private static a i;
    public final BrothersApplication c;
    public final i d;

    static {
        e = a.class.getSimpleName();
        a = e + "share_pref";
        f = e + "key_chtable_nonexist";
        b = e + "key_webtable_nonexist";
        g = e + "key_sttable_nonexist";
        h = new Object();
        i = null;
    }

    public static a a() {
        if (i == null) {
            synchronized (h) {
                if (i == null) {
                    i = new a();
                }
            }
        }
        return i;
    }

    private a() {
        this.c = BrothersApplication.a();
        this.d = i.a();
    }

    public static void a(Context context) {
        context.getSharedPreferences(a, 0).edit().putBoolean(f, true).putBoolean(b, true).putBoolean(g, true).commit();
    }

    public final boolean a(b bVar) {
        return this.d.a(bVar);
    }

    public final boolean a(String str) {
        return this.d.b(str);
    }
}

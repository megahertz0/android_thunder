package com.baidu.mobads.openad.b;

import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.baidu.mobads.openad.e.a;

public class b implements a {
    public static Context a;

    public static void a(Context context) {
        if (a == null) {
            a = context;
            CookieSyncManager.createInstance(context);
            if (VERSION.SDK_INT < 21) {
                new Thread(new c()).start();
            }
        }
    }

    public b() {
        Class.forName("android.webkit.CookieManager", true, a.class.getClassLoader());
    }

    public void a(String str, String str2) {
        CookieManager.getInstance().setCookie(str, str2);
        CookieSyncManager.getInstance().sync();
    }

    public void a() {
        CookieManager.getInstance().removeExpiredCookie();
    }

    public String a(String str) {
        try {
            return CookieManager.getInstance().getCookie(str);
        } catch (Exception e) {
            return com.umeng.a.d;
        }
    }
}

package anetwork.channel.c;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import anet.channel.util.ALog;
import anetwork.channel.http.b;

// compiled from: Taobao
public final class a {
    public static CookieManager a;
    private static volatile boolean b;

    static {
        b = false;
    }

    public static synchronized void a(Context context) {
        synchronized (a.class) {
            if (!b) {
                CookieSyncManager.createInstance(context);
                CookieManager instance = CookieManager.getInstance();
                a = instance;
                instance.setAcceptCookie(true);
                a.removeExpiredCookie();
                b = true;
            }
        }
    }

    private static boolean a() {
        if (!(b || b.b == null)) {
            a(b.b);
        }
        return b;
    }

    public static synchronized void a(String str, String str2) {
        synchronized (a.class) {
            if (a()) {
                try {
                    a.setCookie(str, str2);
                    CookieSyncManager.getInstance().sync();
                } catch (Throwable th) {
                    ALog.e("ANet.CookieManager", new StringBuilder("set cookie failed. url=").append(str).append(" cookies=").append(str2).toString(), null, th, new Object[0]);
                }
            }
        }
    }

    public static synchronized String a(String str) {
        String str2 = null;
        synchronized (a.class) {
            if (a()) {
                try {
                    str2 = a.getCookie(str);
                } catch (Throwable th) {
                    ALog.e("ANet.CookieManager", new StringBuilder("get cookie failed. url=").append(str).toString(), null, th, new Object[0]);
                }
            }
        }
        return str2;
    }
}

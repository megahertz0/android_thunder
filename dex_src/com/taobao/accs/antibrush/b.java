package com.taobao.accs.antibrush;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.util.h;
import com.taobao.accs.utl.ALog;
import com.xunlei.tdlive.R;
import java.util.StringTokenizer;

// compiled from: Taobao
public class b {
    public static final String KEY_SEC = "sec";
    public static final String TAG = "CookieMgr";
    public static CookieManager a;
    private static volatile boolean b;

    static {
        b = false;
    }

    public static synchronized void a(Context context) {
        synchronized (b.class) {
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
        if (!(b || anetwork.channel.http.b.b == null)) {
            a(anetwork.channel.http.b.b);
        }
        return b;
    }

    public static synchronized String a(String str) {
        String str2 = null;
        synchronized (b.class) {
            if (a()) {
                try {
                    str2 = b(a.getCookie(str));
                } catch (Throwable th) {
                    ALog.e(TAG, new StringBuilder("get cookie failed. url=").append(str).toString(), th, new Object[0]);
                }
            } else {
                ALog.e(TAG, "cookieMgr not setup", new Object[0]);
            }
        }
        return str2;
    }

    public static String b(String str) {
        String str2 = null;
        if (!TextUtils.isEmpty(str)) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, h.b);
            do {
                try {
                    String nextToken = stringTokenizer.nextToken();
                    int indexOf = nextToken.indexOf(R.styleable.AppCompatTheme_popupWindowStyle);
                    if (indexOf != -1) {
                        String trim = nextToken.substring(0, indexOf).trim();
                        nextToken = nextToken.substring(indexOf + 1).trim();
                        if (KEY_SEC.equals(trim)) {
                            str2 = c(nextToken);
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid cookie name-value pair");
                    }
                } catch (Throwable th) {
                    ALog.e(TAG, "parse", th, new Object[0]);
                }
            } while (stringTokenizer.hasMoreTokens());
        }
        return str2;
    }

    private static String c(String str) {
        if (str == null || str.length() <= 2 || str.charAt(0) != '\"' || str.charAt(str.length() - 1) != '\"') {
            return (str == null || str.length() <= 2 || str.charAt(0) != '\'' || str.charAt(str.length() - 1) != '\'') ? str : str.substring(1, str.length() - 1);
        } else {
            return str.substring(1, str.length() - 1);
        }
    }
}

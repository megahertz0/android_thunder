package com.baidu.mobads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import com.baidu.mobads.j.j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.lang.reflect.Method;
import java.util.Locale;

public class ac extends af {
    public String a;

    static class a {
        private static String[] a;
        private static String[] b;
        private static String[] c;
        private static String[] d;
        private static final String[] e;
        private static final String[] f;
        private static String[] g;
        private static String[] h;

        static {
            a = "apk,zip,rar,7z,tar.gz,bz".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            b = "mp4,3gp,3g2,avi,rm,rmvb,wmv,flv,mkv,mov,asf,asx".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            c = "mp3,ra,wma,m4a,wav,aac,mmf,amr,ogg,adp".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            d = "sms,smsto,mms".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            e = new String[]{"tel"};
            f = new String[]{"mailto"};
            g = "http,https".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            h = "http,https,sms,smsto,mms,tel,fax,ftp,mailto,gopher,news,telnet,file".split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        }
    }

    @TargetApi(3)
    public ac(Context context, boolean z, boolean z2) {
        super(context);
        this.a = com.umeng.a.d;
        setClickable(true);
        if (z) {
            setBackgroundColor(0);
        }
        if (z2) {
            setFocusable(true);
        } else {
            setFocusable(false);
        }
        setScrollBarStyle(0);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setNeedInitialFocus(false);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setAllowFileAccess(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        String path = context.getApplicationContext().getDir("database", 0).getPath();
        try {
            WebSettings.class.getMethod("setDatabasePath", new Class[]{String.class}).invoke(settings, new Object[]{path});
            WebSettings.class.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE}).invoke(settings, new Object[]{Boolean.valueOf(true)});
            WebSettings.class.getMethod("setDatabaseEnabled", new Class[]{Boolean.TYPE}).invoke(settings, new Object[]{Boolean.valueOf(true)});
        } catch (Exception e) {
        }
        try {
            WebSettings.class.getMethod("setAppCacheEnabled", new Class[]{Boolean.TYPE}).invoke(settings, new Object[]{Boolean.valueOf(true)});
            WebSettings.class.getMethod("setAppCachePath", new Class[]{String.class}).invoke(settings, new Object[]{path});
            WebSettings.class.getMethod("setAppCacheMaxSize", new Class[]{Long.TYPE}).invoke(settings, new Object[]{Long.valueOf(5242880)});
        } catch (Exception e2) {
        }
        try {
            WebSettings.class.getMethod("setGeolocationEnabled", new Class[]{Boolean.TYPE}).invoke(settings, new Object[]{Boolean.valueOf(true)});
            WebSettings.class.getMethod("setGeolocationDatabasePath", new Class[]{String.class}).invoke(settings, new Object[]{path});
        } catch (Exception e3) {
        }
        setWebViewClient(new ad());
        setWebChromeClient(new WebChromeClient());
        try {
            if (Integer.parseInt(VERSION.SDK) >= 14) {
                Method declaredMethod = Class.forName("android.view.View").getDeclaredMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this, new Object[]{Integer.valueOf(1), null});
            }
        } catch (Throwable e4) {
            j.a().e(e4);
        }
    }

    private static boolean a(String[] strArr, String str) {
        if (str == null) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String toLowerCase = parse.getPath() == null ? com.umeng.a.d : parse.getPath().toLowerCase(Locale.getDefault());
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (toLowerCase.trim().endsWith(new StringBuilder(".").append(strArr[i]).toString())) {
                Object obj = 1;
                break;
            }
        }
        boolean z = false;
        return z;
    }

    private static boolean b(String[] strArr, String str) {
        if (str == null) {
            return false;
        }
        String toLowerCase = str.toLowerCase(Locale.getDefault());
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (toLowerCase.trim().startsWith(strArr[i] + ":")) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str) {
        return !c(str) || g(str) || h(str) || i(str);
    }

    public static boolean b(String str) {
        return d(str) || e(str) || f(str) || (j(str) && (!c(str) || g(str) || h(str) || i(str)));
    }

    private static boolean j(String str) {
        return b(a.h, str);
    }

    public static boolean c(String str) {
        return b(a.g, str);
    }

    public static boolean d(String str) {
        return b(a.d, str);
    }

    public static boolean e(String str) {
        return b(a.e, str);
    }

    public static boolean f(String str) {
        return b(a.f, str);
    }

    public static boolean g(String str) {
        return a(a.a, str);
    }

    public static boolean h(String str) {
        return a(a.b, str);
    }

    public static boolean i(String str) {
        return a(a.c, str);
    }
}

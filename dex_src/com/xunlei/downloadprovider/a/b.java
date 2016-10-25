package com.xunlei.downloadprovider.a;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.umeng.a;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.e;
import com.xunlei.xllib.b.g;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// compiled from: AndroidConfig.java
public final class b {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static Context e;

    static {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
    }

    public static void a(Context context) {
        e = context;
        u();
        t();
    }

    public static Context a() {
        return e;
    }

    public static String b() {
        return e.getPackageName();
    }

    public static String c() {
        if (TextUtils.isEmpty(d) && e != null) {
            d = b(e);
        }
        return d == null ? a.d : d;
    }

    public static String b(Context context) {
        if (context != null) {
            d = d.d(context);
        } else if (TextUtils.isEmpty(d) && e != null) {
            d = d.d(e);
        }
        return d == null ? a.d : d;
    }

    public static String d() {
        if (TextUtils.isEmpty(c) && e != null) {
            String toUpperCase;
            Context context = e;
            Object a = e.a(context);
            if (TextUtils.isEmpty(a) || a.equals("02:00:00:00:00:00")) {
                a = a.d;
                Object b = e.b(context);
                if (!TextUtils.isEmpty(b)) {
                    toUpperCase = b.replaceAll("[^0-9a-fA-F]", a.d).toUpperCase();
                    if (toUpperCase.length() >= 12) {
                        a = toUpperCase.substring(toUpperCase.length() - 12);
                    } else {
                        a = g.a(toUpperCase.getBytes()).substring(0, XZBDevice.Fail).toUpperCase();
                    }
                }
            }
            if (TextUtils.isEmpty(a)) {
                toUpperCase = null;
            } else {
                toUpperCase = a.replaceAll("[^0-9a-fA-F]", a.d).toUpperCase() + "004V";
            }
            if (TextUtils.isEmpty(toUpperCase)) {
                toUpperCase = "0000000000000000004V";
            }
            c = toUpperCase;
        }
        return c;
    }

    public static String e() {
        if (TextUtils.isEmpty(a) && e != null) {
            String b = com.xunlei.xllib.a.a.b(e);
            if (!TextUtils.isEmpty(b)) {
                b = b.toUpperCase();
            }
            a = b;
        }
        return a;
    }

    public static String c(Context context) {
        String a = com.xunlei.xllib.a.a.a(context);
        return TextUtils.isEmpty(a) ? "000000000000000" : a;
    }

    public static String f() {
        if (e != null && TextUtils.isEmpty(b)) {
            b = c(e);
        }
        return TextUtils.isEmpty(b) ? "000000000000000" : b;
    }

    public static String d(Context context) {
        return context != null ? context.getResources().getString(R.string.pid) : a.d;
    }

    public static String g() {
        return d(e);
    }

    public static String e(Context context) {
        return context != null ? context.getResources().getString(R.string.product_id) : a.d;
    }

    public static String h() {
        return e(e);
    }

    public static int i() {
        return VERSION.SDK_INT;
    }

    public static String j() {
        return Build.BOARD;
    }

    public static String k() {
        return Build.BRAND;
    }

    public static String l() {
        return Build.DEVICE;
    }

    public static String m() {
        return Build.DISPLAY;
    }

    public static String n() {
        return Build.FINGERPRINT;
    }

    public static String o() {
        return Build.HARDWARE;
    }

    public static String p() {
        return Build.MANUFACTURER;
    }

    public static String q() {
        return Build.MODEL;
    }

    public static String r() {
        return Build.PRODUCT;
    }

    public static String s() {
        return Build.TAGS;
    }

    public static int t() {
        return e != null ? com.xunlei.xllib.a.d.a(e) : 0;
    }

    public static int u() {
        return e != null ? com.xunlei.xllib.a.d.b(e) : 0;
    }

    public static int[] v() {
        Point e = com.xunlei.xllib.a.d.e(e);
        return new int[]{e.x, e.y};
    }

    public static String f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            new StringBuilder("getVersion error ").append(e.getMessage());
            return null;
        }
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            new StringBuilder("getVersionName error ").append(e.getMessage());
            return null;
        }
    }

    public static String w() {
        return g(e);
    }

    public static int x() {
        try {
            return e.getPackageManager().getPackageInfo(e.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            new StringBuilder("getVersion error ").append(e.getMessage());
            return 1;
        }
    }

    public static String y() {
        String str = Build.BRAND + "|" + Build.MODEL;
        try {
            return URLEncoder.encode(str, "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static void a(Context context, View view) {
        if ((context instanceof Context) && (view instanceof View)) {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void h(Context context) {
        if (context instanceof Context) {
            ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }
}

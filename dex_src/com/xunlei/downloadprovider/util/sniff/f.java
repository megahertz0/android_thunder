package com.xunlei.downloadprovider.util.sniff;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.sdk.util.h;
import com.xunlei.c.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.thundersniffer.sniff.SniffingFilter;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import com.xunlei.xllib.b.d;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Set;

// compiled from: SniffUtil.java
public class f {
    private static final String a;
    private static f b;
    private static boolean c;
    private static SniffingFilter d;

    // compiled from: SniffUtil.java
    public static interface a {
        void a(String str);
    }

    static {
        a = f.class.getSimpleName();
        b = null;
        c = false;
    }

    private f() {
    }

    public static f a() {
        if (b == null) {
            b = new f();
        }
        return b;
    }

    public static String a(String str) {
        Object obj = "charset=";
        if (!str.contains(obj)) {
            return "utf-8";
        }
        int indexOf = str.indexOf(obj);
        String substring = str.substring(indexOf, str.substring(indexOf).indexOf(h.f) + indexOf);
        return substring.substring(substring.indexOf("=") + 1);
    }

    public static void a(String str, a aVar) {
        String str2;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.getResponseCode();
            String decode = URLDecoder.decode(httpURLConnection.getURL().toString());
            httpURLConnection.disconnect();
            str2 = "?src=";
            Object obj = "&word=";
            if (decode.contains(str2)) {
                int indexOf = decode.indexOf(str2);
                if (decode.contains(obj)) {
                    str2 = decode.substring(str2.length() + indexOf, decode.indexOf(obj));
                } else {
                    str2 = decode.substring(str2.length() + indexOf);
                }
            } else {
                str2 = decode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            str2 = null;
        }
        aVar.a(str2);
    }

    @Deprecated
    public static void a(int i) {
        BrothersApplication.a().getSharedPreferences("shared_for_report_from_channel", 0).edit().putInt("entry", i).commit();
    }

    public static String b(String str) {
        String e = SniffConfigure.e();
        Object k = str == null ? com.umeng.a.d : b.k(str);
        return e.contains("%s") ? e.replace("%s", k) : e + k;
    }

    public static boolean c(String str) {
        return (str == null || str.equals(com.umeng.a.d)) ? false : ThunderSnifferUtil.isUrlValid(str);
    }

    public static Set<String> b() {
        return BrothersApplication.a().getSharedPreferences("thunder_sniff_preferences", 0).getStringSet("sniff_filter", null);
    }

    public static void a(Set<String> set) {
        Editor edit = BrothersApplication.a().getSharedPreferences("thunder_sniff_preferences", 0).edit();
        edit.clear().commit();
        edit.putStringSet("sniff_filter", set).commit();
        c = true;
    }

    public static SniffingFilter a(Context context) {
        if (context == null) {
            return d.a();
        }
        if (d != null && !c) {
            return d;
        }
        Object b = b();
        if (d.a(b)) {
            return d.a();
        }
        d = new d(b);
        c = false;
        return d;
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        String f = f(str);
        String[] split = f.split(" ");
        int length = split.length;
        return length >= 2 ? f.substring(0, f.length() - split[length - 1].length()).trim() : f.trim();
    }

    private static String f(String str) {
        return str.replaceAll("\n", " ");
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        String[] split = f(str).split(" ");
        return split.length >= 2 ? split[split.length - 1].trim() : com.umeng.a.d;
    }

    public static boolean c() {
        return BrothersApplication.a().getSharedPreferences("get_sniff_config", 0).getBoolean("has_gotten_sniff_config", false);
    }

    public static void a(boolean z) {
        BrothersApplication.a().getSharedPreferences("get_sniff_config", 0).edit().putBoolean("has_gotten_sniff_config", z).commit();
    }

    public static int d() {
        return BrothersApplication.a().getSharedPreferences("curr_app_loading", 0).getInt("curr_app_loading_counts", -1);
    }

    public static void b(int i) {
        BrothersApplication.a().getSharedPreferences("curr_app_loading", 0).edit().putInt("curr_app_loading_counts", i).commit();
    }
}

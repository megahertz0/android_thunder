package com.xunlei.analytics.config;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;

public class a {
    private static boolean a;
    private static boolean b;
    private static int c;
    private static String d;
    private static String e;
    private static String f;
    private static Context g;
    private static HashMap<String, String> h;

    static {
        a = false;
        b = true;
        c = 3;
    }

    public static String a() {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        throw new IllegalArgumentException("app_id is invalid. Did you call init()\uff1f");
    }

    public static void a(int i) {
        c = i;
    }

    public static void a(Context context) {
        g = context;
    }

    public static void a(String str) {
        d = str;
    }

    public static void a(HashMap<String, String> hashMap) {
        h = hashMap;
    }

    public static void a(boolean z) {
        a = z;
    }

    public static String b() {
        if (!TextUtils.isEmpty(d)) {
            return e;
        }
        throw new IllegalArgumentException("secret_key is invalid. Did you call init()\uff1f");
    }

    public static void b(String str) {
        e = str;
    }

    public static void b(boolean z) {
        b = z;
    }

    public static String c() {
        return f;
    }

    public static void c(String str) {
        f = str;
    }

    public static Context d() {
        if (g != null) {
            return g;
        }
        throw new IllegalArgumentException("mGloadApplicationContext is invalid. Did you call init()\uff1f");
    }

    public static boolean e() {
        return a;
    }

    public static int f() {
        return c;
    }

    public static boolean g() {
        return b;
    }

    public static HashMap<String, String> h() {
        return h;
    }
}

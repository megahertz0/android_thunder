package com.xunlei.tdlive.c;

import android.content.Context;
import com.xunlei.tdlive.c.a.b;

// compiled from: UpdateAgent.java
public class a {
    private static a a;
    private static boolean b;
    private static boolean c;
    private static boolean d;
    private static boolean e;
    private static boolean f;
    private static boolean g;

    // compiled from: UpdateAgent.java
    public static abstract class a {
        public abstract void a(int i, b bVar);
    }

    static {
        a = null;
        b = false;
        c = true;
        d = false;
        e = false;
        f = false;
        g = false;
    }

    public static String a(Context context, String str) {
        return null;
    }

    public static void a(Context context) {
        f = false;
        a();
    }

    public static void b(Context context) {
        f = true;
        a();
    }

    public static void c(Context context) {
        g = true;
        a();
    }

    public static void a(boolean z) {
        c = z;
    }

    public static void a(a aVar) {
        a = aVar;
    }

    public static void b(boolean z) {
        b = z;
    }

    public static void c(boolean z) {
        d = z;
    }

    public static void d(boolean z) {
        e = z;
    }

    public static void a(Context context, b bVar) {
    }

    private static void a() {
        if (a != null) {
            a.a(-1, new b());
        }
    }
}

package com.xunlei.common.register.a;

import com.xunlei.xiazaibao.BuildConfig;

// compiled from: XLRegisterTask.java
public abstract class b {
    private static int e;
    public int a;
    private c b;
    private int c;
    private String d;
    private String f;

    // compiled from: XLRegisterTask.java
    public enum a {
        ;

        private static int[] a() {
            return (int[]) e.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = new int[]{1, 2, 3, 4};
        }
    }

    public abstract void e();

    static {
        e = 1000000;
    }

    public b(c cVar) {
        this.a = a.a;
        int i = e;
        e = i + 1;
        this.c = i;
        this.f = BuildConfig.VERSION_NAME;
        this.b = cVar;
    }

    public final int a() {
        return this.c;
    }

    private void f() {
        this.a = a.c;
    }

    public final void a(int i, Object... objArr) {
        this.b.a(i, objArr);
    }

    public final c b() {
        return this.b;
    }

    public final void a(String str) {
        this.f = str;
    }

    public final String c() {
        return this.f;
    }

    public final void b(String str) {
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }

    public final String d() {
        return new StringBuilder("ANDROID-").append(this.b.h()).toString();
    }
}

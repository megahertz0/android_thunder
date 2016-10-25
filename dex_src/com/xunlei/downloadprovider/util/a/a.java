package com.xunlei.downloadprovider.util.a;

import com.xunlei.downloadprovider.util.r;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// compiled from: ConfigureManager.java
public final class a {
    public static ExecutorService a;
    private static a b;

    static /* synthetic */ void e() {
    }

    static {
        b = new a();
        a = Executors.newCachedThreadPool();
    }

    private a() {
    }

    public static a a() {
        return b;
    }

    public static void b() {
        r.c().e();
    }

    public static void c() {
    }

    public static void d() {
        r.c().f();
    }
}

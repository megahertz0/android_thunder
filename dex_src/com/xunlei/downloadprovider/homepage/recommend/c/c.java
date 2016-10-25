package com.xunlei.downloadprovider.homepage.recommend.c;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// compiled from: ClickNiceRecordHelper.java
public final class c {
    private static c b;
    final a a;
    private Executor c;

    private c() {
        this.c = Executors.newSingleThreadExecutor();
        this.a = a.a();
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    public final boolean a(String str) {
        return this.a.a(str) != null;
    }

    public final void a(String str, String str2, String str3, String str4) {
        this.c.execute(new d(this, str3, str, str2, str4));
    }

    public final void b(String str) {
        this.c.execute(new e(this, str));
    }

    public final void b() {
        this.c.execute(new f(this));
    }
}

package com.alipay.apmobilesecuritysdk.g;

import java.util.LinkedList;

public final class b {
    private static b a;
    private Thread b;
    private LinkedList<Runnable> c;

    static {
        a = new b();
    }

    public b() {
        this.b = null;
        this.c = new LinkedList();
    }

    public static b a() {
        return a;
    }

    public final synchronized void a(Runnable runnable) {
        this.c.add(runnable);
        if (this.b == null) {
            this.b = new Thread(new c(this));
            this.b.start();
        }
    }
}

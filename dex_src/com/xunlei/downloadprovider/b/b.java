package com.xunlei.downloadprovider.b;

// compiled from: BpBoxManager.java
public final class b extends com.xunlei.downloadprovider.b.a.b {
    private static b d;

    static {
        d = null;
    }

    private b() {
    }

    public static b a() {
        if (d == null) {
            d = new b();
        }
        return d;
    }
}

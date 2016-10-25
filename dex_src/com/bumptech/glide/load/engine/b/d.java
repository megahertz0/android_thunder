package com.bumptech.glide.load.engine.b;

import java.io.File;

// compiled from: DiskLruCacheFactory.java
public class d implements com.bumptech.glide.load.engine.b.a.a {
    private final int a;
    private final a b;

    // compiled from: DiskLruCacheFactory.java
    public static interface a {
        File a();
    }

    public d(a aVar) {
        this.a = 262144000;
        this.b = aVar;
    }

    public final a a() {
        File a = this.b.a();
        if (a == null) {
            return null;
        }
        return (a.mkdirs() || (a.exists() && a.isDirectory())) ? e.a(a, this.a) : null;
    }
}

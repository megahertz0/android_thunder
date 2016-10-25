package com.xunlei.downloadprovider.web.sniff.a;

import java.util.HashMap;
import java.util.Map;

// compiled from: SniffResultCache.java
public final class a {
    public static a b;
    public Map<String, Object> a;

    public a() {
        this.a = new HashMap();
    }

    public final void a(String str) {
        this.a.remove(str);
    }

    public final Object b(String str) {
        return this.a.get(str);
    }
}

package com.xunlei.b;

import java.util.HashMap;

// compiled from: ReportParams.java
public final class a {
    public String a;
    public HashMap<String, String> b;

    public a(String str) {
        this.b = new HashMap();
        this.a = str;
    }

    public final void a(String str, Object obj) {
        this.b.put(str, obj.toString());
    }

    public final a a(String str, long j) {
        this.b.put(str, String.valueOf(j));
        return this;
    }

    public final a a(String str, String str2) {
        this.b.put(str, str2);
        return this;
    }

    public final a a(String str, int i) {
        this.b.put(str, String.valueOf(i));
        return this;
    }

    public final void a(String str) {
        this.b.remove(str);
    }
}

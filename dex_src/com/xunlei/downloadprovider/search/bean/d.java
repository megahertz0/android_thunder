package com.xunlei.downloadprovider.search.bean;

import com.xunlei.xiazaibao.BuildConfig;

// compiled from: SearchRecord.java
public final class d {
    public String a;
    public long b;
    public int c;
    public String d;

    public d(String str, long j) {
        this.a = str;
        this.b = j;
        this.c = 0;
    }

    public final String a() {
        return this.d == null ? BuildConfig.VERSION_NAME : this.d;
    }
}

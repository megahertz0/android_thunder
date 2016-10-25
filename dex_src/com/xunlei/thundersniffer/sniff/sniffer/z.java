package com.xunlei.thundersniffer.sniff.sniffer;

import com.xunlei.c.b;
import com.xunlei.xiazaibao.BuildConfig;

final class z {
    protected final String a;
    public final String b;
    public String c;
    public int d;
    public String e;

    public z(String str) {
        this.d = -1;
        this.b = str;
        String g = b.g(str);
        if (g != null) {
            str = g;
        }
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return (this.a == null || this.a.equals(BuildConfig.VERSION_NAME)) ? this.b.equals(zVar.b) : this.a.equals(zVar.a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}

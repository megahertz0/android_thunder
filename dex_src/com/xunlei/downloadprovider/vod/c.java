package com.xunlei.downloadprovider.vod;

import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
// compiled from: PlayListUrl.java
public final class c {
    ArrayList<a> a;

    // compiled from: PlayListUrl.java
    private static class a {
        public long a;
        public String b;

        public a(String str, long j) {
            this.a = j;
            this.b = str;
        }
    }

    public c() {
        this.a = new ArrayList();
    }

    final long a() {
        Iterator it = this.a.iterator();
        long j = 0;
        while (it.hasNext()) {
            j = (((a) it.next()).a * 1000) + j;
        }
        return j;
    }
}

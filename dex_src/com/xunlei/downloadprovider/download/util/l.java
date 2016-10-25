package com.xunlei.downloadprovider.download.util;

import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.c.a;

// compiled from: ImageOptions.java
public final class l {
    private static l d;
    public c a;
    public c b;
    public c c;

    static {
        d = null;
    }

    private l() {
        a aVar = new a();
        aVar.b = 2130838043;
        aVar.c = 2130838043;
        aVar.a = 2130838043;
        aVar.h = true;
        aVar = aVar.a();
        aVar.g = true;
        this.a = aVar.b();
        aVar = new a();
        aVar.b = 2130839355;
        aVar.c = 2130839355;
        aVar.a = 2130839355;
        aVar.h = true;
        aVar = aVar.a();
        aVar.g = true;
        aVar.q = new com.nostra13.universalimageloader.core.b.c(10);
        this.b = aVar.b();
        aVar = new a();
        aVar.i = true;
        this.c = aVar.b();
    }

    public static l a() {
        if (d == null) {
            d = new l();
        }
        return d;
    }
}

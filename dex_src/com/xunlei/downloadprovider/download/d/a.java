package com.xunlei.downloadprovider.download.d;

import com.xunlei.downloadprovider.member.a.a.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: HighSpeedConfigManager.java
public final class a implements com.xunlei.downloadprovider.member.a.a.a {
    private static a b;
    public b a;
    private List<Object> c;

    public a() {
        this.a = new b(this);
        this.c = new ArrayList();
    }

    static {
        b = new a();
    }

    public static a a() {
        return b;
    }

    public final void b() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }
}

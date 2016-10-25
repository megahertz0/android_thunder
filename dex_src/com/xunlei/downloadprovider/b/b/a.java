package com.xunlei.downloadprovider.b.b;

import com.xunlei.downloadprovider.b.c.e;
import com.xunlei.downloadprovider.b.c.i;
import java.util.Hashtable;

// compiled from: BpClientDataLoader.java
public final class a extends e {
    public a a;
    protected i b;
    protected int c;
    private d d;

    // compiled from: BpClientDataLoader.java
    public static interface a {
        void a(int i, Object obj);
    }

    public a(String str, i iVar) {
        this.c = 0;
        this.b = iVar;
        this.d = new d(str);
        this.d.b = new b(this);
        this.d.a = new c(this);
    }

    public final void a() {
        d dVar = this.d;
        dVar.f = 4000;
        dVar.g = 4000;
    }

    public final void a(String str, String str2) {
        d dVar = this.d;
        if (dVar.h == null) {
            dVar.h = new Hashtable();
        }
        dVar.h.put(str, str2);
    }

    public final void run() {
        if (this.d != null) {
            this.d.run();
        }
    }

    public final void cancel() {
        if (this.d != null) {
            this.d.cancel();
            super.cancel();
        }
    }
}

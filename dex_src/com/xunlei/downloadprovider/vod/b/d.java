package com.xunlei.downloadprovider.vod.b;

import com.xunlei.downloadprovider.d.c;
import com.xunlei.downloadprovider.vod.b.b.a;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.h;

// compiled from: PlayRecordHelper.java
public final class d implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ VodSourceType b;
    final /* synthetic */ b c;

    public d(b bVar, h hVar, VodSourceType vodSourceType) {
        this.c = bVar;
        this.a = hVar;
        this.b = vodSourceType;
    }

    public final void run() {
        a aVar = new a();
        aVar.b = c.d(this.a.a);
        aVar.c = System.currentTimeMillis();
        aVar.d = b.a(this.b);
        aVar.e = this.a.o;
        aVar.f = this.a.c;
        aVar.h = this.a.d;
        aVar.i = this.a.e;
        aVar.j = this.a.f;
        aVar.k = this.a.s;
        aVar.l = this.a.r;
        aVar.g = this.a.c;
        aVar.p = this.a.n;
        aVar.q = this.a.m;
        this.c.a.a(aVar);
    }
}

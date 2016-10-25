package com.xunlei.downloadprovider.vod.b;

import com.xunlei.downloadprovider.vod.b.b.a;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.h;

// compiled from: PlayRecordHelper.java
public final class c implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ VodSourceType b;
    final /* synthetic */ b c;

    public c(b bVar, h hVar, VodSourceType vodSourceType) {
        this.c = bVar;
        this.a = hVar;
        this.b = vodSourceType;
    }

    public final void run() {
        new StringBuilder("insertPlayRecordToDB name= ").append(this.a.a).append(",tag=").append(this.a.l);
        a aVar = new a();
        aVar.b = com.xunlei.downloadprovider.d.c.d(this.a.a);
        aVar.c = System.currentTimeMillis();
        aVar.d = b.a(this.b);
        aVar.e = this.a.o;
        aVar.f = b.a(this.a.c);
        aVar.h = this.a.d;
        aVar.i = this.a.e;
        aVar.j = this.a.f;
        aVar.k = this.a.s;
        aVar.l = this.a.r;
        aVar.g = this.a.c;
        if (this.a.j) {
            aVar.m = 100;
            aVar.n = this.a.c;
            new StringBuilder("ref_url=").append(this.a.c);
            aVar.p = this.a.k;
            aVar.q = this.a.l;
        }
        this.c.a.a(aVar);
    }
}

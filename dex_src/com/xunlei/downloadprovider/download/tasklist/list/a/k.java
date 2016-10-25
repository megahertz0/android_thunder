package com.xunlei.downloadprovider.download.tasklist.list.a;

import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.ad.common.d.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;

// compiled from: CommendSaleADShowReporter.java
public final class k implements o {
    private int a;
    private String b;
    private String c;
    private String d;
    private b e;

    public k(int i, String str, String str2, String str3, b bVar) {
        this.e = bVar;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public final int a() {
        return this.a;
    }

    public final boolean b() {
        return !this.c.equals(c.f);
    }

    public final void c() {
        a aVar = new a();
        aVar.b = this.e.c.b;
        aVar.c = this.e.c.a;
        aVar.a = 1;
        com.xunlei.downloadprovider.j.a.a().e().a(new com.xunlei.downloadprovider.ad.common.d.c(aVar, new l(this), new m(this)));
        ThunderReporter.a.a(h.a(this.a), "shangwu", this.b, this.c, this.d);
    }
}

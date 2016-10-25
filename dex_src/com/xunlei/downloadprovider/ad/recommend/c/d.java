package com.xunlei.downloadprovider.ad.recommend.c;

import com.xunlei.downloadprovider.download.tasklist.list.a.n;
import com.xunlei.downloadprovider.download.tasklist.list.a.o;
import com.xunlei.downloadprovidercommon.a.a;

// compiled from: RecommendPVReporter.java
public final class d implements o {
    private int a;

    public d(int i) {
        this.a = i;
    }

    public final int a() {
        return this.a;
    }

    public final boolean b() {
        return false;
    }

    public final void c() {
        if (n.a().c != null && !n.a().c.contains(Integer.valueOf(this.a))) {
            int i = this.a;
            String a = a.a();
            new StringBuilder("reportRecommendAdPV attr: adv_downloadin_pv tabId: ").append(i).append(" netType: ").append(a);
            com.xunlei.downloadprovidercommon.a.d.a(a.a("android_advertise", "adv_downloadin_pv").b("tabid", a.a(i)).b("net_type", a));
            n.a().c.add(Integer.valueOf(this.a));
        }
    }
}

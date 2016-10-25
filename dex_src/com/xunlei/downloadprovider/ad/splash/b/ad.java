package com.xunlei.downloadprovider.ad.splash.b;

import com.xunlei.downloadprovider.ad.a.a;
import com.xunlei.downloadprovider.ad.recommend.a.b.b;
import com.xunlei.downloadprovider.ad.splash.a.e;
import java.util.List;

// compiled from: SplashSSPAd.java
final class ad implements b<a> {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    public final void a(List<a> list) {
        com.xunlei.downloadprovider.ad.splash.a.a aVar;
        if (list == null || list.isEmpty()) {
            aVar = null;
        } else {
            a aVar2 = (a) list.get(0);
            if (aVar2 != null) {
                e eVar = new e(aVar2);
                eVar.a(Integer.parseInt("1115"));
                aVar = eVar;
            } else {
                aVar = null;
            }
        }
        new StringBuilder("splashAdInfo : ").append(aVar == null ? "null" : aVar.toString());
        if (aVar != null) {
            this.a.a.e();
            this.a.a.a(aVar, new ae(this, aVar));
            return;
        }
        this.a.a.a(null);
    }

    public final void a(com.xunlei.downloadprovider.ad.recommend.a.b.a aVar) {
        this.a.a.a(aVar);
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_ssp_fail", new StringBuilder("ssp").append(aVar.e).toString());
    }
}

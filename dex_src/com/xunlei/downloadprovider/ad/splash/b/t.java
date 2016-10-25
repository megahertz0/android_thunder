package com.xunlei.downloadprovider.ad.splash.b;

import com.android.volley.r.b;
import com.xunlei.downloadprovider.ad.splash.c.a;
import com.xunlei.downloadprovider.loading.a.d;
import java.util.HashSet;
import java.util.Set;

// compiled from: SplashInMobiAd.java
final class t implements b<String> {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        if (!this.a.g.e) {
            com.xunlei.downloadprovider.loading.a.b a = d.a(str);
            if (a == null) {
                this.a.a(null);
                a.a("adv_launch_inmobi_fail", "-1");
            } else if (a.i) {
                s sVar = this.a;
                if (a.e != null) {
                    Set<String> hashSet = new HashSet();
                    for (String str2 : a.e) {
                        if (str2 != null) {
                            hashSet.add(str2.replace("$TS", String.valueOf(System.currentTimeMillis())));
                        }
                    }
                    a.e.clear();
                    for (String str22 : hashSet) {
                        a.e.add(str22);
                    }
                }
                sVar.a(a.e);
                this.a.e();
                com.xunlei.downloadprovider.ad.splash.a.d dVar = new com.xunlei.downloadprovider.ad.splash.a.d(a);
                dVar.a(Integer.parseInt("1115"));
                this.a.a(dVar, new u(this, dVar));
            } else {
                this.a.a(new com.xunlei.downloadprovider.ad.recommend.a.b.a(a.j, a.j));
                a.a("adv_launch_inmobi_fail", new StringBuilder("inmobierror ").append(a.j).toString());
            }
        }
    }
}

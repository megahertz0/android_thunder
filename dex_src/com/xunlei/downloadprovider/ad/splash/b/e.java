package com.xunlei.downloadprovider.ad.splash.b;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.ad.splash.a.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: SplashAd.java
final class e implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    e(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final void onClick(View view) {
        String p = this.a.p();
        String d = this.a.d();
        int i = this.b.e;
        new StringBuilder("splashReport skip advid: ").append(p).append(" ad_type: ").append(d).append(" splashOrigin: ").append(i);
        String str = "adv_launch_skip";
        g a = g.a("android_advertise", str, str);
        str = "adv_id";
        if (p == null) {
            p = com.umeng.a.d;
        }
        com.xunlei.downloadprovider.ad.splash.c.a.a(a.a(str, p).a("ad_type", d).a("ad_from", com.xunlei.downloadprovider.ad.splash.c.a.b(i)));
    }
}

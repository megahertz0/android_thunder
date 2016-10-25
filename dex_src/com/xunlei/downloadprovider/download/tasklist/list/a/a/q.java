package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.d.a$c;
import com.xunlei.downloadprovider.ad.common.d.c;
import com.xunlei.downloadprovider.download.tasklist.list.a.p;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: ThunderLoader.java
final class q extends b {
    public q(Context context, int i) {
        super(context, i);
    }

    public final void a(a aVar) {
        String str = "adv_downloadtab_ssp_request";
        a.a(g.a("android_advertise", str, str));
        com.xunlei.downloadprovider.j.a.a().e().a(new c(new a$c(p.a), new r(this, aVar), new s(this, aVar)));
    }
}

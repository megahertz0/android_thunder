package com.xunlei.downloadprovider.download.taskDetail.a;

import com.xunlei.downloadprovider.ad.recommend.a.b.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.advertisement.b.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: TaskDetailXunLeiAdModel.java
public final class i implements a {
    public final void a(int i, b<h> bVar) {
        String str = "adv_download_detail_xl_request";
        a.a(g.a("android_advertise", str, str));
        com.xunlei.downloadprovider.frame.advertisement.b.a.a().m = null;
        d.a(BrothersApplication.a()).a("android_downloadinfo", "android_downloadinfo_sell", new j(this, bVar));
    }
}

package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import android.content.Context;
import com.baidu.mobad.feeds.BaiduNative;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;

// compiled from: BaiduLoader.java
public final class c extends b {
    public c(Context context, int i) {
        super(context, i);
    }

    public final void a(a aVar) {
        a.a("adv_downloadtab_baidu_request", h.a(this.b));
        new BaiduNative(this.a, h.c(this.b), new d(this, aVar)).makeRequest();
    }
}

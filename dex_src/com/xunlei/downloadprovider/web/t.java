package com.xunlei.downloadprovider.web;

import android.os.Handler;
import com.tencent.connect.common.Constants;
import com.xunlei.downloadprovider.b.a;
import com.xunlei.downloadprovider.b.c.e;

@Deprecated
// compiled from: PublicReportClient.java
public final class t extends a {
    public t(Handler handler) {
        super(handler, null);
    }

    public final void a(String str) {
        e aVar = new com.xunlei.downloadprovider.b.c.a(str, Constants.HTTP_GET, null, null, null, new v(), 10000, 10000, 1);
        aVar.setBpOnDataLoaderCompleteListener(new u(this));
        setBpFuture(aVar);
        runBox(this);
    }
}

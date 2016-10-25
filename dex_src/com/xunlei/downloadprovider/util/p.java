package com.xunlei.downloadprovider.util;

import com.xunlei.downloadprovider.b.c.g.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: NetworkSpeedCheck.java
final class p implements c {
    final /* synthetic */ k a;

    p(k kVar) {
        this.a = kVar;
    }

    public final void a() {
        if (this.a.k != null) {
            this.a.k.obtainMessage(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED).sendToTarget();
        }
    }
}

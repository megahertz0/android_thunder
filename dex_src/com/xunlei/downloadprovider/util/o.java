package com.xunlei.downloadprovider.util;

import com.xunlei.downloadprovider.b.c.g.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;

// compiled from: NetworkSpeedCheck.java
final class o implements b {
    final /* synthetic */ k a;

    o(k kVar) {
        this.a = kVar;
    }

    public final void a(int i, Map<String, List<String>> map, byte[] bArr) {
        this.a.k.obtainMessage(XZBDevice.DOWNLOAD_LIST_ALL, XZBDevice.DOWNLOAD_LIST_RECYCLE, 0).sendToTarget();
        this.a.f = System.currentTimeMillis();
    }
}

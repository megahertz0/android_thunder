package com.xunlei.downloadprovider.util;

import com.xunlei.downloadprovider.b.c.g;
import com.xunlei.downloadprovider.b.c.g.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: NetworkSpeedCheck.java
final class n implements d {
    final /* synthetic */ k a;

    n(k kVar) {
        this.a = kVar;
    }

    public final void a(byte[] bArr, int i, g gVar) {
        k kVar = this.a;
        kVar.g = ((long) i) + kVar.g;
        new StringBuilder("totalReadedBytes = ").append(this.a.g);
        if (this.a.n >= this.a.d) {
            if (this.a.k != null) {
                this.a.k.obtainMessage(XZBDevice.DOWNLOAD_LIST_ALL).sendToTarget();
            }
            this.a.f = System.currentTimeMillis();
            gVar.cancel();
        }
    }
}

package com.xunlei.downloadprovider.bho;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ScanCodeResultActivity.java
final class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        this.a.a.b.setVisibility(XZBDevice.Wait);
        this.a.a.c.setVisibility(XZBDevice.Wait);
        if (this.a.a.p == null) {
            ScanCodeResultActivity.d(this.a.a);
            return;
        }
        this.a.a.b();
        this.a.a.a.setVisibility(0);
    }
}

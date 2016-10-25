package com.xunlei.downloadprovider.service.downloads.kernel;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: DownloadKernel.java
final class d implements Runnable {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        c cVar = this.a;
        if (cVar.a != null && cVar.b != null) {
            long[] a = c.a(new e(cVar.b, cVar.a).a());
            if (a.length > 0) {
                cVar.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, a);
            }
        }
    }
}

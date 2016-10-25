package com.xunlei.downloadprovider.vod.protocol;

import com.xunlei.downloadprovider.vod.protocol.b.a;

// compiled from: DownloadVodUtil.java
final class f implements Runnable {
    final /* synthetic */ b$c a;
    final /* synthetic */ b b;

    f(b bVar, b$c com_xunlei_downloadprovider_vod_protocol_b_c) {
        this.b = bVar;
        this.a = com_xunlei_downloadprovider_vod_protocol_b_c;
    }

    public final void run() {
        a a = this.b.a(this.a.a.f);
        if (a != null) {
            a.a(this.a.c, null, this.a.b);
        }
    }
}

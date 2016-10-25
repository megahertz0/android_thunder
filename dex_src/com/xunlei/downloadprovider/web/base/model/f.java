package com.xunlei.downloadprovider.web.base.model;

import com.xunlei.downloadprovider.c.a.b;
import org.android.spdy.SpdyProtocol;

// compiled from: ShortMovieDetailDataLoader.java
final class f implements a<Void> {
    final /* synthetic */ long a;
    final /* synthetic */ d b;

    f(d dVar, long j) {
        this.b = dVar;
        this.a = j;
    }

    public final void a(b bVar) {
        this.b.e.a(SpdyProtocol.PUBKEY_PSEQ_ADASH, bVar.b);
    }
}

package com.xunlei.downloadprovider.web.base.model;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.f;
import org.android.spdy.SpdyProtocol;

// compiled from: ShortMovieDetailDataLoader.java
final class q implements a<f> {
    final /* synthetic */ d a;

    q(d dVar) {
        this.a = dVar;
    }

    public final /* synthetic */ void a(Object obj) {
        this.a.e.b((f) obj);
    }

    public final void a(b bVar) {
        this.a.e.a(SpdyProtocol.PUBKEY_SEQ_OPEN, bVar.a);
    }
}

package com.xunlei.downloadprovider.web.base.model;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.web.base.model.a.a;
import org.android.spdy.SpdyProtocol;

// compiled from: ShortMovieDetailDataLoader.java
final class i implements a<Void> {
    final /* synthetic */ c a;
    final /* synthetic */ a b;
    final /* synthetic */ d c;

    i(d dVar, c cVar, a aVar) {
        this.c = dVar;
        this.a = cVar;
        this.b = aVar;
    }

    public final void a(b bVar) {
        this.c.e.a(SpdyProtocol.PUBKEY_SEQ_ADASH, bVar.b);
        this.b.d = false;
    }

    public final /* synthetic */ void a(Object obj) {
        this.c.e.b(this.a);
        this.b.d = true;
    }
}

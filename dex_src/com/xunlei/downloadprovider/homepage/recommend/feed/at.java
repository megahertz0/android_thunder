package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.b;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: FeedVideoItemView.java
final class at implements b {
    final /* synthetic */ ap a;

    at(ap apVar) {
        this.a = apVar;
    }

    public final void a(ab abVar) {
        abVar.a(this.a.G);
        abVar.a(this.a.b, -1, -1);
        abVar.b(SimpleLog.LOG_LEVEL_DEBUG);
        this.a.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.w.a();
        this.a.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void b(ab abVar) {
        this.a.c();
        abVar.o();
        abVar.b(this.a.G);
    }
}

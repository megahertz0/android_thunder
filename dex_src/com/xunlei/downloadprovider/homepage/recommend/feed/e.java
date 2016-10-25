package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.b;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ChannelFeedVideoItemView.java
final class e implements b {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public final void a(ab abVar) {
        abVar.a(a.k(this.a));
        abVar.a(a.l(this.a), -1, -1);
        abVar.b(SimpleLog.LOG_LEVEL_DEBUG);
        a.m(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a.n(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a.o(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a.p(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a.g(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a.a(this.a).a();
        a.q(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void b(ab abVar) {
        this.a.c();
        abVar.o();
        abVar.b(a.k(this.a));
        a.r(this.a);
    }
}

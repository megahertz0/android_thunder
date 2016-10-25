package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.channel.commonutils.misc.f.b;
import com.xiaomi.network.HttpUtils;
import com.xiaomi.push.protobuf.a.a;
import com.xiaomi.smack.util.h;
import org.android.spdy.SpdyProtocol;

class ah extends b {
    boolean a;
    final /* synthetic */ ag b;

    ah(ag agVar) {
        this.b = agVar;
        this.a = false;
    }

    public void b() {
        try {
            a b = a.b(Base64.decode(HttpUtils.a(h.a(), "http://resolver.msg.xiaomi.net/psc/?t=a", null), SpdyProtocol.PUBKEY_SEQ_OPEN));
            if (b != null) {
                ag.a(this.b, b);
                this.a = true;
                ag.a(this.b);
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("fetch config failure: ").append(e.getMessage()).toString());
        }
    }

    public void c() {
        ag.a(this.b, null);
        if (this.a) {
            synchronized (this.b) {
            }
            for (ag.a aVar : (ag.a[]) ag.b(this.b).toArray(new ag.a[ag.b(this.b).size()])) {
                aVar.a(ag.c(this.b));
            }
        }
    }
}

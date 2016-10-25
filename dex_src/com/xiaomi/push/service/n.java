package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.smack.p;
import com.xiaomi.xmpush.thrift.o;
import com.xunlei.analytics.b.c;
import org.android.spdy.SpdyProtocol;

final class n extends g {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ o c;

    n(int i, XMPushService xMPushService, o oVar) {
        this.b = xMPushService;
        this.c = oVar;
        super(i);
    }

    public final void a() {
        try {
            o a = k.a(this.b, this.c);
            a.m().a("message_obsleted", c.f);
            this.b.a(a);
        } catch (p e) {
            b.a(e);
            this.b.a(SpdyProtocol.PUBKEY_SEQ_OPEN, e);
        }
    }

    public final String b() {
        return "send ack message for obsleted message.";
    }
}

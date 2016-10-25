package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.smack.p;
import com.xunlei.analytics.b.c;
import org.android.spdy.SpdyProtocol;

final class o extends g {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ com.xiaomi.xmpush.thrift.o c;

    o(int i, XMPushService xMPushService, com.xiaomi.xmpush.thrift.o oVar) {
        this.b = xMPushService;
        this.c = oVar;
        super(i);
    }

    public final void a() {
        try {
            com.xiaomi.xmpush.thrift.o a = k.a(this.b, this.c);
            a.m().a("miui_message_unrecognized", c.f);
            this.b.a(a);
        } catch (p e) {
            b.a(e);
            this.b.a(SpdyProtocol.PUBKEY_SEQ_OPEN, e);
        }
    }

    public final String b() {
        return "send ack message for unrecognized new miui message.";
    }
}

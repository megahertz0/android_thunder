package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.smack.p;
import com.xiaomi.xmpush.thrift.o;
import org.android.spdy.SpdyProtocol;

final class m extends g {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ o c;

    m(int i, XMPushService xMPushService, o oVar) {
        this.b = xMPushService;
        this.c = oVar;
        super(i);
    }

    public final void a() {
        try {
            this.b.a(k.a(this.b, this.c));
        } catch (p e) {
            b.a(e);
            this.b.a(SpdyProtocol.PUBKEY_SEQ_OPEN, e);
        }
    }

    public final String b() {
        return "send ack message for message.";
    }
}

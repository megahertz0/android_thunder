package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.xmpush.thrift.o;
import org.android.spdy.SpdyProtocol;

final class p extends g {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ o c;
    final /* synthetic */ String d;

    p(int i, XMPushService xMPushService, o oVar, String str) {
        this.b = xMPushService;
        this.c = oVar;
        this.d = str;
        super(i);
    }

    public final void a() {
        try {
            o a = k.a(this.b, this.c);
            a.m().a("absent_target_package", this.d);
            this.b.a(a);
        } catch (com.xiaomi.smack.p e) {
            b.a(e);
            this.b.a(SpdyProtocol.PUBKEY_SEQ_OPEN, e);
        }
    }

    public final String b() {
        return "send app absent ack message for message.";
    }
}

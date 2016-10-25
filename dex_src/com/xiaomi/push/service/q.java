package com.xiaomi.push.service;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.smack.p;
import com.xiaomi.xmpush.thrift.o;
import org.android.spdy.SpdyProtocol;

final class q extends g {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ o c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;

    q(int i, XMPushService xMPushService, o oVar, String str, String str2) {
        this.b = xMPushService;
        this.c = oVar;
        this.d = str;
        this.e = str2;
        super(i);
    }

    public final void a() {
        try {
            o a = k.a(this.b, this.c);
            a.h.a(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, this.d);
            a.h.a("reason", this.e);
            this.b.a(a);
        } catch (p e) {
            b.a(e);
            this.b.a(SpdyProtocol.PUBKEY_SEQ_OPEN, e);
        }
    }

    public final String b() {
        return "send wrong message ack for message.";
    }
}

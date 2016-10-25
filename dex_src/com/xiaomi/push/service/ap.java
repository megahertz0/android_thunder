package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class ap extends g {
    final /* synthetic */ String b;
    final /* synthetic */ byte[] c;
    final /* synthetic */ XMPushService d;

    ap(XMPushService xMPushService, int i, String str, byte[] bArr) {
        this.d = xMPushService;
        this.b = str;
        this.c = bArr;
        super(i);
    }

    public void a() {
        try {
            this.d.a(this.b, this.c);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.d.a((int) XZBDevice.Stop, e);
        }
    }

    public String b() {
        return "send mi push message";
    }
}

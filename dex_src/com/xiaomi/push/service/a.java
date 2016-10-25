package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.smack.packet.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class a extends g {
    private XMPushService b;
    private c[] c;

    public a(XMPushService xMPushService, c[] cVarArr) {
        super(4);
        this.b = null;
        this.b = xMPushService;
        this.c = cVarArr;
    }

    public void a() {
        try {
            this.b.a(this.c);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.b.a((int) XZBDevice.Stop, e);
        }
    }

    public String b() {
        return "batch send message.";
    }
}

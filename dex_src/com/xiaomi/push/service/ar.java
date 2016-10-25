package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.smack.packet.f;
import com.xiaomi.smack.packet.f.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class ar extends g {
    final /* synthetic */ XMPushService b;

    ar(XMPushService xMPushService, int i) {
        this.b = xMPushService;
        super(i);
    }

    public void a() {
        if (XMPushService.e(this.b) != null) {
            XMPushService.e(this.b).a(new f(b.b), (int) XZBDevice.Delete, null);
            XMPushService.a(this.b, null);
        }
    }

    public String b() {
        return "disconnect for service destroy.";
    }
}

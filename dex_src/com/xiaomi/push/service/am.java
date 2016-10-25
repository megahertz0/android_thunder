package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.stats.e;

class am extends g {
    final /* synthetic */ XMPushService b;

    am(XMPushService xMPushService, int i) {
        this.b = xMPushService;
        super(i);
    }

    public void a() {
        e.a().a(this.b, XMPushService.a(this.b));
        XMPushService.b(this.b);
    }

    public String b() {
        return "prepare the mi push account.";
    }
}

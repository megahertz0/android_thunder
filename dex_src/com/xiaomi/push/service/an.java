package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.e;
import com.xiaomi.push.service.x.a;

class an implements a {
    final /* synthetic */ XMPushService a;

    an(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void a() {
        XMPushService.c(this.a);
        if (x.a().c() <= 0) {
            this.a.a(new e(this.a, 12, null));
        }
    }
}

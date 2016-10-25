package com.xiaomi.push.service;

import com.xiaomi.push.service.x.b.a;
import com.xiaomi.push.service.x.c;

class aq implements a {
    final /* synthetic */ XMPushService a;

    aq(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void a(c cVar, c cVar2, int i) {
        if (cVar2 == c.c) {
            j.a(this.a);
            j.b(this.a);
        } else if (cVar2 == c.a) {
            j.a(this.a, 70000001, " the push is not connected.");
        }
    }
}

package com.xiaomi.smack.util;

import com.xiaomi.channel.commonutils.misc.f.b;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.List;

final class l extends b {
    final /* synthetic */ XMPushService a;

    l(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public final void b() {
        List arrayList;
        synchronized (k.a()) {
            arrayList = new ArrayList(k.b());
            k.b().clear();
        }
        k.a(this.a, arrayList);
    }
}

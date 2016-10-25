package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.c;
import com.xiaomi.stats.e;

class ae {
    private static int e;
    private XMPushService a;
    private int b;
    private long c;
    private int d;

    static {
        e = 300000;
    }

    public ae(XMPushService xMPushService) {
        this.d = 0;
        this.a = xMPushService;
        this.b = 500;
        this.c = 0;
    }

    private int b() {
        if (this.d > 8) {
            return 300000;
        }
        if (this.d > 4) {
            return 60000;
        }
        if (this.d > 1) {
            return 10000;
        }
        if (this.c == 0) {
            return 0;
        }
        if (System.currentTimeMillis() - this.c >= 300000) {
            this.b = 500;
            return 0;
        } else if (this.b >= e) {
            return this.b;
        } else {
            int i = this.b;
            this.b = (int) (((double) this.b) * 1.5d);
            return i;
        }
    }

    public void a() {
        this.c = System.currentTimeMillis();
        this.a.a(1);
        this.d = 0;
    }

    public void a(boolean z) {
        if (!this.a.a()) {
            b.c("should not reconnect as no client or network.");
        } else if (z) {
            if (!this.a.b(1)) {
                this.d++;
            }
            this.a.a(1);
            XMPushService xMPushService = this.a;
            XMPushService xMPushService2 = this.a;
            xMPushService2.getClass();
            xMPushService.a(new c(xMPushService2));
        } else if (!this.a.b(1)) {
            int b = b();
            if (!this.a.b(1)) {
                this.d++;
            }
            b.a(new StringBuilder("schedule reconnect in ").append(b).append("ms").toString());
            XMPushService xMPushService3 = this.a;
            XMPushService xMPushService4 = this.a;
            xMPushService4.getClass();
            xMPushService3.a(new c(xMPushService4), (long) b);
            if (this.d == 2 && e.a().c()) {
                t.b();
            }
            if (this.d == 3) {
                t.a();
            }
        }
    }
}

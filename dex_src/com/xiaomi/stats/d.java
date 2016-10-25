package com.xiaomi.stats;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.a;
import com.xiaomi.smack.j;

public class d implements com.xiaomi.smack.d {
    XMPushService a;
    a b;
    private int c;
    private Exception d;
    private String e;
    private long f;
    private long g;
    private long h;
    private long i;

    d(XMPushService xMPushService) {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.a = xMPushService;
        this.e = com.xiaomi.channel.commonutils.network.d.f(xMPushService);
        c();
    }

    private void c() {
        this.g = 0;
        this.i = 0;
        this.f = 0;
        this.h = 0;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (com.xiaomi.channel.commonutils.network.d.d(this.a)) {
            this.f = elapsedRealtime;
        }
        if (this.a.e()) {
            this.h = elapsedRealtime;
        }
    }

    private synchronized void d() {
        b.c(new StringBuilder("stat connpt = ").append(this.e).append(" netDuration = ").append(this.g).append(" ChannelDuration = ").append(this.i).append(" channelConnectedTime = ").append(this.h).toString());
        com.xiaomi.push.thrift.b bVar = new com.xiaomi.push.thrift.b();
        bVar.a = (byte) 0;
        bVar.a(com.xiaomi.push.thrift.a.h.a());
        bVar.a(this.e);
        bVar.d((int) (System.currentTimeMillis() / 1000));
        bVar.b((int) (this.g / 1000));
        bVar.c((int) (this.i / 1000));
        e.a().a(bVar);
        c();
    }

    Exception a() {
        return this.d;
    }

    public void a(a aVar) {
        b();
        this.h = SystemClock.elapsedRealtime();
        g.a(0, com.xiaomi.push.thrift.a.s.a(), aVar.c(), aVar.j());
    }

    public void a(a aVar, int i, Exception exception) {
        if (this.c == 0 && this.d == null) {
            this.c = i;
            this.d = exception;
            g.b(aVar.c(), exception);
        }
        if (i == 22 && this.h != 0) {
            long f = aVar.f() - this.h;
            if (f < 0) {
                f = 0;
            }
            this.i = (f + ((long) (j.c() / 2))) + this.i;
            this.h = 0;
        }
        b();
    }

    public void a(a aVar, Exception exception) {
        g.a(0, com.xiaomi.push.thrift.a.d.a(), 1, aVar.c(), com.xiaomi.channel.commonutils.network.d.d(this.a) ? 1 : 0);
        b();
    }

    public synchronized void b() {
        if (this.a != null) {
            Object f = com.xiaomi.channel.commonutils.network.d.f(this.a);
            boolean d = com.xiaomi.channel.commonutils.network.d.d(this.a);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.f > 0) {
                this.g += elapsedRealtime - this.f;
                this.f = 0;
            }
            if (this.h != 0) {
                this.i += elapsedRealtime - this.h;
                this.h = 0;
            }
            if (d) {
                if ((!TextUtils.equals(this.e, f) && this.g > 30000) || this.g > 5400000) {
                    d();
                }
                this.e = f;
                if (this.f == 0) {
                    this.f = elapsedRealtime;
                }
                if (this.a.e()) {
                    this.h = elapsedRealtime;
                }
            }
        }
    }

    public void b(a aVar) {
        this.c = 0;
        this.d = null;
        this.b = aVar;
        g.a(0, com.xiaomi.push.thrift.a.s.a());
    }
}

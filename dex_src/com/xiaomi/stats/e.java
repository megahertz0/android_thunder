package com.xiaomi.stats;

import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.channel.commonutils.stats.a;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ag;
import com.xiaomi.push.thrift.b;
import com.xiaomi.push.thrift.c;
import com.xiaomi.smack.util.h;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.l;

public class e {
    private String a;
    private boolean b;
    private int c;
    private long d;
    private d e;
    private a f;

    public e() {
        this.b = false;
        this.f = a.a();
    }

    private b a(a.a aVar) {
        if (aVar.a == 0) {
            return aVar.c instanceof b ? (b) aVar.c : null;
        } else {
            b f = f();
            f.a(com.xiaomi.push.thrift.a.ac.a());
            f.c(aVar.a);
            f.c(aVar.b);
            return f;
        }
    }

    public static e a() {
        return a.a;
    }

    private c b(int i) {
        List arrayList = new ArrayList();
        c cVar = new c(this.a, arrayList);
        if (!d.e(this.e.a)) {
            cVar.a(com.xiaomi.push.service.d.f(this.e.a));
        }
        org.apache.thrift.transport.b bVar = new org.apache.thrift.transport.b(i);
        f a = new l.a().a(bVar);
        try {
            cVar.b(a);
        } catch (org.apache.thrift.f e) {
        }
        LinkedList c = this.f.c();
        while (c.size() > 0) {
            try {
                b a2 = a((a.a) c.getLast());
                if (a2 != null) {
                    a2.b(a);
                }
                if (bVar.a_() > i) {
                    break;
                }
                if (a2 != null) {
                    arrayList.add(a2);
                }
                c.removeLast();
            } catch (NoSuchElementException e2) {
            } catch (org.apache.thrift.f e3) {
            }
        }
        return cVar;
    }

    public static d b() {
        return a.a.e;
    }

    private void g() {
        if (this.d == 0) {
            this.d = System.currentTimeMillis();
        }
    }

    private void h() {
        if (this.b && System.currentTimeMillis() - this.d > ((long) this.c)) {
            this.b = false;
            this.d = 0;
        }
    }

    public void a(int i) {
        int i2 = 604800000;
        if (i > 0) {
            this.b = true;
            int i3 = i * 1000;
            if (i3 <= 604800000) {
                i2 = i3;
            }
            if (this.c != i2) {
                this.c = i2;
                g();
            }
        }
    }

    public synchronized void a(XMPushService xMPushService, com.xiaomi.smack.l lVar) {
        this.e = new d(xMPushService);
        this.a = com.umeng.a.d;
        if (lVar != null) {
            lVar.a(this.e);
        }
        ag.a().a(new f(this));
    }

    synchronized void a(b bVar) {
        this.f.a(bVar);
    }

    public boolean c() {
        return this.b;
    }

    boolean d() {
        h();
        return this.b && this.f.b() > 0;
    }

    synchronized c e() {
        c cVar;
        cVar = null;
        if (d()) {
            int i = 750;
            if (!d.e(h.a())) {
                i = 375;
            }
            cVar = b(i);
        }
        return cVar;
    }

    b f() {
        b bVar = new b();
        bVar.a(d.f(this.e.a));
        bVar.a = (byte) 0;
        bVar.c = 1;
        bVar.d((int) (System.currentTimeMillis() / 1000));
        if (this.e.b != null) {
            bVar.e(this.e.b.e());
        }
        return bVar;
    }
}

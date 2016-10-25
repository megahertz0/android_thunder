package com.xiaomi.stats;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.x;
import com.xiaomi.push.thrift.a;
import com.xiaomi.push.thrift.b;
import com.xiaomi.push.thrift.c;
import com.xiaomi.xmpush.thrift.ad;

public class g {
    private static final int a;

    static {
        a = a.c.a();
    }

    public static void a() {
        a(0, a);
    }

    public static void a(int i) {
        b f = e.a().f();
        f.a(a.ac.a());
        f.c(i);
        e.a().a(f);
    }

    public static synchronized void a(int i, int i2) {
        synchronized (g.class) {
            if (i2 < 16777215) {
                a.a.put(Integer.valueOf((i << 24) | i2), Long.valueOf(System.currentTimeMillis()));
            } else {
                com.xiaomi.channel.commonutils.logger.b.d("stats key should less than 16777215");
            }
        }
    }

    public static void a(int i, int i2, int i3, String str, int i4) {
        b f = e.a().f();
        f.a((byte) i);
        f.a(i2);
        f.b(i3);
        f.b(str);
        f.c(i4);
        e.a().a(f);
    }

    public static synchronized void a(int i, int i2, String str, int i3) {
        synchronized (g.class) {
            long currentTimeMillis = System.currentTimeMillis();
            int i4 = (i << 24) | i2;
            if (a.a.containsKey(Integer.valueOf(i4))) {
                b f = e.a().f();
                f.a(i2);
                f.b((int) (currentTimeMillis - ((Long) a.a.get(Integer.valueOf(i4))).longValue()));
                f.b(str);
                if (i3 >= 0) {
                    f.c(i3);
                }
                e.a().a(f);
                a.a.remove(Integer.valueOf(i2));
            } else {
                com.xiaomi.channel.commonutils.logger.b.d("stats key not found");
            }
        }
    }

    public static void a(XMPushService xMPushService, x.b bVar) {
        new a(xMPushService, bVar).a();
    }

    public static void a(String str, int i, Exception exception) {
        b f = e.a().f();
        if (i > 0) {
            f.a(a.i.a());
            f.b(str);
            f.b(i);
            e.a().a(f);
            return;
        }
        try {
            c.a a = c.a(exception);
            f.a(a.a.a());
            f.c(a.b);
            f.b(str);
            e.a().a(f);
        } catch (NullPointerException e) {
        }
    }

    public static void a(String str, Exception exception) {
        try {
            c.a b = c.b(exception);
            b f = e.a().f();
            f.a(b.a.a());
            f.c(b.b);
            f.b(str);
            e.a().a(f);
        } catch (NullPointerException e) {
        }
    }

    public static void b() {
        a(0, a, null, -1);
    }

    public static void b(String str, Exception exception) {
        try {
            c.a d = c.d(exception);
            b f = e.a().f();
            f.a(d.a.a());
            f.c(d.b);
            f.b(str);
            e.a().a(f);
        } catch (NullPointerException e) {
        }
    }

    public static String c() {
        c e = e.a().e();
        if (e == null) {
            return null;
        }
        byte[] a = ad.a(e);
        return a != null ? new String(com.xiaomi.channel.commonutils.string.a.a(a)) : null;
    }
}

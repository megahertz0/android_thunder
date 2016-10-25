package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.anr.b;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// compiled from: BUGLY
public final class c {
    public static int a;
    public static boolean b;
    public static boolean c;
    public static int d;
    public static int e;
    public static long f;
    public static String g;
    public static boolean h;
    public static String i;
    public static int j;
    private static c n;
    public final b k;
    public a l;
    private final Context m;
    private final e o;
    private final NativeCrashHandler p;
    private com.tencent.bugly.crashreport.common.strategy.a q;
    private v r;
    private final b s;
    private final z t;
    private Boolean u;

    // compiled from: BUGLY
    final class AnonymousClass_1 implements Runnable {
        private /* synthetic */ boolean a;
        private /* synthetic */ Thread b;
        private /* synthetic */ Throwable c;
        private /* synthetic */ String d;
        private /* synthetic */ byte[] e;

        AnonymousClass_1(boolean z, Thread thread, Throwable th, String str, byte[] bArr) {
            this.a = z;
            this.b = thread;
            this.c = th;
            this.d = str;
            this.e = bArr;
        }

        public final void run() {
            try {
                w.c("post a throwable %b", Boolean.valueOf(this.a));
                c.this.o.a(this.b, this.c, false, this.d, this.e);
            } catch (Throwable th) {
                if (!w.b(th)) {
                    th.printStackTrace();
                }
                w.e("java catch error: %s", this.c.toString());
            }
        }
    }

    static {
        a = 0;
        b = false;
        c = true;
        d = 20000;
        e = 20000;
        f = 604800000;
        g = null;
        h = false;
        i = null;
        j = 5000;
    }

    private c(int i, Context context, v vVar, boolean z, a aVar, n nVar, String str) {
        Context context2;
        a = i;
        if (context == null) {
            context2 = context;
        } else {
            context2 = context.getApplicationContext();
            if (context2 == null) {
                context2 = context;
            }
        }
        this.m = context2;
        this.q = com.tencent.bugly.crashreport.common.strategy.a.a();
        t a = t.a();
        o a2 = o.a();
        com.tencent.bugly.crashreport.common.info.a a3 = com.tencent.bugly.crashreport.common.info.a.a(context2);
        this.r = vVar;
        this.l = aVar;
        this.k = new b(i, context2, a, a2, this.q, aVar, nVar);
        this.o = new e(context2, this.k, this.q, a3);
        this.p = NativeCrashHandler.getInstance(context2, a3, this.k, this.q, vVar, z, str);
        this.s = new b(context2, this.q, a3, vVar, this.k);
        this.t = new z(context2, this.k, this.q, a3, vVar, aVar);
        BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
        instance.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
        instance.regist(context2, this.k);
    }

    public static synchronized void a(int i, Context context, boolean z, a aVar, n nVar, String str) {
        synchronized (c.class) {
            if (n == null) {
                n = new c(1004, context, v.a(), z, aVar, null, null);
            }
        }
    }

    public static c a() {
        return n;
    }

    public final void a(StrategyBean strategyBean) {
        this.o.a(strategyBean);
        this.p.onStrategyChanged(strategyBean);
        this.s.a(strategyBean);
        this.t.a(strategyBean);
    }

    public final boolean b() {
        Boolean bool = this.u;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = com.tencent.bugly.crashreport.common.info.a.a().d;
        List<q> a = o.a().a(1);
        List arrayList = new ArrayList();
        if (a == null || a.size() <= 0) {
            this.u = Boolean.valueOf(false);
            return false;
        }
        for (q qVar : a) {
            if (str.equals(qVar.c)) {
                this.u = Boolean.valueOf(true);
                arrayList.add(qVar);
            }
        }
        if (arrayList.size() > 0) {
            o.a();
            o.a(arrayList);
        }
        return true;
    }

    public final synchronized void c() {
        this.o.a();
        this.p.setUserOpened(true);
        this.s.a(true);
        this.t.a(true, -1);
    }

    public final synchronized void d() {
        this.o.b();
        this.p.setUserOpened(false);
        this.s.a(false);
        this.t.a(false, -1);
    }

    public final void e() {
        this.o.a();
    }

    public final void f() {
        this.p.setUserOpened(false);
    }

    public final void g() {
        this.p.setUserOpened(true);
    }

    public final void h() {
        this.s.a(true);
    }

    public final void i() {
        this.s.a(false);
    }

    public final void a(long j) {
        this.t.a(true, j);
    }

    public final void j() {
        this.t.a(false, -1);
    }

    public final synchronized void k() {
        this.p.testNativeCrash();
    }

    public final synchronized void l() {
        int i = 0;
        synchronized (this) {
            while (true) {
                int i2 = i + 1;
                if (i >= 30) {
                    break;
                }
                try {
                    w.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    try {
                        Thread.sleep(5000);
                        i = i2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        i = i2;
                    }
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public final synchronized void m() {
        z.a();
    }

    public final boolean n() {
        return this.s.a();
    }

    public final void a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        this.r.b(new AnonymousClass_1(false, thread, th, null, null));
    }

    public final void a(CrashDetailBean crashDetailBean) {
        this.k.c(crashDetailBean);
    }

    public final void b(long j) {
        v.a().a(new Thread() {
            public final void run() {
                if (com.tencent.bugly.proguard.a.a(c.this.m, "local_crash_lock", 10000)) {
                    List a = c.this.k.a();
                    if (a != null && a.size() > 0) {
                        List arrayList;
                        int size = a.size();
                        if (((long) size) > 100) {
                            arrayList = new ArrayList();
                            Collections.sort(a);
                            for (int i = 0; ((long) i) < 100; i++) {
                                arrayList.add(a.get((size - 1) - i));
                            }
                        } else {
                            arrayList = a;
                        }
                        c.this.k.a(arrayList, 0, false, false);
                    }
                    com.tencent.bugly.proguard.a.b(c.this.m, "local_crash_lock");
                }
            }
        }, j);
    }
}

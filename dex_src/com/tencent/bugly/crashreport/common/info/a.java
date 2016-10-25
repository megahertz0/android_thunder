package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.alipay.sdk.util.h;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.b;
import com.tencent.bugly.proguard.w;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

// compiled from: BUGLY
public final class a {
    private static a W;
    private final Context A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private long K;
    private long L;
    private long M;
    private String N;
    private String O;
    private Map<String, PlugInBean> P;
    private boolean Q;
    private String R;
    private String S;
    private Boolean T;
    private String U;
    private Map<String, PlugInBean> V;
    private int X;
    private int Y;
    private Map<String, String> Z;
    public final long a;
    private Map<String, String> aa;
    private String ab;
    private String ac;
    private String ad;
    private String ae;
    private final Object af;
    private final Object ag;
    private final Object ah;
    public final byte b;
    public String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public long h;
    public String i;
    public String j;
    public String k;
    public String l;
    public List<String> m;
    public boolean n;
    public String o;
    public long p;
    public long q;
    public long r;
    public long s;
    public boolean t;
    public String u;
    public String v;
    public String w;
    public boolean x;
    public boolean y;
    public HashMap<String, String> z;

    static {
        W = null;
    }

    private a(Context context) {
        Context context2;
        this.D = UtilityImpl.NET_TYPE_UNKNOWN;
        this.E = UtilityImpl.NET_TYPE_UNKNOWN;
        this.F = com.umeng.a.d;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = -1;
        this.L = -1;
        this.M = -1;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = true;
        this.R = null;
        this.i = null;
        this.S = null;
        this.j = null;
        this.T = null;
        this.U = null;
        this.k = null;
        this.l = null;
        this.V = null;
        this.m = null;
        this.X = -1;
        this.Y = -1;
        this.Z = new HashMap();
        this.aa = new HashMap();
        this.o = UtilityImpl.NET_TYPE_UNKNOWN;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = false;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.y = false;
        this.z = new HashMap();
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = new Object();
        this.ag = new Object();
        this.ah = new Object();
        this.a = System.currentTimeMillis();
        if (context == null) {
            context2 = context;
        } else {
            context2 = context.getApplicationContext();
            if (context2 == null) {
                context2 = context;
            }
        }
        this.A = context2;
        this.b = (byte) 1;
        PackageInfo b = AppInfo.b(context);
        if (b != null) {
            this.i = AppInfo.a(b);
            this.u = this.i;
            this.v = AppInfo.b(b);
        }
        this.c = AppInfo.a(context);
        this.d = AppInfo.c(context);
        this.e = b.l();
        this.f = b.a();
        this.g = new StringBuilder("Android ").append(b.b()).append(",level ").append(b.c()).toString();
        new StringBuilder().append(this.f).append(h.b).append(this.g);
        Map d = AppInfo.d(context);
        if (d != null) {
            try {
                this.m = AppInfo.a(d);
                String str = (String) d.get("BUGLY_APPID");
                if (str != null) {
                    this.S = str;
                }
                str = (String) d.get("BUGLY_APP_VERSION");
                if (str != null) {
                    this.i = str;
                }
                str = (String) d.get("BUGLY_APP_CHANNEL");
                if (str != null) {
                    this.j = str;
                }
                str = (String) d.get("BUGLY_ENABLE_DEBUG");
                if (str != null) {
                    boolean z;
                    if (str.toLowerCase().equals("true")) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.t = z;
                }
                str = (String) d.get("com.tencent.rdm.uuid");
                if (str != null) {
                    this.w = str;
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.y = true;
                w.c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th2) {
            if (b.b) {
                th2.printStackTrace();
            }
        }
        w.c("com info create end", new Object[0]);
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (W == null) {
                W = new a(context);
            }
            aVar = W;
        }
        return aVar;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            aVar = W;
        }
        return aVar;
    }

    public static String b() {
        return "2.2.0";
    }

    public final void c() {
        synchronized (this.af) {
            this.B = UUID.randomUUID().toString();
        }
    }

    public final String d() {
        if (this.B == null) {
            synchronized (this.af) {
                if (this.B == null) {
                    this.B = UUID.randomUUID().toString();
                }
            }
        }
        return this.B;
    }

    public final String e() {
        return this.S;
    }

    public final void a(String str) {
        this.S = str;
    }

    public final synchronized String f() {
        return this.D;
    }

    public final synchronized void b(String str) {
        if (str == null) {
            str = "10000";
        }
        this.D = str;
    }

    public final synchronized String g() {
        String str;
        if (this.C != null) {
            str = this.C;
        } else {
            this.C = j() + "|" + l() + "|" + m();
            str = this.C;
        }
        return str;
    }

    public final synchronized void c(String str) {
        this.C = str;
    }

    public final synchronized String h() {
        return this.E;
    }

    public final synchronized void d(String str) {
        this.E = str;
    }

    public final synchronized String i() {
        return this.F;
    }

    public final synchronized void e(String str) {
        this.F = str;
    }

    public final synchronized String j() {
        String str;
        if (this.Q) {
            if (this.G == null) {
                this.G = b.a(this.A);
            }
            str = this.G;
        } else {
            str = com.umeng.a.d;
        }
        return str;
    }

    public final synchronized String k() {
        String str;
        if (this.Q) {
            if (this.H == null) {
                this.H = b.d(this.A);
            }
            str = this.H;
        } else {
            str = com.umeng.a.d;
        }
        return str;
    }

    public final synchronized String l() {
        String str;
        if (this.Q) {
            if (this.I == null) {
                this.I = b.b(this.A);
            }
            str = this.I;
        } else {
            str = com.umeng.a.d;
        }
        return str;
    }

    public final synchronized String m() {
        String str;
        if (this.Q) {
            if (this.J == null) {
                this.J = b.c(this.A);
            }
            str = this.J;
        } else {
            str = com.umeng.a.d;
        }
        return str;
    }

    public final synchronized long n() {
        if (this.K <= 0) {
            this.K = b.e();
        }
        return this.K;
    }

    public final synchronized long o() {
        if (this.L <= 0) {
            this.L = b.g();
        }
        return this.L;
    }

    public final synchronized long p() {
        if (this.M <= 0) {
            this.M = b.i();
        }
        return this.M;
    }

    public final synchronized String q() {
        if (this.N == null) {
            this.N = b.d();
        }
        return this.N;
    }

    public final String r() {
        if (this.O == null) {
            synchronized (this.ag) {
                if (this.O == null) {
                    this.O = b.g(this.A);
                }
            }
        }
        return this.O;
    }

    public final void a(String str, String str2) {
        if (str != null && str2 != null) {
            synchronized (this.ah) {
                this.z.put(str, str2);
            }
        }
    }

    public final void s() {
        try {
            Map all = this.A.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.ah) {
                    for (Entry entry : all.entrySet()) {
                        try {
                            this.z.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            w.a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            w.a(th2);
        }
        if (!this.z.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Entry entry2 : this.z.entrySet()) {
                stringBuilder.append("[");
                stringBuilder.append((String) entry2.getKey());
                stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                stringBuilder.append((String) entry2.getValue());
                stringBuilder.append("] ");
            }
            c("SDK_INFO", stringBuilder.toString());
        }
    }

    public final String t() {
        if (this.ae == null) {
            this.ae = AppInfo.e(this.A);
        }
        return this.ae;
    }

    public final synchronized Map<String, PlugInBean> u() {
        return null;
    }

    public final String v() {
        if (this.R == null) {
            this.R = b.k();
        }
        return this.R;
    }

    public final synchronized Boolean w() {
        if (this.T == null) {
            this.T = Boolean.valueOf(b.h(this.A));
        }
        return this.T;
    }

    public final synchronized String x() {
        if (this.U == null) {
            this.U = b.f(this.A);
            w.a("rom:%s", this.U);
        }
        return this.U;
    }

    public final synchronized Map<String, String> y() {
        Map<String, String> map;
        if (this.Z.size() <= 0) {
            map = null;
        } else {
            map = new HashMap(this.Z);
        }
        return map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String f(java.lang.String r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.a.f(java.lang.String):java.lang.String");
        /*
        this = this;
        r1 = 1;
        r0 = 0;
        monitor-enter(r3);
        if (r4 == 0) goto L_0x0020;
    L_0x0005:
        r2 = r4.trim();	 Catch:{ all -> 0x002b }
        r2 = r2.length();	 Catch:{ all -> 0x002b }
        if (r2 <= 0) goto L_0x0020;
    L_0x000f:
        if (r0 == 0) goto L_0x0022;
    L_0x0011:
        r0 = "key should not be empty %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x002b }
        r2 = 0;
        r1[r2] = r4;	 Catch:{ all -> 0x002b }
        com.tencent.bugly.proguard.w.d(r0, r1);	 Catch:{ all -> 0x002b }
        r0 = 0;
    L_0x001e:
        monitor-exit(r3);
        return r0;
    L_0x0020:
        r0 = r1;
        goto L_0x000f;
    L_0x0022:
        r0 = r3.Z;	 Catch:{ all -> 0x002b }
        r0 = r0.remove(r4);	 Catch:{ all -> 0x002b }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x002b }
        goto L_0x001e;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        */
    }

    public final synchronized void z() {
        this.Z.clear();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String g(java.lang.String r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.a.g(java.lang.String):java.lang.String");
        /*
        this = this;
        r1 = 1;
        r0 = 0;
        monitor-enter(r3);
        if (r4 == 0) goto L_0x0020;
    L_0x0005:
        r2 = r4.trim();	 Catch:{ all -> 0x002b }
        r2 = r2.length();	 Catch:{ all -> 0x002b }
        if (r2 <= 0) goto L_0x0020;
    L_0x000f:
        if (r0 == 0) goto L_0x0022;
    L_0x0011:
        r0 = "key should not be empty %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x002b }
        r2 = 0;
        r1[r2] = r4;	 Catch:{ all -> 0x002b }
        com.tencent.bugly.proguard.w.d(r0, r1);	 Catch:{ all -> 0x002b }
        r0 = 0;
    L_0x001e:
        monitor-exit(r3);
        return r0;
    L_0x0020:
        r0 = r1;
        goto L_0x000f;
    L_0x0022:
        r0 = r3.Z;	 Catch:{ all -> 0x002b }
        r0 = r0.get(r4);	 Catch:{ all -> 0x002b }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x002b }
        goto L_0x001e;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        */
    }

    public final synchronized void b(String str, String str2) {
        Object obj = null;
        synchronized (this) {
            Object obj2;
            if (str != null) {
                if (str.trim().length() > 0) {
                    obj2 = null;
                    if (obj2 == null) {
                        if (str2 == null || str2.trim().length() <= 0) {
                            int i = 1;
                        }
                        if (obj == null) {
                            this.Z.put(str, str2);
                        }
                    }
                    w.d("key&value should not be empty %s %s", str, str2);
                }
            }
            int i2 = 1;
            if (obj2 == null) {
                int i3 = 1;
                if (obj == null) {
                    this.Z.put(str, str2);
                }
            }
            w.d("key&value should not be empty %s %s", str, str2);
        }
    }

    public final synchronized int A() {
        return this.Z.size();
    }

    public final synchronized Set<String> B() {
        return this.Z.keySet();
    }

    public final synchronized void c(String str, String str2) {
        Object obj = null;
        synchronized (this) {
            Object obj2;
            if (str != null) {
                if (str.trim().length() > 0) {
                    obj2 = null;
                    if (obj2 == null) {
                        if (str2 == null || str2.trim().length() <= 0) {
                            int i = 1;
                        }
                        if (obj == null) {
                            this.aa.put(str, str2);
                        }
                    }
                    w.d("server key&value should not be empty %s %s", str, str2);
                }
            }
            int i2 = 1;
            if (obj2 == null) {
                int i3 = 1;
                if (obj == null) {
                    this.aa.put(str, str2);
                }
            }
            w.d("server key&value should not be empty %s %s", str, str2);
        }
    }

    public final synchronized Map<String, String> C() {
        Map<String, String> map;
        if (this.aa.size() <= 0) {
            map = null;
        } else {
            map = new HashMap(this.aa);
        }
        return map;
    }

    public final synchronized void a(int i) {
        if (this.X != i) {
            this.X = i;
            w.a("user scene tag %d changed to tag %d", Integer.valueOf(r0), Integer.valueOf(this.X));
        }
    }

    public final synchronized int D() {
        return this.X;
    }

    public final synchronized int E() {
        return this.Y;
    }

    public final synchronized boolean F() {
        return AppInfo.f(this.A);
    }

    public final synchronized Map<String, PlugInBean> G() {
        return null;
    }

    public final synchronized int H() {
        return b.c();
    }

    public final String I() {
        if (this.ab == null) {
            this.ab = b.m();
        }
        return this.ab;
    }

    public final String J() {
        if (this.ac == null) {
            this.ac = b.i(this.A);
        }
        return this.ac;
    }

    public final String K() {
        return b.j(this.A);
    }

    public final String L() {
        return b.n();
    }

    public final String M() {
        if (this.ad == null) {
            this.ad = b.k(this.A);
        }
        return this.ad;
    }

    public final long N() {
        return b.o();
    }
}

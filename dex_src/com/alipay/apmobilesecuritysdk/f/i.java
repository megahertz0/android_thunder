package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import com.umeng.a;
import java.util.HashMap;
import java.util.Map;

public final class i {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static Map<String, String> f;

    static {
        a = a.d;
        b = a.d;
        c = a.d;
        d = a.d;
        e = a.d;
        f = new HashMap();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String a(java.lang.String r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.f.i.a(java.lang.String):java.lang.String");
        /*
        r1 = com.alipay.apmobilesecuritysdk.f.i.class;
        monitor-enter(r1);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002f }
        r2 = "apdidTokenCache";
        r0.<init>(r2);	 Catch:{ all -> 0x002f }
        r0 = r0.append(r3);	 Catch:{ all -> 0x002f }
        r0 = r0.toString();	 Catch:{ all -> 0x002f }
        r2 = f;	 Catch:{ all -> 0x002f }
        r2 = r2.containsKey(r0);	 Catch:{ all -> 0x002f }
        if (r2 == 0) goto L_0x002b;
    L_0x001b:
        r2 = f;	 Catch:{ all -> 0x002f }
        r0 = r2.get(r0);	 Catch:{ all -> 0x002f }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x002f }
        r2 = com.alipay.b.a.a.a.a.b(r0);	 Catch:{ all -> 0x002f }
        if (r2 == 0) goto L_0x002b;
    L_0x0029:
        monitor-exit(r1);
        return r0;
    L_0x002b:
        r0 = "";
        goto L_0x0029;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
    }

    public static synchronized void a() {
        synchronized (i.class) {
        }
    }

    public static synchronized void a(b bVar) {
        synchronized (i.class) {
            if (bVar != null) {
                a = bVar.a();
                b = bVar.b();
                c = bVar.c();
            }
        }
    }

    public static synchronized void a(c cVar) {
        synchronized (i.class) {
            if (cVar != null) {
                a = cVar.a();
                b = cVar.b();
                d = cVar.d();
                e = cVar.e();
                c = cVar.c();
            }
        }
    }

    public static synchronized void a(String str, String str2) {
        synchronized (i.class) {
            String toString = new StringBuilder("apdidTokenCache").append(str).toString();
            if (f.containsKey(toString)) {
                f.remove(toString);
            }
            f.put(toString, str2);
        }
    }

    public static synchronized boolean a(Context context, String str) {
        boolean z;
        synchronized (i.class) {
            try {
                if (Math.abs(System.currentTimeMillis() - h.c(context, str)) < 86400000) {
                    z = true;
                }
            } catch (Throwable th) {
                com.alipay.apmobilesecuritysdk.c.a.a(th);
            }
            z = false;
        }
        return z;
    }

    public static synchronized String b() {
        String str;
        synchronized (i.class) {
            str = a;
        }
        return str;
    }

    public static void b(String str) {
        a = str;
    }

    public static synchronized String c() {
        String str;
        synchronized (i.class) {
            str = b;
        }
        return str;
    }

    public static void c(String str) {
        b = str;
    }

    public static synchronized String d() {
        String str;
        synchronized (i.class) {
            str = d;
        }
        return str;
    }

    public static void d(String str) {
        c = str;
    }

    public static synchronized String e() {
        String str;
        synchronized (i.class) {
            str = e;
        }
        return str;
    }

    public static void e(String str) {
        d = str;
    }

    public static synchronized String f() {
        String str;
        synchronized (i.class) {
            str = c;
        }
        return str;
    }

    public static void f(String str) {
        e = str;
    }

    public static synchronized c g() {
        c cVar;
        synchronized (i.class) {
            cVar = new c(a, b, c, d, e);
        }
        return cVar;
    }

    public static void h() {
        f.clear();
        a = a.d;
        b = a.d;
        d = a.d;
        e = a.d;
        c = a.d;
    }
}

package com.tencent.bugly.crashreport.crash.h5;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.a;

// compiled from: BUGLY
public final class b {
    private Context a;
    private com.tencent.bugly.crashreport.crash.b b;
    private a c;
    private com.tencent.bugly.crashreport.common.info.a d;

    public b(Context context, com.tencent.bugly.crashreport.crash.b bVar, a aVar, com.tencent.bugly.crashreport.common.info.a aVar2, BuglyStrategy.a aVar3) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Thread r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.h5.b.a(java.lang.Thread, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
        /*
        this = this;
        r6 = 0;
        r0 = "H5 Crash Happen";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = r7.c;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.b();	 Catch:{ Throwable -> 0x009b }
        if (r0 != 0) goto L_0x002e;
    L_0x0011:
        r0 = "waiting for remote sync";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x009b }
        com.tencent.bugly.proguard.w.e(r0, r1);	 Catch:{ Throwable -> 0x009b }
        r0 = r6;
    L_0x001b:
        r1 = r7.c;	 Catch:{ Throwable -> 0x009b }
        r1 = r1.b();	 Catch:{ Throwable -> 0x009b }
        if (r1 != 0) goto L_0x002e;
    L_0x0023:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x0096 }
    L_0x0028:
        r0 = r0 + 500;
        r1 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        if (r0 < r1) goto L_0x001b;
    L_0x002e:
        r0 = r7.c;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.b();	 Catch:{ Throwable -> 0x009b }
        if (r0 != 0) goto L_0x003f;
    L_0x0036:
        r0 = "no remote but still store!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x009b }
        com.tencent.bugly.proguard.w.d(r0, r1);	 Catch:{ Throwable -> 0x009b }
    L_0x003f:
        r0 = r7.c;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.c();	 Catch:{ Throwable -> 0x009b }
        r1 = r0.d;	 Catch:{ Throwable -> 0x009b }
        if (r1 != 0) goto L_0x00ae;
    L_0x0049:
        r1 = r7.c;	 Catch:{ Throwable -> 0x009b }
        r1 = r1.b();	 Catch:{ Throwable -> 0x009b }
        if (r1 == 0) goto L_0x00ae;
    L_0x0051:
        r0 = "crash report was closed by remote , will not upload to Bugly , print local for helpful!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x009b }
        com.tencent.bugly.proguard.w.e(r0, r1);	 Catch:{ Throwable -> 0x009b }
        r0 = "H5";
        r1 = com.tencent.bugly.proguard.a.b();	 Catch:{ Throwable -> 0x009b }
        r2 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r2 = r2.d;	 Catch:{ Throwable -> 0x009b }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x009b }
        r3.<init>();	 Catch:{ Throwable -> 0x009b }
        r3 = r3.append(r9);	 Catch:{ Throwable -> 0x009b }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x009b }
        r3 = r3.append(r10);	 Catch:{ Throwable -> 0x009b }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x009b }
        r3 = r3.append(r11);	 Catch:{ Throwable -> 0x009b }
        r4 = r3.toString();	 Catch:{ Throwable -> 0x009b }
        r5 = 0;
        r3 = r8;
        com.tencent.bugly.crashreport.crash.b.a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x009b }
        r0 = "handle end";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
    L_0x0095:
        return;
    L_0x0096:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Throwable -> 0x009b }
        goto L_0x0028;
    L_0x009b:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);	 Catch:{ all -> 0x021a }
        if (r1 != 0) goto L_0x00a5;
    L_0x00a2:
        r0.printStackTrace();	 Catch:{ all -> 0x021a }
    L_0x00a5:
        r0 = "handle end";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0095;
    L_0x00ae:
        r0 = r0.j;	 Catch:{ Throwable -> 0x009b }
        if (r0 != 0) goto L_0x00c4;
    L_0x00b2:
        r0 = "cocos report is disabled.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x009b }
        com.tencent.bugly.proguard.w.e(r0, r1);	 Catch:{ Throwable -> 0x009b }
        r0 = "handle end";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0095;
    L_0x00c4:
        r5 = new com.tencent.bugly.crashreport.crash.CrashDetailBean;	 Catch:{ Throwable -> 0x009b }
        r5.<init>();	 Catch:{ Throwable -> 0x009b }
        r0 = com.tencent.bugly.crashreport.common.info.b.h();	 Catch:{ Throwable -> 0x009b }
        r5.B = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = com.tencent.bugly.crashreport.common.info.b.f();	 Catch:{ Throwable -> 0x009b }
        r5.C = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = com.tencent.bugly.crashreport.common.info.b.j();	 Catch:{ Throwable -> 0x009b }
        r5.D = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.o();	 Catch:{ Throwable -> 0x009b }
        r5.E = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.n();	 Catch:{ Throwable -> 0x009b }
        r5.F = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.p();	 Catch:{ Throwable -> 0x009b }
        r5.G = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.a;	 Catch:{ Throwable -> 0x009b }
        r1 = com.tencent.bugly.crashreport.crash.c.d;	 Catch:{ Throwable -> 0x009b }
        r2 = 0;
        r0 = com.tencent.bugly.proguard.a.a(r0, r1, r2);	 Catch:{ Throwable -> 0x009b }
        r5.w = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = 5;
        r5.b = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.g();	 Catch:{ Throwable -> 0x009b }
        r5.e = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.i;	 Catch:{ Throwable -> 0x009b }
        r5.f = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.v();	 Catch:{ Throwable -> 0x009b }
        r5.g = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.f();	 Catch:{ Throwable -> 0x009b }
        r5.m = r0;	 Catch:{ Throwable -> 0x009b }
        r5.n = r9;	 Catch:{ Throwable -> 0x009b }
        r5.o = r10;	 Catch:{ Throwable -> 0x009b }
        r0 = "0";
        r5.p = r0;	 Catch:{ Throwable -> 0x009b }
        r5.q = r11;	 Catch:{ Throwable -> 0x009b }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x009b }
        r5.r = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r5.q;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.getBytes();	 Catch:{ Throwable -> 0x009b }
        r0 = com.tencent.bugly.proguard.a.c(r0);	 Catch:{ Throwable -> 0x009b }
        r5.u = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = com.tencent.bugly.crashreport.crash.c.e;	 Catch:{ Throwable -> 0x009b }
        r1 = 0;
        r0 = com.tencent.bugly.proguard.a.a(r0, r1);	 Catch:{ Throwable -> 0x009b }
        r5.y = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.d;	 Catch:{ Throwable -> 0x009b }
        r5.z = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x009b }
        r0.<init>();	 Catch:{ Throwable -> 0x009b }
        r1 = r8.getName();	 Catch:{ Throwable -> 0x009b }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x009b }
        r1 = "(";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x009b }
        r2 = r8.getId();	 Catch:{ Throwable -> 0x009b }
        r0 = r0.append(r2);	 Catch:{ Throwable -> 0x009b }
        r1 = ")";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x009b }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x009b }
        r5.A = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.x();	 Catch:{ Throwable -> 0x009b }
        r5.H = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.u();	 Catch:{ Throwable -> 0x009b }
        r5.h = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.a;	 Catch:{ Throwable -> 0x009b }
        r5.L = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.n;	 Catch:{ Throwable -> 0x009b }
        r5.M = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.D();	 Catch:{ Throwable -> 0x009b }
        r5.O = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.E();	 Catch:{ Throwable -> 0x009b }
        r5.P = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.y();	 Catch:{ Throwable -> 0x009b }
        r5.Q = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.C();	 Catch:{ Throwable -> 0x009b }
        r5.R = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r7.b;	 Catch:{ Throwable -> 0x009b }
        r0.b(r5);	 Catch:{ Throwable -> 0x009b }
        r0 = 0;
        r0 = com.tencent.bugly.proguard.x.a(r0);	 Catch:{ Throwable -> 0x009b }
        r5.x = r0;	 Catch:{ Throwable -> 0x009b }
        r0 = r5.N;	 Catch:{ Throwable -> 0x009b }
        if (r0 != 0) goto L_0x01c7;
    L_0x01c0:
        r0 = new java.util.LinkedHashMap;	 Catch:{ Throwable -> 0x009b }
        r0.<init>();	 Catch:{ Throwable -> 0x009b }
        r5.N = r0;	 Catch:{ Throwable -> 0x009b }
    L_0x01c7:
        if (r12 == 0) goto L_0x01ce;
    L_0x01c9:
        r0 = r5.N;	 Catch:{ Throwable -> 0x009b }
        r0.putAll(r12);	 Catch:{ Throwable -> 0x009b }
    L_0x01ce:
        r0 = "H5";
        r1 = com.tencent.bugly.proguard.a.b();	 Catch:{ Throwable -> 0x009b }
        r2 = r7.d;	 Catch:{ Throwable -> 0x009b }
        r2 = r2.d;	 Catch:{ Throwable -> 0x009b }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x009b }
        r3.<init>();	 Catch:{ Throwable -> 0x009b }
        r3 = r3.append(r9);	 Catch:{ Throwable -> 0x009b }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x009b }
        r3 = r3.append(r10);	 Catch:{ Throwable -> 0x009b }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x009b }
        r3 = r3.append(r11);	 Catch:{ Throwable -> 0x009b }
        r4 = r3.toString();	 Catch:{ Throwable -> 0x009b }
        r3 = r8;
        com.tencent.bugly.crashreport.crash.b.a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x009b }
        r0 = r7.b;	 Catch:{ Throwable -> 0x009b }
        r0 = r0.a(r5);	 Catch:{ Throwable -> 0x009b }
        if (r0 != 0) goto L_0x0210;
    L_0x0208:
        r0 = r7.b;	 Catch:{ Throwable -> 0x009b }
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r1 = 0;
        r0.a(r5, r2, r1);	 Catch:{ Throwable -> 0x009b }
    L_0x0210:
        r0 = "handle end";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0095;
    L_0x021a:
        r0 = move-exception;
        r1 = "handle end";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
        throw r0;
        */
    }
}

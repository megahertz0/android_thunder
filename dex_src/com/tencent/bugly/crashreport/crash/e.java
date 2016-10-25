package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import org.android.agoo.message.MessageService;

// compiled from: BUGLY
public final class e implements UncaughtExceptionHandler {
    private static UncaughtExceptionHandler f;
    private static boolean h;
    private static final Object i;
    private static int j;
    private Context a;
    private b b;
    private a c;
    private com.tencent.bugly.crashreport.common.info.a d;
    private UncaughtExceptionHandler e;
    private boolean g;

    static {
        i = new Object();
    }

    public e(Context context, b bVar, a aVar, com.tencent.bugly.crashreport.common.info.a aVar2) {
        this.g = false;
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final synchronized void a() {
        if (j >= 10) {
            w.a("java crash handler over %d, no need set.", Integer.valueOf(XZBDevice.Stop));
        } else {
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (!(defaultUncaughtExceptionHandler == null || getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName()))) {
                if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                    w.a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                    f = defaultUncaughtExceptionHandler;
                    this.e = defaultUncaughtExceptionHandler;
                } else {
                    w.a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.e = defaultUncaughtExceptionHandler;
                }
                a(defaultUncaughtExceptionHandler);
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.g = true;
                j++;
                w.a("registered java monitor: %s", toString());
            }
        }
    }

    public final synchronized void b() {
        this.g = false;
        w.a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            w.a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.e);
            j--;
        }
    }

    private synchronized void a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.e = uncaughtExceptionHandler;
    }

    private CrashDetailBean b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String str2;
        boolean n = c.a().n();
        if (n && z) {
            str2 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
        } else {
            str2 = com.umeng.a.d;
        }
        if (n && z) {
            w.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        w.e(MessageService.MSG_DB_NOTIFY_DISMISS, new Object[0]);
        crashDetailBean.B = b.h();
        crashDetailBean.C = b.f();
        crashDetailBean.D = b.j();
        crashDetailBean.E = this.d.o();
        crashDetailBean.F = this.d.n();
        crashDetailBean.G = this.d.p();
        crashDetailBean.w = com.tencent.bugly.proguard.a.a(this.a, c.d, null);
        crashDetailBean.x = x.a(z);
        String str3 = "user log size:%d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.x == null ? 0 : crashDetailBean.x.length);
        w.a(str3, objArr);
        crashDetailBean.b = z ? 0 : XZBDevice.DOWNLOAD_LIST_RECYCLE;
        crashDetailBean.e = this.d.g();
        crashDetailBean.f = this.d.i;
        crashDetailBean.g = this.d.v();
        crashDetailBean.m = this.d.f();
        if (th == null) {
            return null;
        }
        Object a;
        Throwable th2 = th;
        while (th2.getCause() != null) {
            th2 = th2.getCause();
        }
        String name = th.getClass().getName();
        str3 = b(th, IHost.HOST_NOFITY_REFRESH_LIST);
        if (str3 == null) {
            str3 = com.umeng.a.d;
        }
        String str4 = "stack frame :%d, has cause %b";
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        w.e(str4, objArr2);
        String str5 = com.umeng.a.d;
        if (th.getStackTrace().length > 0) {
            str5 = th.getStackTrace()[0].toString();
        }
        if (th2 != th) {
            crashDetailBean.n = th2.getClass().getName();
            crashDetailBean.o = b(th2, IHost.HOST_NOFITY_REFRESH_LIST);
            if (crashDetailBean.o == null) {
                crashDetailBean.o = com.umeng.a.d;
            }
            crashDetailBean.p = th2.getStackTrace()[0].toString();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name).append(":").append(str3).append("\n");
            stringBuilder.append(str5);
            stringBuilder.append("\n......");
            stringBuilder.append("\nCaused by:\n");
            stringBuilder.append(crashDetailBean.n).append(":").append(crashDetailBean.o).append("\n");
            a = a(th2, c.e);
            stringBuilder.append(a);
            crashDetailBean.q = stringBuilder.toString();
        } else {
            crashDetailBean.n = name;
            crashDetailBean.o = str3 + str2;
            if (crashDetailBean.o == null) {
                crashDetailBean.o = com.umeng.a.d;
            }
            crashDetailBean.p = str5;
            a = a(th, c.e);
            crashDetailBean.q = a;
        }
        crashDetailBean.r = System.currentTimeMillis();
        crashDetailBean.u = com.tencent.bugly.proguard.a.c(crashDetailBean.q.getBytes());
        try {
            crashDetailBean.y = com.tencent.bugly.proguard.a.a(c.e, false);
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = thread.getName() + SocializeConstants.OP_OPEN_PAREN + thread.getId() + SocializeConstants.OP_CLOSE_PAREN;
            crashDetailBean.y.put(crashDetailBean.A, a);
            crashDetailBean.H = this.d.x();
            crashDetailBean.h = this.d.u();
            crashDetailBean.i = this.d.G();
            crashDetailBean.L = this.d.a;
            crashDetailBean.M = this.d.n;
            crashDetailBean.O = this.d.D();
            crashDetailBean.P = this.d.E();
            crashDetailBean.Q = this.d.y();
            crashDetailBean.R = this.d.C();
        } catch (Throwable th22) {
            w.e("handle crash error %s", th22.toString());
        }
        if (z) {
            this.b.b(crashDetailBean);
        } else {
            Object obj;
            if (str == null || str.length() <= 0) {
                obj = null;
            } else {
                int i = 1;
            }
            a = (bArr == null || bArr.length <= 0) ? null : 1;
            if (obj != null) {
                crashDetailBean.N = new HashMap(1);
                crashDetailBean.N.put("UserData", str);
            }
            if (a != null) {
                crashDetailBean.S = bArr;
            }
        }
        return crashDetailBean;
    }

    private static boolean c() {
        boolean z;
        synchronized (i) {
            z = h;
            h = true;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Thread r9, java.lang.Throwable r10, boolean r11, java.lang.String r12, byte[] r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.e.a(java.lang.Thread, java.lang.Throwable, boolean, java.lang.String, byte[]):void");
        /*
        this = this;
        r7 = 1;
        r6 = 0;
        if (r11 == 0) goto L_0x0067;
    L_0x0004:
        r0 = "Java Crash Happen cause by %s(%d)";
        r1 = 2;
        r1 = new java.lang.Object[r1];
        r2 = r9.getName();
        r1[r6] = r2;
        r2 = r9.getId();
        r2 = java.lang.Long.valueOf(r2);
        r1[r7] = r2;
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = c();
        if (r0 == 0) goto L_0x003c;
    L_0x0023:
        r0 = "this class has handled this exception";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.a(r0, r1);
        r0 = f;
        if (r0 == 0) goto L_0x0054;
    L_0x002f:
        r0 = "call system handler";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.a(r0, r1);
        r0 = f;
        r0.uncaughtException(r9, r10);
    L_0x003c:
        r0 = r8.g;	 Catch:{ Throwable -> 0x0147 }
        if (r0 != 0) goto L_0x00cf;
    L_0x0040:
        r0 = "Java crash handler is disable. Just return.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0147 }
        com.tencent.bugly.proguard.w.c(r0, r1);	 Catch:{ Throwable -> 0x0147 }
        if (r11 != 0) goto L_0x0070;
    L_0x004b:
        r0 = "not to shut down return";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
    L_0x0053:
        return;
    L_0x0054:
        r0 = "current process die";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        goto L_0x003c;
    L_0x0067:
        r0 = "Java Catch Happen";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x003c;
    L_0x0070:
        r0 = r8.e;
        if (r0 == 0) goto L_0x0092;
    L_0x0074:
        r0 = r8.e;
        r0 = b(r0);
        if (r0 == 0) goto L_0x0092;
    L_0x007c:
        r0 = "sys default last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = r8.e;
        r0.uncaughtException(r9, r10);
        r0 = "sys default last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0092:
        r0 = f;
        if (r0 == 0) goto L_0x00ac;
    L_0x0096:
        r0 = "system handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = f;
        r0.uncaughtException(r9, r10);
        r0 = "system handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x00ac:
        r0 = "crashreport last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = "current process die";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        r0 = "crashreport last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x00cf:
        r0 = r8.c;	 Catch:{ Throwable -> 0x0147 }
        r0 = r0.b();	 Catch:{ Throwable -> 0x0147 }
        if (r0 != 0) goto L_0x00f4;
    L_0x00d7:
        r0 = "waiting for remote sync";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0147 }
        com.tencent.bugly.proguard.w.e(r0, r1);	 Catch:{ Throwable -> 0x0147 }
        r0 = r6;
    L_0x00e1:
        r1 = r8.c;	 Catch:{ Throwable -> 0x0147 }
        r1 = r1.b();	 Catch:{ Throwable -> 0x0147 }
        if (r1 != 0) goto L_0x00f4;
    L_0x00e9:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x0142 }
    L_0x00ee:
        r0 = r0 + 500;
        r1 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        if (r0 < r1) goto L_0x00e1;
    L_0x00f4:
        r0 = r8.c;	 Catch:{ Throwable -> 0x0147 }
        r0 = r0.b();	 Catch:{ Throwable -> 0x0147 }
        if (r0 != 0) goto L_0x0105;
    L_0x00fc:
        r0 = "no remote but still store!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0147 }
        com.tencent.bugly.proguard.w.d(r0, r1);	 Catch:{ Throwable -> 0x0147 }
    L_0x0105:
        r0 = r8.c;	 Catch:{ Throwable -> 0x0147 }
        r0 = r0.c();	 Catch:{ Throwable -> 0x0147 }
        r0 = r0.d;	 Catch:{ Throwable -> 0x0147 }
        if (r0 != 0) goto L_0x01c3;
    L_0x010f:
        r0 = r8.c;	 Catch:{ Throwable -> 0x0147 }
        r0 = r0.b();	 Catch:{ Throwable -> 0x0147 }
        if (r0 == 0) goto L_0x01c3;
    L_0x0117:
        r0 = "crash report was closed by remote , will not upload to Bugly , print local for helpful!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0147 }
        com.tencent.bugly.proguard.w.e(r0, r1);	 Catch:{ Throwable -> 0x0147 }
        if (r11 == 0) goto L_0x015d;
    L_0x0122:
        r0 = "JAVA_CRASH";
    L_0x0125:
        r1 = com.tencent.bugly.proguard.a.b();	 Catch:{ Throwable -> 0x0147 }
        r2 = r8.d;	 Catch:{ Throwable -> 0x0147 }
        r2 = r2.d;	 Catch:{ Throwable -> 0x0147 }
        r4 = com.tencent.bugly.proguard.a.a(r10);	 Catch:{ Throwable -> 0x0147 }
        r5 = 0;
        r3 = r9;
        com.tencent.bugly.crashreport.crash.b.a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0147 }
        if (r11 != 0) goto L_0x0161;
    L_0x0138:
        r0 = "not to shut down return";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0142:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Throwable -> 0x0147 }
        goto L_0x00ee;
    L_0x0147:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);	 Catch:{ all -> 0x0338 }
        if (r1 != 0) goto L_0x0151;
    L_0x014e:
        r0.printStackTrace();	 Catch:{ all -> 0x0338 }
    L_0x0151:
        if (r11 != 0) goto L_0x02d6;
    L_0x0153:
        r0 = "not to shut down return";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x015d:
        r0 = "JAVA_CATCH";
        goto L_0x0125;
    L_0x0161:
        r0 = r8.e;
        if (r0 == 0) goto L_0x0184;
    L_0x0165:
        r0 = r8.e;
        r0 = b(r0);
        if (r0 == 0) goto L_0x0184;
    L_0x016d:
        r0 = "sys default last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = r8.e;
        r0.uncaughtException(r9, r10);
        r0 = "sys default last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0184:
        r0 = f;
        if (r0 == 0) goto L_0x019f;
    L_0x0188:
        r0 = "system handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = f;
        r0.uncaughtException(r9, r10);
        r0 = "system handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x019f:
        r0 = "crashreport last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = "current process die";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        r0 = "crashreport last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x01c3:
        r5 = r8.b(r9, r10, r11, r12, r13);	 Catch:{ Throwable -> 0x0147 }
        if (r5 != 0) goto L_0x0240;
    L_0x01c9:
        r0 = "pkg crash datas fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0147 }
        com.tencent.bugly.proguard.w.e(r0, r1);	 Catch:{ Throwable -> 0x0147 }
        if (r11 != 0) goto L_0x01de;
    L_0x01d4:
        r0 = "not to shut down return";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x01de:
        r0 = r8.e;
        if (r0 == 0) goto L_0x0201;
    L_0x01e2:
        r0 = r8.e;
        r0 = b(r0);
        if (r0 == 0) goto L_0x0201;
    L_0x01ea:
        r0 = "sys default last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = r8.e;
        r0.uncaughtException(r9, r10);
        r0 = "sys default last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0201:
        r0 = f;
        if (r0 == 0) goto L_0x021c;
    L_0x0205:
        r0 = "system handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = f;
        r0.uncaughtException(r9, r10);
        r0 = "system handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x021c:
        r0 = "crashreport last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = "current process die";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        r0 = "crashreport last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0240:
        if (r11 == 0) goto L_0x0270;
    L_0x0242:
        r0 = "JAVA_CRASH";
    L_0x0245:
        r1 = com.tencent.bugly.proguard.a.b();	 Catch:{ Throwable -> 0x0147 }
        r2 = r8.d;	 Catch:{ Throwable -> 0x0147 }
        r2 = r2.d;	 Catch:{ Throwable -> 0x0147 }
        r4 = com.tencent.bugly.proguard.a.a(r10);	 Catch:{ Throwable -> 0x0147 }
        r3 = r9;
        com.tencent.bugly.crashreport.crash.b.a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0147 }
        r0 = r8.b;	 Catch:{ Throwable -> 0x0147 }
        r0 = r0.a(r5);	 Catch:{ Throwable -> 0x0147 }
        if (r0 != 0) goto L_0x0264;
    L_0x025d:
        r0 = r8.b;	 Catch:{ Throwable -> 0x0147 }
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r0.a(r5, r2, r11);	 Catch:{ Throwable -> 0x0147 }
    L_0x0264:
        if (r11 != 0) goto L_0x0274;
    L_0x0266:
        r0 = "not to shut down return";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0270:
        r0 = "JAVA_CATCH";
        goto L_0x0245;
    L_0x0274:
        r0 = r8.e;
        if (r0 == 0) goto L_0x0297;
    L_0x0278:
        r0 = r8.e;
        r0 = b(r0);
        if (r0 == 0) goto L_0x0297;
    L_0x0280:
        r0 = "sys default last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = r8.e;
        r0.uncaughtException(r9, r10);
        r0 = "sys default last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0297:
        r0 = f;
        if (r0 == 0) goto L_0x02b2;
    L_0x029b:
        r0 = "system handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = f;
        r0.uncaughtException(r9, r10);
        r0 = "system handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x02b2:
        r0 = "crashreport last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = "current process die";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        r0 = "crashreport last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x02d6:
        r0 = r8.e;
        if (r0 == 0) goto L_0x02f9;
    L_0x02da:
        r0 = r8.e;
        r0 = b(r0);
        if (r0 == 0) goto L_0x02f9;
    L_0x02e2:
        r0 = "sys default last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = r8.e;
        r0.uncaughtException(r9, r10);
        r0 = "sys default last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x02f9:
        r0 = f;
        if (r0 == 0) goto L_0x0314;
    L_0x02fd:
        r0 = "system handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = f;
        r0.uncaughtException(r9, r10);
        r0 = "system handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0314:
        r0 = "crashreport last handle start!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = "current process die";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        r0 = "crashreport last handle end!";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0338:
        r0 = move-exception;
        if (r11 != 0) goto L_0x0345;
    L_0x033b:
        r0 = "not to shut down return";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r0, r1);
        goto L_0x0053;
    L_0x0345:
        r1 = r8.e;
        if (r1 == 0) goto L_0x0367;
    L_0x0349:
        r1 = r8.e;
        r1 = b(r1);
        if (r1 == 0) goto L_0x0367;
    L_0x0351:
        r1 = "sys default last handle start!";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
        r1 = r8.e;
        r1.uncaughtException(r9, r10);
        r1 = "sys default last handle end!";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
    L_0x0366:
        throw r0;
    L_0x0367:
        r1 = f;
        if (r1 == 0) goto L_0x0381;
    L_0x036b:
        r1 = "system handle start!";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
        r1 = f;
        r1.uncaughtException(r9, r10);
        r1 = "system handle end!";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
        goto L_0x0366;
    L_0x0381:
        r1 = "crashreport last handle start!";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
        r1 = "current process die";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
        r1 = android.os.Process.myPid();
        android.os.Process.killProcess(r1);
        java.lang.System.exit(r7);
        r1 = "crashreport last handle end!";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.w.e(r1, r2);
        goto L_0x0366;
        */
    }

    private static boolean b(UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        String str = "uncaughtException";
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        for (int i = 0; i < length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && str.equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        a(thread, th, true, null, null);
    }

    public final synchronized void a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.d != this.g) {
                w.a("java changed to %b", Boolean.valueOf(strategyBean.d));
                if (strategyBean.d) {
                    a();
                } else {
                    b();
                }
            }
        }
    }

    private static String a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                for (int i2 = 0; i2 < length; i2++) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (i > 0 && stringBuilder.length() >= i) {
                        stringBuilder.append(new StringBuilder("\n[Stack over limit size :").append(i).append(" , has been cutted !]").toString());
                        return stringBuilder.toString();
                    }
                    stringBuilder.append(stackTraceElement.toString()).append("\n");
                }
            }
        } catch (Throwable th2) {
            w.e("gen stack error %s", th2.toString());
        }
        return stringBuilder.toString();
    }

    private static String b(Throwable th, int i) {
        if (th.getMessage() == null) {
            return com.umeng.a.d;
        }
        return th.getMessage().length() <= 1000 ? th.getMessage() : th.getMessage().substring(0, IHost.HOST_NOFITY_REFRESH_LIST) + "\n[Message over limit size:1000, has been cutted!]";
    }
}

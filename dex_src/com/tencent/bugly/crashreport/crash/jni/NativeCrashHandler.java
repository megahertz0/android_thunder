package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import org.android.agoo.message.MessageService;

// compiled from: BUGLY
public class NativeCrashHandler {
    private static NativeCrashHandler a;
    private static boolean l;
    private static boolean m;
    private final Context b;
    private final a c;
    private final v d;
    private NativeExceptionHandler e;
    private String f;
    private final boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private b n;

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    static {
        l = false;
        m = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"SdCardPath"})
    private NativeCrashHandler(android.content.Context r4, com.tencent.bugly.crashreport.common.info.a r5, com.tencent.bugly.crashreport.crash.b r6, com.tencent.bugly.proguard.v r7, boolean r8, java.lang.String r9) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.<init>(android.content.Context, com.tencent.bugly.crashreport.common.info.a, com.tencent.bugly.crashreport.crash.b, com.tencent.bugly.proguard.v, boolean, java.lang.String):void");
        /*
        this = this;
        r1 = 0;
        r3.<init>();
        r3.h = r1;
        r3.i = r1;
        r3.j = r1;
        r3.k = r1;
        if (r4 != 0) goto L_0x0037;
    L_0x000e:
        r0 = r4;
    L_0x000f:
        r3.b = r0;
        if (r9 == 0) goto L_0x003f;
    L_0x0013:
        r0 = r9.trim();	 Catch:{ Throwable -> 0x0041 }
        r0 = r0.length();	 Catch:{ Throwable -> 0x0041 }
        if (r0 <= 0) goto L_0x003f;
    L_0x001d:
        r0 = r1;
    L_0x001e:
        if (r0 == 0) goto L_0x002c;
    L_0x0020:
        r0 = "bugly";
        r1 = 0;
        r0 = r4.getDir(r0, r1);	 Catch:{ Throwable -> 0x0041 }
        r9 = r0.getAbsolutePath();	 Catch:{ Throwable -> 0x0041 }
    L_0x002c:
        r3.n = r6;
        r3.f = r9;
        r3.c = r5;
        r3.d = r7;
        r3.g = r8;
        return;
    L_0x0037:
        r0 = r4.getApplicationContext();
        if (r0 != 0) goto L_0x000f;
    L_0x003d:
        r0 = r4;
        goto L_0x000f;
    L_0x003f:
        r0 = 1;
        goto L_0x001e;
    L_0x0041:
        r0 = move-exception;
        r0 = com.tencent.bugly.crashreport.common.info.a.a(r4);
        r0 = r0.c;
        r1 = new java.lang.StringBuilder;
        r2 = "/data/data/";
        r1.<init>(r2);
        r0 = r1.append(r0);
        r1 = "/app_bugly";
        r0 = r0.append(r1);
        r9 = r0.toString();
        goto L_0x002c;
        */
    }

    public static synchronized NativeCrashHandler getInstance(Context context, a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, v vVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (a == null) {
                a = new NativeCrashHandler(context, aVar, bVar, vVar, z, str);
            }
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f = str;
    }

    private synchronized void a(boolean z) {
        if (this.j) {
            w.d("native already registed!", new Object[0]);
        } else {
            this.e = new a(this.b, this.c, this.n, com.tencent.bugly.crashreport.common.strategy.a.a(), this.f);
            String replace;
            if (this.i) {
                try {
                    String regist = regist(this.f, z, 1);
                    if (regist != null) {
                        w.a("Native Crash Report enable!", new Object[0]);
                        w.c("Check extra jni for Bugly NDK v%s", regist);
                        String replace2 = "2.1.1".replace(".", com.umeng.a.d);
                        String replace3 = "2.3.0".replace(".", com.umeng.a.d);
                        replace = regist.replace(".", com.umeng.a.d);
                        if (replace.length() == 2) {
                            replace = replace + MessageService.MSG_DB_READY_REPORT;
                        } else if (replace.length() == 1) {
                            replace = replace + "00";
                        }
                        try {
                            if (Integer.parseInt(replace) >= Integer.parseInt(replace2)) {
                                l = true;
                            }
                            if (Integer.parseInt(replace) >= Integer.parseInt(replace3)) {
                                m = true;
                            }
                        } catch (Throwable th) {
                        }
                        if (m) {
                            w.a("Info setting jni can be accessed.", new Object[0]);
                        } else {
                            w.d("Info setting jni can not be accessed.", new Object[0]);
                        }
                        if (l) {
                            w.a("Extra jni can be accessed.", new Object[0]);
                        } else {
                            w.d("Extra jni can not be accessed.", new Object[0]);
                        }
                        this.c.l = regist;
                        this.j = true;
                    }
                } catch (Throwable th2) {
                    w.c("load bugly so fail", new Object[0]);
                }
            } else if (this.h) {
                try {
                    replace = (String) com.tencent.bugly.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler2", null, new Class[]{String.class, String.class, Integer.TYPE, Integer.TYPE}, new Object[]{this.f, a.a().q(), Integer.valueOf(a.a().H()), Integer.valueOf(1)});
                    if (replace == null) {
                        replace = (String) com.tencent.bugly.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, new Class[]{String.class, String.class, Integer.TYPE}, new Object[]{this.f, a.a().q(), Integer.valueOf(a.a().H())});
                    }
                    if (replace != null) {
                        this.j = true;
                        a.a().l = replace;
                        com.tencent.bugly.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(true)});
                        int i = com.tencent.bugly.b.b ? 3 : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                        com.tencent.bugly.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
                    }
                } catch (Throwable th3) {
                }
            }
            this.i = false;
            this.h = false;
        }
    }

    public synchronized void startNativeMonitor() {
        boolean z = true;
        synchronized (this) {
            if (this.i || this.h) {
                a(this.g);
            } else {
                boolean z2;
                Object obj;
                String str = this.c.k;
                if (str == null || str.trim().length() <= 0) {
                    z2 = true;
                } else {
                    obj = null;
                }
                if (!z2) {
                    str = this.c.k;
                }
                str = this.c.k;
                if (str == null || str.trim().length() <= 0) {
                    z2 = true;
                } else {
                    obj = null;
                }
                String str2 = z2 ? "Bugly" : this.c.k;
                str = this.c.k;
                if (str == null || str.trim().length() <= 0) {
                    z2 = true;
                } else {
                    obj = null;
                }
                if (z2) {
                    z = false;
                }
                this.i = a(str2, z);
                if (this.i || this.h) {
                    a(this.g);
                    this.d.b(new Runnable() {
                        public final void run() {
                            if (com.tencent.bugly.proguard.a.a(NativeCrashHandler.this.b, "native_record_lock", 10000)) {
                                NativeCrashHandler.this.setNativeAppVersion(NativeCrashHandler.this.c.i);
                                NativeCrashHandler.this.setNativeUserId(NativeCrashHandler.this.c.f());
                                CrashDetailBean a = b.a(NativeCrashHandler.this.b, NativeCrashHandler.this.f, NativeCrashHandler.this.e);
                                if (a != null) {
                                    if (!NativeCrashHandler.this.n.a(a)) {
                                        NativeCrashHandler.this.n.a(a, (long) TabsImpl.SYNC_TIME_OUT, false);
                                    }
                                    b.b(NativeCrashHandler.this.f);
                                    w.a("get crash from native record!", new Object[0]);
                                }
                                NativeCrashHandler.this.a();
                                com.tencent.bugly.proguard.a.b(NativeCrashHandler.this.b, "native_record_lock");
                                return;
                            }
                            w.a("Failed to lock file for handling native crash record.", new Object[0]);
                        }
                    });
                }
            }
        }
    }

    private static boolean a(String str, boolean z) {
        try {
            w.a("[native] trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                w.a("[native] load so success: %s", str);
                return true;
            } catch (Throwable th) {
                Throwable th2 = th;
                boolean z2 = true;
            }
        } catch (Throwable th3) {
            th2 = th3;
            int i = 0;
            w.d(th2.getMessage(), new Object[0]);
            w.d("[native] Failed to load so, please check.", str);
            return z2;
        }
    }

    private synchronized void b() {
        if (this.j) {
            try {
                if (unregist() != null) {
                    w.a("Native Crash Report close!", new Object[0]);
                    this.j = false;
                } else {
                    w.c("unregist bugly so success", new Object[0]);
                    try {
                        com.tencent.bugly.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(false)});
                        this.j = false;
                        w.c("unregist rqd so success", new Object[0]);
                    } catch (Throwable th) {
                        w.c("unregist rqd so fail", new Object[0]);
                        this.i = false;
                        this.h = false;
                    }
                }
            } catch (Throwable th2) {
                w.c("unregist bugly so fail", new Object[0]);
            }
        } else {
            w.d("native already unregisted!", new Object[0]);
        }
    }

    public void testNativeCrash() {
        if (this.i) {
            testCrash();
        } else {
            w.d("libBugly.so has not been load! so fail!", new Object[0]);
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }

    protected final void a() {
        long c = com.tencent.bugly.proguard.a.c() - c.f;
        File file = new File(this.f);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "tomb_";
                String str2 = ".txt";
                int length = str.length();
                int length2 = listFiles.length;
                int i = 0;
                for (int i2 = 0; i2 < length2; i2++) {
                    File file2 = listFiles[i2];
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= c) {
                            }
                        } catch (Throwable th) {
                            w.e("tomb format error delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                w.c("clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    private synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            b();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.k;
    }

    private synchronized void c(boolean z) {
        if (this.k != z) {
            w.a("user change native %b", Boolean.valueOf(z));
            this.k = z;
        }
    }

    public void setUserOpened(boolean z) {
        c(z);
        boolean z2 = com.tencent.bugly.crashreport.common.strategy.a.a().c().d && isUserOpened();
        if (z2 != this.j) {
            w.a("native changed to %b", Boolean.valueOf(z2));
            b(z2);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.d != this.j) {
                    w.d("server native changed to %b", Boolean.valueOf(strategyBean.d));
                }
            }
            if (!(com.tencent.bugly.crashreport.common.strategy.a.a().c().d && this.k)) {
                z = false;
            }
            if (z != this.j) {
                w.a("native changed to %b", Boolean.valueOf(z));
                b(z);
            }
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if (!this.i || !l || str == null || str2 == null || str3 == null) {
            return false;
        }
        try {
            return appendNativeLog(str, str2, str3);
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if (!this.i || !l || str == null || str2 == null) {
            return false;
        }
        try {
            return putNativeKeyValue(str, str2);
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    private boolean a(int i, String str) {
        boolean z = false;
        if (!this.i || !m) {
            return false;
        }
        try {
            setNativeInfo(i, str);
            z = true;
            return true;
        } catch (UnsatisfiedLinkError e) {
            m = z;
            return z;
        } catch (Throwable th) {
            if (w.a(th)) {
                return z;
            }
            th.printStackTrace();
            return z;
        }
    }

    public boolean setNativeAppVersion(String str) {
        return a((int) XZBDevice.Stop, str);
    }

    public boolean setNativeUserId(String str) {
        return a((int) XZBDevice.Success, str);
    }
}

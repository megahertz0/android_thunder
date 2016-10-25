package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.content.Context;
import android.os.FileObserver;
import android.os.Process;
import com.alipay.sdk.util.h;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: BUGLY
public final class b {
    private AtomicInteger a;
    private long b;
    private final Context c;
    private final a d;
    private final v e;
    private final com.tencent.bugly.crashreport.common.strategy.a f;
    private final String g;
    private final com.tencent.bugly.crashreport.crash.b h;
    private FileObserver i;
    private boolean j;

    // compiled from: BUGLY
    final class AnonymousClass_1 extends FileObserver {
        AnonymousClass_1(String str, int i) {
            super(str, 8);
        }

        public final void onEvent(int i, String str) {
            if (str != null) {
                String toString = new StringBuilder("/data/anr/").append(str).toString();
                if (toString.contains("trace")) {
                    b.this.a(toString);
                    return;
                }
                w.d("not anr file %s", toString);
            }
        }
    }

    public b(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, a aVar2, v vVar, com.tencent.bugly.crashreport.crash.b bVar) {
        Context context2;
        this.a = new AtomicInteger(0);
        this.b = -1;
        this.j = true;
        if (context == null) {
            context2 = context;
        } else {
            context2 = context.getApplicationContext();
            if (context2 == null) {
                context2 = context;
            }
        }
        this.c = context2;
        this.g = context.getDir("bugly", 0).getAbsolutePath();
        this.d = aVar2;
        this.e = vVar;
        this.f = aVar;
        this.h = bVar;
    }

    private static ProcessErrorStateInfo a(Context context, long j) {
        long j2 = 10000;
        if (10000 < 0) {
            j2 = 0;
        }
        w.c("to find!", new Object[0]);
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        long j3 = j2 / 500;
        int i = 0;
        while (true) {
            w.c("waiting!", new Object[0]);
            List<ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        w.c("found!", new Object[0]);
                        return processErrorStateInfo;
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i2 = i + 1;
            if (((long) i) >= j3) {
                w.c("end!", new Object[0]);
                return null;
            }
            i = i2;
        }
    }

    private CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.h();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.f();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.j();
            crashDetailBean.E = this.d.o();
            crashDetailBean.F = this.d.n();
            crashDetailBean.G = this.d.p();
            crashDetailBean.w = com.tencent.bugly.proguard.a.a(this.c, c.d, null);
            crashDetailBean.x = x.a(true);
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.g();
            crashDetailBean.f = this.d.i;
            crashDetailBean.g = this.d.v();
            crashDetailBean.m = this.d.f();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f;
            crashDetailBean.q = aVar.g;
            crashDetailBean.N = new HashMap();
            crashDetailBean.N.put("BUGLY_CR_01", aVar.e);
            int indexOf = crashDetailBean.q.indexOf("\n");
            crashDetailBean.p = indexOf > 0 ? crashDetailBean.q.substring(0, indexOf) : "GET_FAIL";
            crashDetailBean.r = aVar.c;
            crashDetailBean.u = com.tencent.bugly.proguard.a.c(crashDetailBean.q.getBytes());
            crashDetailBean.y = aVar.b;
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = "main(1)";
            crashDetailBean.H = this.d.x();
            crashDetailBean.h = this.d.u();
            crashDetailBean.i = this.d.G();
            crashDetailBean.v = aVar.d;
            crashDetailBean.K = this.d.l;
            crashDetailBean.L = this.d.a;
            crashDetailBean.M = this.d.n;
            crashDetailBean.O = this.d.D();
            crashDetailBean.P = this.d.E();
            crashDetailBean.Q = this.d.y();
            crashDetailBean.R = this.d.C();
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    private static boolean a(String str, String str2, String str3) {
        BufferedWriter bufferedWriter;
        Throwable e;
        TraceFileHelper.a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.d == null || readTargetDumpInfo.d.size() <= 0) {
            w.e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (file.exists() && file.canWrite()) {
                BufferedWriter bufferedWriter2 = null;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                    try {
                        String[] strArr = (String[]) readTargetDumpInfo.d.get("main");
                        if (strArr != null && strArr.length >= 3) {
                            String str4 = strArr[0];
                            bufferedWriter.write(new StringBuilder("\"main\" tid=").append(strArr[2]).append(" :\n").append(str4).append("\n").append(strArr[1]).append("\n\n").toString());
                            bufferedWriter.flush();
                        }
                        for (Entry entry : readTargetDumpInfo.d.entrySet()) {
                            if (!((String) entry.getKey()).equals("main") && entry.getValue() != null && ((String[]) entry.getValue()).length >= 3) {
                                String str5 = ((String[]) entry.getValue())[0];
                                bufferedWriter.write(new StringBuilder(h.f).append((String) entry.getKey()).append("\" tid=").append(((String[]) entry.getValue())[2]).append(" :\n").append(str5).append("\n").append(((String[]) entry.getValue())[1]).append("\n\n").toString());
                                bufferedWriter.flush();
                            }
                        }
                        try {
                            bufferedWriter.close();
                        } catch (Throwable e2) {
                            if (!w.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        return true;
                    } catch (IOException e3) {
                        e2 = e3;
                        bufferedWriter2 = bufferedWriter;
                        try {
                            if (!w.a(e2)) {
                                e2.printStackTrace();
                            }
                            w.e("dump trace fail %s", e2.getClass().getName() + ":" + e2.getMessage());
                            if (bufferedWriter2 != null) {
                                try {
                                    bufferedWriter2.close();
                                } catch (Throwable e22) {
                                    if (!w.a(e22)) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            e22 = th;
                            bufferedWriter = bufferedWriter2;
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                            throw e22;
                        }
                    } catch (Throwable th2) {
                        e22 = th2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (Throwable e4) {
                                if (!w.a(e4)) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    if (w.a(e22)) {
                        e22.printStackTrace();
                    }
                    w.e("dump trace fail %s", e22.getClass().getName() + ":" + e22.getMessage());
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    return false;
                } catch (Throwable th3) {
                    e22 = th3;
                    bufferedWriter = null;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw e22;
                }
            }
            w.e("backup file create fail %s", str2);
            return false;
        } catch (Throwable e222) {
            if (!w.a(e222)) {
                e222.printStackTrace();
            }
            w.e("backup file create error! %s  %s", e222.getClass().getName() + ":" + e222.getMessage(), str2);
            return false;
        }
    }

    public final boolean a() {
        return this.a.get() != 0;
    }

    private boolean a(Context context, String str, ProcessErrorStateInfo processErrorStateInfo, long j, Map<String, String> map) {
        int i;
        this.f.c();
        if (!this.f.b()) {
            w.e("waiting for remote sync", new Object[0]);
            i = 0;
            while (!this.f.b()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i += 500;
                if (i >= 3000) {
                    break;
                }
            }
        }
        File file = new File(context.getFilesDir(), new StringBuilder("bugly/bugly_trace_").append(j).append(".txt").toString());
        a aVar = new a();
        aVar.c = j;
        aVar.d = file.getAbsolutePath();
        aVar.a = processErrorStateInfo.processName;
        aVar.f = processErrorStateInfo.shortMsg;
        aVar.e = processErrorStateInfo.longMsg;
        aVar.b = map;
        if (map != null) {
            for (String str2 : map.keySet()) {
                if (str2.startsWith("main(")) {
                    aVar.g = (String) map.get(str2);
                }
            }
        }
        String str3 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
        Object[] objArr = new Object[6];
        objArr[0] = Long.valueOf(aVar.c);
        objArr[1] = aVar.d;
        objArr[2] = aVar.a;
        objArr[3] = aVar.f;
        objArr[4] = aVar.e;
        if (aVar.b == null) {
            i = 0;
        } else {
            i = aVar.b.size();
        }
        objArr[5] = Integer.valueOf(i);
        w.c(str3, objArr);
        if (!this.f.b()) {
            w.e("crash report sync remote fail, will not upload to Bugly , print local for helpful!", new Object[0]);
            com.tencent.bugly.crashreport.crash.b.a("ANR", com.tencent.bugly.proguard.a.b(), aVar.a, null, aVar.e, null);
            return false;
        } else if (this.f.c().g) {
            w.a("found visiable anr , start to upload!", new Object[0]);
            CrashDetailBean a = a(aVar);
            if (a == null) {
                w.e("pack anr fail!", new Object[0]);
                return false;
            }
            c.a().a(a);
            if (a.a >= 0) {
                w.a("backup anr record success!", new Object[0]);
            } else {
                w.d("backup anr record fail!", new Object[0]);
            }
            if (str != null && new File(str).exists()) {
                this.a.set(XZBDevice.DOWNLOAD_LIST_FAILED);
                if (a(str, aVar.d, aVar.a)) {
                    w.a("backup trace success", new Object[0]);
                }
            }
            com.tencent.bugly.crashreport.crash.b.a("ANR", com.tencent.bugly.proguard.a.b(), aVar.a, null, aVar.e, a);
            if (!this.h.a(a)) {
                this.h.a(a, (long) TabsImpl.SYNC_TIME_OUT, true);
            }
            this.h.b(a);
            return true;
        } else {
            w.d("ANR Report is closed!", new Object[0]);
            return false;
        }
    }

    public final void a(String str) {
        synchronized (this) {
            if (this.a.get() != 0) {
                w.c("trace started return ", new Object[0]);
                return;
            }
            this.a.set(1);
            try {
                long j;
                w.c("read trace first dump for create time!", new Object[0]);
                TraceFileHelper.a readFirstDumpInfo = TraceFileHelper.readFirstDumpInfo(str, false);
                if (readFirstDumpInfo != null) {
                    j = readFirstDumpInfo.c;
                } else {
                    j = -1;
                }
                if (j == -1) {
                    w.d("trace dump fail could not get time!", new Object[0]);
                    j = System.currentTimeMillis();
                }
                if (Math.abs(j - this.b) < 10000) {
                    w.d("should not process ANR too Fre in %d", Integer.valueOf(10000));
                    this.a.set(0);
                    return;
                }
                this.b = j;
                this.a.set(1);
                try {
                    Map a = com.tencent.bugly.proguard.a.a(c.e, false);
                    if (a != null) {
                        if (a.size() > 0) {
                            ProcessErrorStateInfo a2 = a(this.c, 10000);
                            if (a2 == null) {
                                w.c("proc state is unvisiable!", new Object[0]);
                                this.a.set(0);
                                return;
                            } else if (a2.pid != Process.myPid()) {
                                w.c("not mind proc!", a2.processName);
                                this.a.set(0);
                                return;
                            } else {
                                w.a("found visiable anr , start to process!", new Object[0]);
                                a(this.c, str, a2, j, a);
                                this.a.set(0);
                                return;
                            }
                        }
                    }
                    w.d("can't get all thread skip this anr", new Object[0]);
                    this.a.set(0);
                } catch (Throwable th) {
                    w.a(th);
                    w.e("get all thread stack fail!", new Object[0]);
                    this.a.set(0);
                }
            } catch (Throwable th2) {
                if (!w.a(th2)) {
                    th2.printStackTrace();
                }
                w.e("handle anr error %s", th2.getClass().toString());
                this.a.set(0);
            }
        }
    }

    private synchronized void c() {
        if (e()) {
            w.d("start when started!", new Object[0]);
        } else {
            this.i = new AnonymousClass_1("/data/anr/", 8);
            try {
                this.i.startWatching();
                w.a("start anr monitor!", new Object[0]);
                this.e.b(new Runnable() {
                    public final void run() {
                        b.this.b();
                    }
                });
            } catch (Throwable th) {
                this.i = null;
                w.d("start anr monitor failed!", new Object[0]);
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private synchronized void d() {
        if (e()) {
            try {
                this.i.stopWatching();
                this.i = null;
                w.d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                w.d("stop anr monitor failed!", new Object[0]);
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        } else {
            w.d("close when closed!", new Object[0]);
        }
    }

    private synchronized boolean e() {
        return this.i != null;
    }

    private synchronized void b(boolean z) {
        if (z) {
            c();
        } else {
            d();
        }
    }

    private synchronized boolean f() {
        return this.j;
    }

    private synchronized void c(boolean z) {
        if (this.j != z) {
            w.a("user change anr %b", Boolean.valueOf(z));
            this.j = z;
        }
    }

    public final void a(boolean z) {
        c(z);
        boolean z2 = com.tencent.bugly.crashreport.common.strategy.a.a().c().g && f();
        if (z2 != e()) {
            w.a("anr changed to %b", Boolean.valueOf(z2));
            b(z2);
        }
    }

    protected final void b() {
        long c = com.tencent.bugly.proguard.a.c() - c.f;
        File file = new File(this.g);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "bugly_trace_";
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

    public final synchronized void a(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.g != e()) {
                    w.d("server anr changed to %b", Boolean.valueOf(strategyBean.g));
                }
            }
            if (!(strategyBean.g && f())) {
                z = false;
            }
            if (z != e()) {
                w.a("anr changed to %b", Boolean.valueOf(z));
                b(z);
            }
        }
    }
}

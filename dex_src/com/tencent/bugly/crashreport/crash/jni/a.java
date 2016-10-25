package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.uc.addon.sdk.remote.TabsImpl;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

// compiled from: BUGLY
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;
    private final String e;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, String str) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
        this.e = str;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, byte[] bArr, Map<String, String> map, boolean z) {
        boolean n = c.a().n();
        String str10 = n ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : com.umeng.a.d;
        if (n) {
            w.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.g();
        crashDetailBean.f = this.c.i;
        crashDetailBean.g = this.c.v();
        crashDetailBean.m = this.c.f();
        crashDetailBean.n = str3;
        crashDetailBean.o = str10;
        crashDetailBean.p = str4;
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = com.tencent.bugly.proguard.a.c(crashDetailBean.q.getBytes());
        crashDetailBean.z = str;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.x();
        crashDetailBean.h = this.c.u();
        crashDetailBean.i = this.c.G();
        crashDetailBean.v = str8;
        str10 = b.a(this.e, str8);
        if (str10 != null) {
            crashDetailBean.T = str10;
        }
        File file = new File(this.e, "backup_record.txt");
        crashDetailBean.U = file.exists() ? file.getAbsolutePath() : null;
        crashDetailBean.I = str7;
        crashDetailBean.J = str6;
        crashDetailBean.K = str9;
        crashDetailBean.E = this.c.o();
        crashDetailBean.F = this.c.n();
        crashDetailBean.G = this.c.p();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.h();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.f();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.j();
            crashDetailBean.w = com.tencent.bugly.proguard.a.a(this.a, c.d, null);
            crashDetailBean.x = x.a(true);
            crashDetailBean.L = this.c.a;
            crashDetailBean.M = this.c.n;
            crashDetailBean.O = this.c.D();
            crashDetailBean.P = this.c.E();
            crashDetailBean.Q = this.c.y();
            crashDetailBean.R = this.c.C();
            crashDetailBean.y = com.tencent.bugly.proguard.a.a(c.e, false);
            str10 = "java:\n";
            if (crashDetailBean.q != null) {
                int indexOf = crashDetailBean.q.indexOf(str10);
                if (indexOf > 0) {
                    indexOf += str10.length();
                    String substring = crashDetailBean.q.substring(indexOf, crashDetailBean.q.length() - 1);
                    if (substring.length() > 0 && crashDetailBean.y.containsKey(crashDetailBean.A)) {
                        str10 = (String) crashDetailBean.y.get(crashDetailBean.A);
                        int indexOf2 = str10.indexOf(substring);
                        if (indexOf2 > 0) {
                            str10 = str10.substring(indexOf2);
                            crashDetailBean.y.put(crashDetailBean.A, str10);
                            crashDetailBean.q = crashDetailBean.q.substring(0, indexOf);
                            crashDetailBean.q += str10;
                        }
                    }
                }
            }
            if (str == null) {
                crashDetailBean.z = this.c.d;
            }
            this.b.b(crashDetailBean);
        } else {
            crashDetailBean.B = -1;
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            crashDetailBean.L = -1;
            crashDetailBean.O = -1;
            crashDetailBean.P = -1;
            crashDetailBean.Q = map;
            crashDetailBean.R = null;
            crashDetailBean.y = null;
            if (str == null) {
                crashDetailBean.z = "unknown(record)";
            }
            if (bArr == null) {
                crashDetailBean.x = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.".getBytes();
            } else {
                crashDetailBean.x = bArr;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        w.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        w.a("Native Crash Happen v2", new Object[0]);
        try {
            int i7;
            String str8;
            String str9;
            this.d.c();
            if (!this.d.b()) {
                w.e("waiting for remote sync", new Object[0]);
                i7 = 0;
                while (!this.d.b()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i7 += 500;
                    if (i7 >= 3000) {
                        break;
                    }
                }
            }
            long j3 = (1000 * j) + (j2 / 1000);
            String a = b.a(str3);
            String toString = new StringBuilder("UNKNOWN(").append(i4).append(SocializeConstants.OP_CLOSE_PAREN).toString();
            if (i3 > 0) {
                str8 = "KERNEL";
                str9 = str + SocializeConstants.OP_OPEN_PAREN + str5 + SocializeConstants.OP_CLOSE_PAREN;
            } else if (i4 > 0) {
                toString = AppInfo.a(i4);
                str8 = str5;
                str9 = str;
            } else {
                str8 = str5;
                str9 = str;
            }
            if (!this.d.b()) {
                w.d("no remote but still store!", new Object[0]);
            }
            if (this.d.c().d || !this.d.b()) {
                String str10 = null;
                String str11 = null;
                if (strArr != null) {
                    Map hashMap = new HashMap();
                    for (String str112 : strArr) {
                        String[] split = str112.split("=");
                        if (split.length == 2) {
                            hashMap.put(split[0], split[1]);
                        } else {
                            w.d("bad extraMsg %s", str112);
                        }
                    }
                    str112 = (String) hashMap.get("ExceptionThreadName");
                    str10 = (String) hashMap.get("ExceptionProcessName");
                } else {
                    w.c("not found extraMsg", new Object[0]);
                }
                if (str10 == null || str10.length() == 0) {
                    str10 = this.c.d;
                } else {
                    w.c("crash process name change to %s", str10);
                }
                Thread currentThread;
                if (str112 != null && str112.length() != 0) {
                    w.c("crash thread name change to %s", str112);
                    for (Thread currentThread2 : Thread.getAllStackTraces().keySet()) {
                        if (currentThread2.getName().equals(str112)) {
                            str112 = str112 + SocializeConstants.OP_OPEN_PAREN + currentThread2.getId() + SocializeConstants.OP_CLOSE_PAREN;
                            break;
                        }
                    }
                }
                currentThread2 = Thread.currentThread();
                str112 = currentThread2.getName() + SocializeConstants.OP_OPEN_PAREN + currentThread2.getId() + SocializeConstants.OP_CLOSE_PAREN;
                CrashDetailBean packageCrashDatas = packageCrashDatas(str10, str112, j3, str9, str2, a, str8, toString, str4, str7, null, null, true);
                if (packageCrashDatas == null) {
                    w.e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                b.a("NATIVE_CRASH", com.tencent.bugly.proguard.a.b(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + a, packageCrashDatas);
                if (!this.b.a(packageCrashDatas, i3)) {
                    this.b.a(packageCrashDatas, (long) TabsImpl.SYNC_TIME_OUT, true);
                }
                b.b(this.e);
                return;
            }
            w.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            b.a("NATIVE_CRASH", com.tencent.bugly.proguard.a.b(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + a, null);
            if (str4 != null) {
                File file = new File(str4);
                if (file.isFile() && file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }
}

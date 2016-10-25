package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import android.os.Parcel;
import com.tencent.bugly.crashreport.biz.b;
import com.tencent.bugly.proguard.av;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;
import org.android.agoo.message.MessageService;

// compiled from: BUGLY
public final class a {
    public static int a;
    private static a b;
    private final List<com.tencent.bugly.a> c;
    private final v d;
    private final StrategyBean e;
    private StrategyBean f;
    private Context g;

    static {
        a = 1000;
        b = null;
    }

    private a(Context context, List<com.tencent.bugly.a> list) {
        this.f = null;
        this.g = context;
        this.e = new StrategyBean();
        this.c = list;
        this.d = v.a();
        this.d.b(new Thread() {
            public final void run() {
                try {
                    Map a = o.a().a(a, null, true);
                    if (a != null) {
                        byte[] bArr = (byte[]) a.get("key_imei");
                        byte[] bArr2 = (byte[]) a.get("key_ip");
                        if (bArr != null) {
                            com.tencent.bugly.crashreport.common.info.a.a(a.this.g).e(new String(bArr));
                        }
                        if (bArr2 != null) {
                            com.tencent.bugly.crashreport.common.info.a.a(a.this.g).d(new String(bArr2));
                        }
                    }
                    a.this.f = a.d();
                    a.this.a(a.this.f);
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        });
    }

    public static synchronized a a(Context context, List<com.tencent.bugly.a> list) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context, list);
            }
            aVar = b;
        }
        return aVar;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            aVar = b;
        }
        return aVar;
    }

    public final synchronized boolean b() {
        return this.f != null;
    }

    public final StrategyBean c() {
        return this.f != null ? this.f : this.e;
    }

    protected final void a(StrategyBean strategyBean) {
        for (com.tencent.bugly.a aVar : this.c) {
            try {
                w.c("[strategy] Notify %s", aVar.getClass().getName());
                aVar.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        b.a(strategyBean);
    }

    public final void a(av avVar) {
        if (avVar != null) {
            if (this.f == null || avVar.h != this.f.m) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.d = avVar.a;
                strategyBean.f = avVar.c;
                strategyBean.e = avVar.b;
                String str = avVar.d;
                boolean z = str == null || str.trim().length() <= 0;
                if (!z) {
                    w.c("upload url changes to %s", avVar.d);
                    strategyBean.o = avVar.d;
                }
                str = avVar.e;
                if (str == null || str.trim().length() <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    w.c("exception upload url changes to %s", avVar.e);
                    strategyBean.p = avVar.e;
                }
                if (avVar.f != null) {
                    str = avVar.f.a;
                    if (str == null || str.trim().length() <= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        strategyBean.r = avVar.f.a;
                    }
                }
                if (avVar.h != 0) {
                    strategyBean.m = avVar.h;
                }
                if (avVar.g != null && avVar.g.size() > 0) {
                    strategyBean.s = avVar.g;
                    str = (String) avVar.g.get("B11");
                    if (str == null || !str.equals(MessageService.MSG_DB_NOTIFY_REACHED)) {
                        strategyBean.g = false;
                    } else {
                        strategyBean.g = true;
                    }
                    str = (String) avVar.g.get("B3");
                    if (str != null) {
                        strategyBean.v = Long.valueOf(str).longValue();
                    }
                    strategyBean.n = (long) avVar.i;
                    strategyBean.u = (long) avVar.i;
                    str = (String) avVar.g.get("B27");
                    if (str != null && str.length() > 0) {
                        try {
                            int parseInt = Integer.parseInt(str);
                            if (parseInt > 0) {
                                strategyBean.t = parseInt;
                            }
                        } catch (Throwable e) {
                            if (!w.a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    str = (String) avVar.g.get("B25");
                    if (str == null || !str.equals(MessageService.MSG_DB_NOTIFY_REACHED)) {
                        strategyBean.i = false;
                    } else {
                        strategyBean.i = true;
                    }
                }
                w.a("cr:%b,qu:%b,uin:%b,an:%b,bl:%b,ss:%b,ssT:%b,ssOT:%d,cos:%b,lstT:%d", Boolean.valueOf(strategyBean.d), Boolean.valueOf(strategyBean.f), Boolean.valueOf(strategyBean.e), Boolean.valueOf(strategyBean.g), Boolean.valueOf(strategyBean.h), Boolean.valueOf(strategyBean.k), Boolean.valueOf(strategyBean.l), Long.valueOf(strategyBean.n), Boolean.valueOf(strategyBean.i), Long.valueOf(strategyBean.m));
                this.f = strategyBean;
                o.a();
                o.b((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                q qVar = new q();
                qVar.b = 2;
                qVar.a = strategyBean.b;
                qVar.e = strategyBean.c;
                Parcel obtain = Parcel.obtain();
                strategyBean.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                qVar.g = marshall;
                o.a().a(qVar);
                a(strategyBean);
            }
        }
    }

    public static StrategyBean d() {
        List a = o.a().a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        if (a != null && a.size() > 0) {
            q qVar = (q) a.get(0);
            if (qVar.g != null) {
                return (StrategyBean) com.tencent.bugly.proguard.a.a(qVar.g, StrategyBean.CREATOR);
            }
        }
        return null;
    }
}

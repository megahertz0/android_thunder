package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.crashreport.inner.InnerAPI;
import com.tencent.bugly.proguard.m;
import com.tencent.bugly.proguard.w;

// compiled from: BUGLY
public class CrashModule extends a {
    public static final int MODULE_ID = 1004;
    private static int c;
    private static boolean d;
    private static CrashModule e;
    private long a;
    private a b;

    static {
        c = 0;
        d = false;
        e = new CrashModule();
    }

    public static CrashModule getInstance() {
        e.id = 1004;
        return e;
    }

    public static boolean hasInitialized() {
        return d;
    }

    public void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null && !d) {
            m a = m.a();
            int i = c + 1;
            c = i;
            a.a(MODULE_ID, i);
            d = true;
            CrashReport.setContext(context);
            a(context, buglyStrategy);
            c.a(MODULE_ID, context, z, this.b, null, null);
            c a2 = c.a();
            a2.e();
            if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                a2.g();
            } else {
                w.a("[crash] Closed native crash monitor!", new Object[0]);
                a2.f();
            }
            if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                a2.h();
            } else {
                w.a("[crash] Closed ANR monitor!", new Object[0]);
                a2.i();
            }
            if (buglyStrategy == null || !buglyStrategy.isEnableBlockCrashMonitor()) {
                a2.j();
            } else {
                a2.a(buglyStrategy == null ? -1 : buglyStrategy.getBlockThresholdTime());
            }
            InnerAPI.context = context;
            d.a(context);
            c.a().b(this.a);
            a = m.a();
            i = c - 1;
            c = i;
            a.a(MODULE_ID, i);
        }
    }

    private synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy != null) {
            Object libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty(libBuglySOFilePath)) {
                com.tencent.bugly.crashreport.common.info.a.a(context).k = libBuglySOFilePath;
                w.a("setted libBugly.so file path :%s", libBuglySOFilePath);
            }
            if (buglyStrategy.getCrashHandleCallback() != null) {
                this.b = buglyStrategy.getCrashHandleCallback();
                w.a("setted CrashHanldeCallback", new Object[0]);
            }
            if (buglyStrategy.getAppReportDelay() > 0) {
                this.a = buglyStrategy.getAppReportDelay();
                w.a("setted delay: %d", Long.valueOf(this.a));
            }
        }
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            c a = c.a();
            if (a != null) {
                a.a(strategyBean);
            }
            d.a(strategyBean);
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}

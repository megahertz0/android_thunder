package com.xunlei.downloadprovider.app.a;

import android.content.Context;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;
import com.umeng.analytics.MobclickAgent;
import com.xunlei.downloadprovider.a.b;

// compiled from: ThunderCrash.java
public final class d implements Runnable {
    final /* synthetic */ Context a;

    public d(Context context) {
        this.a = context;
    }

    public final void run() {
        boolean z;
        Context context = this.a;
        String g = b.g(context);
        if (g == null || !g.startsWith("5.1.")) {
            z = false;
        } else {
            z = true;
        }
        UserStrategy userStrategy = new UserStrategy(context);
        try {
            userStrategy.setAppChannel(b.d(context));
            userStrategy.setCrashHandleCallback(new b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CrashReport.initCrashReport(context, "900015673", z, userStrategy);
        MobclickAgent.setCatchUncaughtExceptions(false);
    }
}

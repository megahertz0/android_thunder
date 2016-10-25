package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.k;

final class l implements Runnable {
    final /* synthetic */ Context a;

    l(Context context) {
        this.a = context;
    }

    public final void run() {
        a.a(StatServiceImpl.t).h();
        k.a(this.a, true);
        au.a(this.a);
        i.b(this.a);
        StatServiceImpl.r = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new ao());
        if (StatConfig.getStatSendStrategy() == StatReportStrategy.APP_LAUNCH) {
            StatServiceImpl.commitEvents(this.a, -1);
        }
        if (StatConfig.isDebugEnable()) {
            StatServiceImpl.q.d("Init MTA StatService success.");
        }
    }
}

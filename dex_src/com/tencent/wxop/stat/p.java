package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.d;

final class p implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    p(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.a = str;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            if (StatServiceImpl.a(this.a)) {
                StatServiceImpl.q.error((Object) "Error message in StatService.reportError() is empty.");
            } else {
                new aq(new d(this.b, StatServiceImpl.a(this.b, false, this.c), this.a, 0, StatConfig.getMaxReportEventLength(), null, this.c)).a();
            }
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.b, th);
        }
    }
}

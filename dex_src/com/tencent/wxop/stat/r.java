package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.d;

final class r implements Runnable {
    final /* synthetic */ Throwable a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    r(Throwable th, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.a = th;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.a == null) {
            StatServiceImpl.q.error((Object) "The Throwable error message of StatService.reportException() can not be null!");
        } else {
            new aq(new d(this.b, StatServiceImpl.a(this.b, false, this.c), 1, this.a, this.c)).a();
        }
    }
}

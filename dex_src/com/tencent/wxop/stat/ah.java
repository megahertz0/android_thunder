package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.e;
import com.tencent.wxop.stat.a.k;
import com.umeng.socialize.common.SocializeConstants;

final class ah implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    ah(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.a = context;
        this.b = str;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            StatServiceImpl.flushDataToDB(this.a);
            synchronized (StatServiceImpl.o) {
                Long l = (Long) StatServiceImpl.o.remove(this.b);
            }
            if (l != null) {
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                if (valueOf.longValue() <= 0) {
                    valueOf = Long.valueOf(1);
                }
                String j = StatServiceImpl.n;
                if (j != null && j.equals(this.b)) {
                    j = SocializeConstants.OP_DIVIDER_MINUS;
                }
                e kVar = new k(this.a, j, this.b, StatServiceImpl.a(this.a, false, this.c), valueOf, this.c);
                if (!this.b.equals(StatServiceImpl.m)) {
                    StatServiceImpl.q.warn("Invalid invocation since previous onResume on diff page.");
                }
                new aq(kVar).a();
                StatServiceImpl.n = this.b;
                return;
            }
            StatServiceImpl.q.e(new StringBuilder("Starttime for PageID:").append(this.b).append(" not found, lost onResume()?").toString());
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.a, th);
        }
    }
}

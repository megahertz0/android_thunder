package com.xunlei.analytics.a;

import android.os.Handler.Callback;
import android.os.Message;
import com.xunlei.analytics.HubbleAgent;
import com.xunlei.analytics.c.g;
import com.xunlei.analytics.config.a;
import java.util.List;

class b implements Callback {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        if (this.a.b(HubbleAgent.getReportConfiguration().reportRule)) {
            int a = com.xunlei.analytics.dbstore.b.a().a(System.currentTimeMillis() - HubbleAgent.getReportConfiguration().deleteExpirationDayTime) + g.a(a.d());
            List a2 = com.xunlei.analytics.dbstore.b.a().a(HubbleAgent.getReportConfiguration().batchUploadCount);
            if (!(a2 == null || a2.size() == 0)) {
                this.a.a(a);
                if (c.a(a2, a)) {
                    com.xunlei.analytics.dbstore.b.a().b(a2);
                    g.b(a.d());
                    if (a > 0) {
                        g.a(a.d(), 0);
                    }
                    this.a.f = 0;
                } else {
                    a.a(this.a);
                    g.a(a.d(), a);
                }
                this.a.a(true);
            }
        }
        return true;
    }
}

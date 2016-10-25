package com.xunlei.downloadprovider.ad.common;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.ad.common.d.a;
import java.util.Iterator;

// compiled from: RequestTimeoutController.java
final class e extends Handler {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final void handleMessage(Message message) {
        if (this.a.c != null && !this.a.d) {
            d dVar = this.a;
            dVar.b--;
            if (this.a.b <= 0) {
                Iterator it = this.a.c.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    this.a.e = true;
                    aVar.a();
                }
                return;
            }
            this.a.f.sendEmptyMessageDelayed(-1, 1000);
        }
    }
}

package com.xunlei.downloadprovider.frame.advertisement.b;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.frame.advertisement.b.d.a;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: ThunderADManager.java
final class e extends Handler {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final void handleMessage(Message message) {
        synchronized (d.a()) {
            String str = d.a;
            Object obj = message.obj;
            if (obj != null && (obj instanceof a)) {
                a aVar = (a) obj;
                aVar.c = true;
                String str2 = d.a;
                new StringBuilder("get delay listener.isTimeoutCancel: ").append(aVar.d);
                if (!aVar.d) {
                    aVar.a(-400, BuildConfig.VERSION_NAME);
                }
            }
        }
    }
}

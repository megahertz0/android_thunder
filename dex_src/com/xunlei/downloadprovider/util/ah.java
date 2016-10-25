package com.xunlei.downloadprovider.util;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.util.ag.b;

// compiled from: XLBroadcast.java
final class ah implements a {
    ah() {
    }

    public final void a(Message message) {
        switch (message.what) {
            case 5001:
                if (message.obj != null && (message.obj instanceof b)) {
                    b bVar = (b) message.obj;
                    ag.a().add(bVar);
                    if (ag.b() != null) {
                        bVar.b(ag.b());
                    }
                }
            case 5002:
                if (message.obj != null && (message.obj instanceof b)) {
                    ag.a().remove((b) message.obj);
                }
            default:
                break;
        }
    }
}

package com.xunlei.downloadprovider.a;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import java.lang.ref.WeakReference;

// compiled from: HandlerUtil.java
public final class h {
    private static int a;

    // compiled from: HandlerUtil.java
    public static interface a {
        void a(Message message);
    }

    // compiled from: HandlerUtil.java
    public static class b extends Handler {
        WeakReference<a> a;

        public b(a aVar) {
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            if (this.a != null) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.a(message);
                }
            }
        }
    }

    static {
        a = 16777216;
    }

    public static final int a() {
        int i = a + 1;
        a = i;
        return i;
    }
}

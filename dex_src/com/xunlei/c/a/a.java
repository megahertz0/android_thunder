package com.xunlei.c.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

public final class a {
    private static int a;

    public static interface a {
        void a(Message message);
    }

    public static class b extends Handler {
        WeakReference<com.xunlei.c.a.a.a> a;

        public b(com.xunlei.c.a.a.a aVar) {
            this.a = new WeakReference(aVar);
        }

        public b(Looper looper, com.xunlei.c.a.a.a aVar) {
            super(looper);
            this.a = new WeakReference(aVar);
        }

        public final void handleMessage(Message message) {
            com.xunlei.c.a.a.a aVar = (com.xunlei.c.a.a.a) this.a.get();
            if (aVar != null) {
                aVar.a(message);
            }
        }
    }

    static {
        a = 16777216;
    }
}

package com.google.zxing.client.a;

import android.os.Handler;
import android.os.Looper;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

// compiled from: ScanCodeDecodeThread.java
final class f extends Thread {
    private Object a;
    private Handler b;
    private final CountDownLatch c;

    f(Object obj) {
        this.a = obj;
        this.c = new CountDownLatch(1);
        Vector vector = new Vector();
        vector.addAll(a.b);
        vector.addAll(a.c);
        vector.addAll(a.d);
    }

    final Handler a() {
        try {
            this.c.await();
        } catch (InterruptedException e) {
        }
        return this.b;
    }

    public final void run() {
        Looper.prepare();
        this.b = new e(this.a);
        this.c.countDown();
        Looper.loop();
    }
}

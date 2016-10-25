package com.xiaomi.channel.commonutils.misc;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.channel.commonutils.misc.f.b;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class f {
    private a a;
    private Handler b;
    private volatile boolean c;
    private final boolean d;
    private int e;
    private volatile b f;

    private class a extends Thread {
        private final LinkedBlockingQueue<b> b;

        public a() {
            super("PackageProcessor");
            this.b = new LinkedBlockingQueue();
        }

        public void a(b bVar) {
            this.b.add(bVar);
        }

        public void run() {
            int a = f.this.e > 0 ? f.this.e : 1;
            while (!f.this.c) {
                try {
                    f.this.f = (b) this.b.poll((long) a, TimeUnit.SECONDS);
                    if (f.this.f != null) {
                        f.this.b.sendMessage(f.this.b.obtainMessage(0, f.this.f));
                        f.this.f.b();
                        f.this.b.sendMessage(f.this.b.obtainMessage(1, f.this.f));
                    } else if (f.this.e > 0) {
                        f.this.a();
                    }
                } catch (Throwable e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                }
            }
        }
    }

    public static abstract class b {
        public void a() {
        }

        public abstract void b();

        public void c() {
        }
    }

    public f() {
        this(false);
    }

    public f(boolean z) {
        this(z, 0);
    }

    public f(boolean z, int i) {
        this.b = null;
        this.c = false;
        this.e = 0;
        this.b = new g(this, Looper.getMainLooper());
        this.d = z;
        this.e = i;
    }

    private synchronized void a() {
        this.a = null;
        this.c = true;
    }

    public synchronized void a(b bVar) {
        if (this.a == null) {
            this.a = new a();
            this.a.setDaemon(this.d);
            this.c = false;
            this.a.start();
        }
        this.a.a(bVar);
    }

    public void a(b bVar, long j) {
        this.b.postDelayed(new h(this, bVar), j);
    }
}

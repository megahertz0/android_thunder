package com.xiaomi.push.service;

import android.os.SystemClock;
import com.xiaomi.push.service.e.b;
import java.util.concurrent.RejectedExecutionException;

public class e {
    private static long a;
    private final c b;
    private final a c;

    public static abstract class b implements Runnable {
        protected int a;

        b(int i) {
            this.a = i;
        }
    }

    private static final class a {
        private final c a;

        a(c cVar) {
            this.a = cVar;
        }

        protected final void finalize() {
            try {
                synchronized (this.a) {
                    this.a.e = true;
                    this.a.notify();
                }
                super.finalize();
            } catch (Throwable th) {
            }
        }
    }

    private static final class c extends Thread {
        private volatile long a;
        private volatile boolean b;
        private long c;
        private boolean d;
        private boolean e;
        private a f;

        private static final class a {
            private int a;
            private d[] b;
            private int c;
            private int d;

            private a() {
                this.a = 256;
                this.b = new d[this.a];
                this.c = 0;
                this.d = 0;
            }

            private int b(d dVar) {
                for (int i = 0; i < this.b.length; i++) {
                    if (this.b[i] == dVar) {
                        return i;
                    }
                }
                return -1;
            }

            private void d(int i) {
                int i2 = (i * 2) + 1;
                while (i2 < this.c && this.c > 0) {
                    if (i2 + 1 < this.c && this.b[i2 + 1].c < this.b[i2].c) {
                        i2++;
                    }
                    if (this.b[i].c >= this.b[i2].c) {
                        d dVar = this.b[i];
                        this.b[i] = this.b[i2];
                        this.b[i2] = dVar;
                        i = i2;
                        i2 = (i2 * 2) + 1;
                    } else {
                        return;
                    }
                }
            }

            private void e() {
                int i = this.c - 1;
                for (int i2 = (i - 1) / 2; this.b[i].c < this.b[i2].c; i2 = (i2 - 1) / 2) {
                    d dVar = this.b[i];
                    this.b[i] = this.b[i2];
                    this.b[i2] = dVar;
                    i = i2;
                }
            }

            public final d a() {
                return this.b[0];
            }

            public final void a(int i, b bVar) {
                for (int i2 = 0; i2 < this.c; i2++) {
                    if (this.b[i2].d == bVar) {
                        this.b[i2].a();
                    }
                }
                d();
            }

            public final void a(d dVar) {
                if (this.b.length == this.c) {
                    Object obj = new Object[(this.c * 2)];
                    System.arraycopy(this.b, 0, obj, 0, this.c);
                    this.b = obj;
                }
                d[] dVarArr = this.b;
                int i = this.c;
                this.c = i + 1;
                dVarArr[i] = dVar;
                e();
            }

            public final boolean a(int i) {
                for (int i2 = 0; i2 < this.c; i2++) {
                    if (this.b[i2].e == i) {
                        return true;
                    }
                }
                return false;
            }

            public final void b(int i) {
                for (int i2 = 0; i2 < this.c; i2++) {
                    if (this.b[i2].e == i) {
                        this.b[i2].a();
                    }
                }
                d();
            }

            public final boolean b() {
                return this.c == 0;
            }

            public final void c() {
                this.b = new d[this.a];
                this.c = 0;
            }

            public final void c(int i) {
                if (i >= 0 && i < this.c) {
                    d[] dVarArr = this.b;
                    d[] dVarArr2 = this.b;
                    int i2 = this.c - 1;
                    this.c = i2;
                    dVarArr[i] = dVarArr2[i2];
                    this.b[this.c] = null;
                    d(i);
                }
            }

            public final void d() {
                int i = 0;
                while (i < this.c) {
                    if (this.b[i].b) {
                        this.d++;
                        c(i);
                        i--;
                    }
                    i++;
                }
            }
        }

        c(String str, boolean z) {
            this.a = 0;
            this.b = false;
            this.c = 50;
            this.f = new a();
            setName(str);
            setDaemon(z);
            start();
        }

        private void a(d dVar) {
            this.f.a(dVar);
            notify();
        }

        public final synchronized void a() {
            this.d = true;
            this.f.c();
            notify();
        }

        public final boolean b() {
            return this.b && SystemClock.uptimeMillis() - this.a > 600000;
        }

        public final void run() {
            while (true) {
                synchronized (this) {
                    try {
                        if (this.d) {
                            return;
                        } else if (!this.f.b()) {
                            long a = e.a();
                            d a2 = this.f.a();
                            synchronized (a2.a) {
                                if (a2.b) {
                                    this.f.c(0);
                                } else {
                                    a = a2.c - a;
                                    if (a > 0) {
                                        if (a > this.c) {
                                            a = this.c;
                                        }
                                        this.c += 50;
                                        if (this.c > 500) {
                                            this.c = 500;
                                        }
                                        try {
                                            wait(a);
                                        } catch (InterruptedException e) {
                                        }
                                    } else {
                                        this.c = 50;
                                        synchronized (a2.a) {
                                            int i = 0;
                                            if (this.f.a().c != a2.c) {
                                                i = this.f.b(a2);
                                            }
                                            if (a2.b) {
                                                this.f.c(this.f.b(a2));
                                            } else {
                                                a2.a(a2.c);
                                                this.f.c(i);
                                                a2.c = 0;
                                                try {
                                                    this.a = SystemClock.uptimeMillis();
                                                    this.b = true;
                                                    a2.d.run();
                                                    this.b = false;
                                                } catch (Throwable th) {
                                                    synchronized (this) {
                                                    }
                                                    this.d = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (this.e) {
                            return;
                        } else {
                            try {
                                wait();
                            } catch (InterruptedException e2) {
                            }
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
        }
    }

    static class d {
        final Object a;
        boolean b;
        long c;
        b d;
        int e;
        private long f;

        d() {
            this.a = new Object();
        }

        void a(long j) {
            synchronized (this.a) {
                this.f = j;
            }
        }

        public boolean a() {
            boolean z = true;
            synchronized (this.a) {
                if (this.b || this.c <= 0) {
                    z = false;
                }
                this.b = true;
            }
            return z;
        }
    }

    public e() {
        this(false);
    }

    public e(String str) {
        this(str, false);
    }

    public e(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        this.b = new c(str, z);
        this.c = new a(this.b);
    }

    public e(boolean z) {
        this(new StringBuilder("Timer-").append(e()).toString(), z);
    }

    static long a() {
        return SystemClock.elapsedRealtime();
    }

    private void b(b bVar, long j) {
        synchronized (this.b) {
            if (this.b.d) {
                throw new IllegalStateException("Timer was canceled");
            }
            long a = a() + j;
            if (a < 0) {
                throw new IllegalArgumentException(new StringBuilder("Illegal delay to start the TimerTask: ").append(a).toString());
            }
            d dVar = new d();
            dVar.e = bVar.a;
            dVar.d = bVar;
            dVar.c = a;
            this.b.a(dVar);
        }
    }

    private static synchronized long e() {
        long j;
        synchronized (e.class) {
            j = a;
            a = 1 + j;
        }
        return j;
    }

    public void a(int i, b bVar) {
        synchronized (this.b) {
            this.b.f.a(i, bVar);
        }
    }

    public void a(b bVar) {
        if (com.xiaomi.channel.commonutils.logger.b.a() > 0 || Thread.currentThread() == this.b) {
            bVar.run();
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        }
    }

    public void a(b bVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException(new StringBuilder("delay < 0: ").append(j).toString());
        }
        b(bVar, j);
    }

    public boolean a(int i) {
        boolean a;
        synchronized (this.b) {
            a = this.b.f.a(i);
        }
        return a;
    }

    public void b() {
        this.b.a();
    }

    public void b(int i) {
        synchronized (this.b) {
            this.b.f.b(i);
        }
    }

    public void c() {
        synchronized (this.b) {
            this.b.f.c();
        }
    }

    public boolean d() {
        return this.b.b();
    }
}

package com.xunlei.downloadprovider.util;

import android.os.Handler;
import android.os.Message;
import com.tencent.connect.common.Constants;
import com.xunlei.downloadprovider.b.c;
import com.xunlei.downloadprovider.b.c.g;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Timer;
import org.android.spdy.SpdyAgent;

// compiled from: NetworkSpeedCheck.java
public final class k {
    String a;
    public boolean b;
    public a c;
    public int d;
    long e;
    long f;
    long g;
    long h;
    long i;
    g j;
    public b k;
    boolean l;
    Timer m;
    int n;
    List<Long> o;
    public int p;
    public int q;

    // compiled from: NetworkSpeedCheck.java
    public static interface a {
        void a();

        void a(int i, long j);

        void a(long j);

        void b();

        void c();

        void d();

        void e();
    }

    // compiled from: NetworkSpeedCheck.java
    static class b extends Handler {
        WeakReference<k> a;

        public b(k kVar) {
            this.a = new WeakReference(kVar);
        }

        public final void handleMessage(Message message) {
            k kVar = (k) this.a.get();
            if (kVar != null) {
                long j;
                switch (message.what) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (kVar.c != null) {
                            kVar.c.a(1, 0);
                        }
                        kVar.b();
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (kVar.c != null) {
                            kVar.c.e();
                        }
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        j = kVar.g - kVar.h;
                        if (kVar.c != null) {
                            kVar.c.a(j);
                        }
                        kVar.h = kVar.g;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        j = (long) ((((double) (kVar.f - kVar.e)) / 1000.0d) + 0.5d);
                        if (kVar.c != null) {
                            if (j != 0) {
                                kVar.c.a(0, kVar.g / j);
                            } else {
                                kVar.c.a(0, 0);
                            }
                        }
                        kVar.b();
                        kVar.c();
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (kVar.c != null) {
                            kVar.c.a(1, 0);
                        }
                        kVar.b();
                        kVar.c();
                    case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                        kVar.o = ((com.xunlei.downloadprovider.service.downloads.task.a) message.obj).a;
                        if (kVar.c != null) {
                            kVar.c.b();
                        }
                        kVar.j = new g(kVar.a);
                        kVar.j.a(kVar.p, kVar.q);
                        kVar.j.a(Constants.HTTP_GET, null, null, null, null, false);
                        kVar.j.g = 1;
                        kVar.l = false;
                        kVar.g = 0;
                        kVar.h = 0;
                        kVar.n = 0;
                        kVar.j.d = new l(kVar);
                        kVar.j.c = new n(kVar);
                        kVar.j.b = new o(kVar);
                        kVar.j.a = new p(kVar);
                        new Thread(new q(kVar)).start();
                    case 10012:
                        if (message.arg1 == 0) {
                            c cVar = (c) message.obj;
                            if (cVar != null) {
                                kVar.a = (String) cVar.b;
                                if (kVar.c != null) {
                                    kVar.c.d();
                                }
                                if (kVar.c != null) {
                                    kVar.c.a();
                                }
                                d.a();
                                d.a(kVar.k, false);
                                return;
                            }
                        }
                        if (kVar.c != null) {
                            kVar.c.a(1, 0);
                        }
                    default:
                        break;
                }
            }
        }
    }

    public k() {
        this.b = false;
    }

    public final boolean a() {
        boolean z = false;
        if (this.j != null) {
            this.j.cancel();
        }
        if (this.l) {
            this.f = System.currentTimeMillis();
            long j = (long) ((((double) (this.f - this.e)) / 1000.0d) + 0.5d);
            if (this.c != null) {
                if (j != 0) {
                    this.c.a(0, this.g / j);
                } else {
                    this.c.a(0, 0);
                }
                z = true;
            }
        }
        if (this.o != null) {
            c();
        }
        b();
        return z;
    }

    final void b() {
        this.c = null;
        this.j = null;
        if (this.m != null) {
            this.m.cancel();
            this.m = null;
        }
        this.b = false;
        this.l = false;
        this.k = null;
    }

    final void c() {
        d.a();
        d.a(this.o);
        this.o = null;
    }
}

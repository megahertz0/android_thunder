package com.xunlei.analytics.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import com.xunlei.analytics.HubbleAgent;
import com.xunlei.analytics.c.b;
import com.xunlei.analytics.c.f;
import com.xunlei.analytics.c.g;

public class a {
    private static volatile a c = null;
    private static final int d = 3000;
    private static final int e = 3100;
    private HandlerThread a;
    private Handler b;
    private int f;
    private Callback g;

    private a() {
        this.f = 0;
        this.g = new b(this);
        this.a = new HandlerThread(getClass().getSimpleName() + "-QueryAndUploadThread");
        this.a.start();
        this.b = new Handler(this.a.getLooper(), this.g);
    }

    static /* synthetic */ int a(a aVar) {
        int i = aVar.f;
        aVar.f = i + 1;
        return i;
    }

    public static a a() {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a();
                }
            }
        }
        return c;
    }

    private void a(int i) {
        if (i > 0 && f.a()) {
            f.a(new StringBuilder("delete expiration event count:").append(i).toString());
        }
    }

    private void b() {
        this.b.removeMessages(e);
        this.b.sendMessageDelayed(this.b.obtainMessage(e), HubbleAgent.getReportConfiguration().reportCheckInterval);
    }

    private boolean b(int i) {
        if (!com.xunlei.analytics.config.a.g() || !b.e(com.xunlei.analytics.config.a.d()) || this.f > HubbleAgent.getReportConfiguration().reportRetryCount) {
            return false;
        }
        int b = com.xunlei.analytics.dbstore.b.a().b();
        if (b <= 0 || i != 0) {
            return false;
        }
        int g = b.g(com.xunlei.analytics.config.a.d());
        if (HubbleAgent.getReportConfiguration().isWifiOnly && g != 1) {
            return false;
        }
        if (b >= HubbleAgent.getReportConfiguration().batchUploadCount) {
            return true;
        }
        if (System.currentTimeMillis() - g.c(com.xunlei.analytics.config.a.d()) >= HubbleAgent.getReportConfiguration().reportCheckInterval) {
            return true;
        }
        if (this.b.hasMessages(e)) {
            return false;
        }
        b();
        return false;
    }

    public void a(boolean z) {
        if (!z) {
            this.f = 0;
        }
        this.b.removeMessages(d);
        this.b.obtainMessage(d).sendToTarget();
    }
}

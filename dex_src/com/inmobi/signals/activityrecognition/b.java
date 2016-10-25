package com.inmobi.signals.activityrecognition;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.signals.o;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

// compiled from: ActivityRecognitionSampler.java
public class b {
    private static final String a;
    private static final Object b;
    private static volatile b c;
    private static List<a> d;
    private HandlerThread e;
    private Handler f;

    // compiled from: ActivityRecognitionSampler.java
    static class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    int c = ActivityRecognitionManager.c();
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Polling for ActivityRecognition. Detected activity:").append(c).toString());
                    if (c != -1) {
                        d.add(new a(c, System.currentTimeMillis()));
                    }
                    if (d.size() < o.a().e().s()) {
                        sendEmptyMessageDelayed(0, (long) (o.a().e().r() * 1000));
                    }
                default:
                    break;
            }
        }
    }

    static {
        a = b.class.getSimpleName();
        b = new Object();
    }

    public static b a() {
        b bVar = c;
        if (bVar == null) {
            synchronized (b) {
                bVar = c;
                if (bVar == null) {
                    bVar = new b();
                    c = bVar;
                }
            }
        }
        return bVar;
    }

    private b() {
        d = new ArrayList();
        this.e = new HandlerThread("ActivityRecognitionSampler");
        this.e.start();
        this.f = new a(this.e.getLooper());
    }

    public void b() {
        if (h() && i() && !this.f.hasMessages(0)) {
            ActivityRecognitionManager.a();
            this.f.sendEmptyMessage(0);
        }
    }

    public void c() {
        if (h() && i() && this.f.hasMessages(0)) {
            ActivityRecognitionManager.b();
            this.f.removeMessages(0);
        }
    }

    public List<a> d() {
        return d;
    }

    public void e() {
        d = new ArrayList();
    }

    private static boolean h() {
        if (d.a("signals", "com.google.android.gms.permission.ACTIVITY_RECOGNITION")) {
            return true;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, "Activity recognition sampling did not work due to missing permission.");
        return false;
    }

    private static boolean i() {
        if (com.inmobi.commons.a.a.b().getPackageManager().queryIntentServices(new Intent(com.inmobi.commons.a.a.b(), ActivityRecognitionManager.class), AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0) {
            return true;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, "Activity recognition sampling did not work due to missing service in manifest.");
        return false;
    }
}

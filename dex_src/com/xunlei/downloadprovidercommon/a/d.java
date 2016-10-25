package com.xunlei.downloadprovidercommon.a;

import android.content.Context;
import com.xunlei.analytics.HubbleAgent;

// compiled from: ThunderReport.java
public final class d {
    static final a a;
    static Context b;
    static boolean c;

    static {
        a = new a();
        c = true;
    }

    public static synchronized void a(Context context) {
        synchronized (d.class) {
            Context applicationContext = context.getApplicationContext();
            b = applicationContext;
            b.init(applicationContext);
        }
    }

    public static String d(Context context) {
        return HubbleAgent.getHubbleDeviceId(context);
    }

    public static void a(c cVar) {
        b.reportEvent(cVar);
        if (c) {
            new StringBuilder("[HUBBLE_STAT_EVENT]").append(cVar);
        }
    }

    public static void b(Context context) {
        try {
            b.onResume(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c) {
            new StringBuilder("[HUBBLE_STAT_EVENT][onResume]").append(context);
        }
    }

    public static void c(Context context) {
        try {
            b.onPause(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c) {
            new StringBuilder("[HUBBLE_STAT_EVENT][onPause]").append(context);
        }
    }
}

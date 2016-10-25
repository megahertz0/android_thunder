package com.inmobi.signals.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.b.c.a;
import com.taobao.accs.utl.UtilityImpl;
import java.util.List;

// compiled from: WifiScanner.java
public class c {
    private static final String a;
    private static Context b;
    private static a c;
    private static Handler d;
    private static boolean e;
    private static final IntentFilter f;
    private static List<a> g;
    private static Runnable h;
    private static final BroadcastReceiver i;

    // compiled from: WifiScanner.java
    public static interface a {
        void a();

        void a(List<a> list);
    }

    static {
        a = c.class.getSimpleName();
        b = null;
        c = null;
        d = null;
        e = false;
        f = new IntentFilter("android.net.wifi.SCAN_RESULTS");
        h = new Runnable() {
            public final void run() {
                a b = c;
                c.f();
                if (b != null) {
                    b.a();
                }
            }
        };
        i = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                a b = c;
                WifiManager wifiManager = (WifiManager) b.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                c.f();
                if (b != null) {
                    g = b.a(wifiManager.getScanResults());
                    b.a(g);
                }
            }
        };
    }

    public static boolean a(a aVar) {
        b = com.inmobi.commons.a.a.b();
        return a(Looper.myLooper(), aVar, 10000, false);
    }

    public static List<a> a() {
        return g;
    }

    private static synchronized boolean a(Looper looper, a aVar, long j, boolean z) {
        boolean z2;
        synchronized (c.class) {
            if (d != null) {
                z2 = false;
            } else {
                WifiManager wifiManager = (WifiManager) com.inmobi.commons.a.a.b().getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (wifiManager.isWifiEnabled()) {
                    c = aVar;
                    Handler handler = new Handler(looper);
                    d = handler;
                    handler.postDelayed(h, j);
                    h();
                    z2 = wifiManager.startScan();
                } else {
                    z2 = false;
                }
            }
        }
        return z2;
    }

    private static synchronized void f() {
        synchronized (c.class) {
            if (d != null) {
                d.removeCallbacks(h);
                g();
                d = null;
                c = null;
                b = null;
            }
        }
    }

    private static void g() {
        if (e) {
            e = false;
            try {
                b.unregisterReceiver(i);
            } catch (IllegalArgumentException e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to register for Wifi scanning.");
            }
        }
    }

    private static void h() {
        if (!e) {
            e = true;
            b.registerReceiver(i, f, null, d);
        }
    }
}

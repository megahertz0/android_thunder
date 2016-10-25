package com.xunlei.downloadprovider.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.util.ag.b;
import java.util.HashSet;
import java.util.Set;

// compiled from: XLBroadcast.java
public class ag {
    private static final String a;
    private static final Set<b> b;
    private static final a c;
    private static Intent d;
    private static final com.xunlei.downloadprovider.a.h.a e;

    // compiled from: XLBroadcast.java
    public static abstract class b {
        public void a(Intent intent) {
        }

        public void b(Intent intent) {
        }
    }

    // compiled from: XLBroadcast.java
    private static class a extends BroadcastReceiver {
        private a() {
        }

        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            a;
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                for (b bVar : b) {
                    bVar.a(intent);
                }
            } else if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                d = new Intent(intent);
                for (b bVar2 : b) {
                    bVar2.b(intent);
                }
            } else if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                it = b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
                it = b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            } else if ("android.intent.action.MEDIA_REMOVED".equals(action) || "android.intent.action.MEDIA_BAD_REMOVAL".equals(action)) {
                it = b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            } else if ("android.intent.action.MEDIA_SCANNER_FINISHED".equals(action)) {
                it = b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            } else if ("android.intent.action.MEDIA_SHARED".equals(action)) {
                it = b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }
    }

    static {
        a = ag.class.getSimpleName();
        b = new HashSet();
        c = new a();
        d = null;
        e = new ah();
    }

    public static boolean a(b bVar) {
        if (b.isEmpty()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            BrothersApplication.a().getApplicationContext().registerReceiver(c, intentFilter);
            intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
            intentFilter.addAction("android.intent.action.MEDIA_SHARED");
            intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
            intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
            intentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
            intentFilter.addDataScheme("file");
            BrothersApplication.a().getApplicationContext().registerReceiver(c, intentFilter);
        }
        Message obtainMessage = new com.xunlei.downloadprovider.a.h.b(e).obtainMessage(5001);
        obtainMessage.obj = bVar;
        obtainMessage.sendToTarget();
        return true;
    }

    public static boolean b(b bVar) {
        Message obtainMessage = new com.xunlei.downloadprovider.a.h.b(e).obtainMessage(5002);
        obtainMessage.obj = bVar;
        obtainMessage.sendToTarget();
        if (b.isEmpty()) {
            try {
                BrothersApplication.a().getApplicationContext().unregisterReceiver(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

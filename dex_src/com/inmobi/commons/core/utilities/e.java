package com.inmobi.commons.core.utilities;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// compiled from: SystemBroadcastObserver.java
public class e {
    private static final String a;
    private static HashMap<String, ArrayList<b>> b;
    private static HashMap<String, a> c;
    private static final Object d;
    private static volatile e e;

    // compiled from: SystemBroadcastObserver.java
    public static interface b {
        void b(boolean z);
    }

    // compiled from: SystemBroadcastObserver.java
    static final class a extends BroadcastReceiver {
        private static final String a;

        a() {
        }

        static {
            a = a.class.getSimpleName();
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                boolean z;
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    if (connectivityManager != null) {
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                            z = true;
                        }
                    }
                    z = false;
                } else {
                    if ("android.os.action.DEVICE_IDLE_MODE_CHANGED".equalsIgnoreCase(intent.getAction())) {
                        z = a(context);
                    }
                    z = false;
                }
                e.b(z, intent.getAction());
                Logger.a(InternalLogLevel.INTERNAL, a, intent.getAction() + " Availability:" + z);
            }
        }

        @TargetApi(23)
        private boolean a(Context context) {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return powerManager != null && VERSION.SDK_INT >= 23 && powerManager.isDeviceIdleMode();
        }
    }

    static {
        a = e.class.getSimpleName();
        b = new HashMap();
        c = new HashMap();
        d = new Object();
    }

    public static e a() {
        e eVar = e;
        if (eVar == null) {
            synchronized (d) {
                eVar = e;
                if (eVar == null) {
                    eVar = new e();
                    e = eVar;
                }
            }
        }
        return eVar;
    }

    public void a(String str, b bVar) {
        ArrayList arrayList = (ArrayList) b.get(str);
        if (arrayList != null) {
            arrayList.add(bVar);
        } else {
            arrayList = new ArrayList();
            arrayList.add(bVar);
        }
        b.put(str, arrayList);
        if (arrayList.size() == 1) {
            a(str);
        }
    }

    private void a(String str) {
        BroadcastReceiver aVar = new a();
        c.put(str, aVar);
        com.inmobi.commons.a.a.b().registerReceiver(aVar, new IntentFilter(str));
    }

    private static void b(boolean z, String str) {
        ArrayList arrayList = (ArrayList) b.get(str);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((b) it.next()).b(z);
            }
        }
    }

    public void a(b bVar, String str) {
        ArrayList arrayList = (ArrayList) b.get(str);
        if (arrayList != null) {
            arrayList.remove(bVar);
            if (arrayList.size() == 0) {
                b(str);
            }
        }
    }

    private void b(String str) {
        com.inmobi.commons.a.a.b().unregisterReceiver((BroadcastReceiver) c.get(str));
        c.remove(str);
    }
}

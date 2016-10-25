package com.inmobi.commons.core.configs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;

// compiled from: ConfigComponent.java
public class b {
    private static final String a;
    private static final Object b;
    private static Map<a, ArrayList<WeakReference<b>>> c;
    private static g d;
    private static volatile b e;
    private static c f;
    private HandlerThread g;
    private a h;
    private boolean i;

    // compiled from: ConfigComponent.java
    public static interface b {
        void a(a aVar);
    }

    // compiled from: ConfigComponent.java
    static final class a extends Handler implements com.inmobi.commons.core.configs.d.a {
        private List<a> a;
        private Map<String, Map<String, a>> b;
        private Map<String, a> c;
        private ExecutorService d;

        a(Looper looper) {
            super(looper);
            this.a = new ArrayList();
            this.b = new HashMap();
            this.c = new HashMap();
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    a aVar = (a) message.obj;
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Fetch requested for config:").append(aVar.a()).append(". IsAlreadyScheduled:").append(a(aVar.a())).toString());
                    if (a(aVar.a())) {
                        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config fetching already in progress:").append(aVar.a()).toString());
                        return;
                    }
                    this.a.add(aVar);
                    if (!hasMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                        sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_FAILED, (long) (d.g() * 1000));
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    a(this.a);
                    this.a.clear();
                    if (this.d == null || this.d.isShutdown()) {
                        this.d = Executors.newFixedThreadPool(1);
                        sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
                    }
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    Entry entry;
                    if (this.b.isEmpty()) {
                        entry = null;
                    } else {
                        entry = (Entry) this.b.entrySet().iterator().next();
                    }
                    if (entry != null) {
                        this.c = (Map) entry.getValue();
                        this.b.remove(entry.getKey());
                        a((String) entry.getKey(), this.c);
                        return;
                    }
                    Logger.a(InternalLogLevel.INTERNAL, a, "Config fetching stopping as no more configs left to fetch.");
                    sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    a();
                default:
                    break;
            }
        }

        public final void a() {
            if (this.d != null && !this.d.isShutdown()) {
                this.c = null;
                this.b.clear();
                removeMessages(XZBDevice.DOWNLOAD_LIST_FAILED);
                this.d.shutdownNow();
            }
        }

        private boolean a(String str) {
            boolean z;
            if (this.b.get(d.b(str)) == null || !((Map) this.b.get(d.b(str))).containsKey(str)) {
                z = false;
            } else {
                z = true;
            }
            return (this.c == null || !this.c.containsKey(str)) ? z : true;
        }

        private void a(String str, Map<String, a> map) {
            int f = d.f();
            Map<String, a> map2 = map;
            this.d.execute(new d(this, new e(map2, new d(d.o().a()), str, d.e(), f)));
        }

        private void a(List<a> list) {
            for (int i = 0; i < list.size(); i++) {
                a aVar = (a) list.get(i);
                HashMap hashMap = (HashMap) this.b.get(d.b(aVar.a()));
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(aVar.a(), aVar);
                this.b.put(d.b(aVar.a()), hashMap);
            }
        }

        public final void a(ConfigResponse configResponse) {
            c cVar = new c();
            if (configResponse.d()) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config fetching failed:").append(configResponse.a().a()).append(", Error code:").append(configResponse.c().a()).toString());
            } else if (configResponse.b() == ConfigResponseStatus.NOT_MODIFIED) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config not modified status from server:").append(configResponse.a().a()).toString());
                cVar.a(configResponse.a().a(), System.currentTimeMillis());
            } else {
                cVar.a(configResponse.a());
                try {
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config cached successfully:").append(configResponse.a().a()).toString());
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config cached successfully:").append(configResponse.a().b().toString()).toString());
                    b.b(configResponse.a());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        public final void b() {
            sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }

    // compiled from: ConfigComponent.java
    static class c implements com.inmobi.commons.core.configs.b.b {
        c() {
        }

        public void a(a aVar) {
            d = (g) aVar;
        }
    }

    static {
        a = b.class.getSimpleName();
        b = new Object();
    }

    public static b a() {
        b bVar = e;
        if (bVar == null) {
            synchronized (b) {
                bVar = e;
                if (bVar == null) {
                    bVar = new b();
                    e = bVar;
                }
            }
        }
        return bVar;
    }

    private b() {
        this.i = false;
        c = new HashMap();
        this.g = new HandlerThread("ConfigBootstrapHandler");
        this.g.start();
        this.h = new a(this.g.getLooper());
        d = new g();
    }

    public synchronized void b() {
        if (!this.i) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Starting config component.");
            this.i = true;
            com.inmobi.commons.core.c.a.a().a("root", d.i());
            if (f == null) {
                f = new c();
                a(d, f);
            }
            g();
        }
    }

    public synchronized void c() {
        if (this.i) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Stopping config component.");
            this.i = false;
            this.h.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        }
    }

    private void b(a aVar, b bVar) {
        ArrayList arrayList;
        ArrayList arrayList2 = (ArrayList) c.get(aVar);
        if (arrayList2 == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = arrayList2;
        }
        arrayList.add(bVar == null ? null : new WeakReference(bVar));
        c.put(aVar, arrayList);
    }

    private void g() {
        for (Entry entry : c.entrySet()) {
            a aVar = (a) entry.getKey();
            c(aVar);
            b(aVar);
        }
    }

    private static void b(a aVar) {
        ArrayList arrayList = (ArrayList) c.get(aVar);
        if (arrayList != null) {
            int i = 0;
            while (i < arrayList.size()) {
                if (arrayList.get(i) != null && ((WeakReference) arrayList.get(i)).get() != null) {
                    ((b) ((WeakReference) arrayList.get(i)).get()).a(aVar);
                }
                i++;
            }
        }
    }

    public final synchronized void a(a aVar, b bVar) {
        if (this.i) {
            b(aVar, bVar);
            c(aVar);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config component not yet started, config can't be fetched. Requested type:").append(aVar.a()).toString());
        }
    }

    public final void d() {
        String a = d.h().a();
        String b = d.h().b();
        if (a.trim().length() != 0 && a(com.inmobi.commons.a.b.c(), a.trim())) {
            Logger.a(InternalLogLevel.DEBUG, a, new StringBuilder("A newer version (version ").append(a).append(") of the InMobi SDK is available! You are currently on an older version (Version ").append(com.inmobi.commons.a.b.c()).append("). Please download the latest InMobi SDK from ").append(b).toString());
        }
    }

    private final synchronized void c(a aVar) {
        c cVar = new c();
        if (cVar.a(d.a())) {
            cVar.b(d);
            if (a(cVar.b(d.a()), d.a(d.a()))) {
                Logger.a(InternalLogLevel.INTERNAL, a, "RootConfig expired. Fetching root.");
                d(d.d());
            }
            if (cVar.a(aVar.a())) {
                cVar.b(aVar);
                if (a(cVar.b(aVar.a()), d.a(aVar.a()))) {
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Requested config expired. Returning currently cached and fetching. Config type:").append(aVar.a()).toString());
                    d(aVar.d());
                } else {
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Serving config from cache. Config:").append(aVar.a()).toString());
                }
            } else {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Requested config not present. Returning default and fetching. Config type:").append(aVar.a()).toString());
                d(aVar.d());
            }
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("RootConfig not available. Fetching root and returning defaults for config type:").append(aVar.a()).toString());
            d(d.d());
        }
    }

    private boolean a(long j, long j2) {
        return System.currentTimeMillis() - j > 1000 * j2;
    }

    private void d(a aVar) {
        Message obtainMessage = this.h.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = aVar;
        this.h.sendMessage(obtainMessage);
    }

    public static boolean a(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i = 0;
        while (i < split.length) {
            try {
                if (Integer.valueOf(split[i]).intValue() < 0) {
                    return false;
                }
                i++;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        for (i = 0; i < split2.length; i++) {
            if (Integer.valueOf(split2[i]).intValue() < 0) {
                return false;
            }
        }
        i = split.length < split2.length ? split.length : split2.length;
        int i2 = 0;
        while (i2 < i) {
            if (!split[i2].equals(split2[i2])) {
                return Integer.valueOf(split[i2]).intValue() < Integer.valueOf(split2[i2]).intValue();
            } else {
                i2++;
            }
        }
        return split.length < split2.length;
    }
}

package com.inmobi.commons.core.c;

import android.content.ContentValues;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.configs.b.b;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.c;
import com.inmobi.commons.core.utilities.e;
import com.inmobi.commons.core.utilities.uid.d;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: TelemetryComponent.java
public class a implements b {
    private static final String a;
    private static final Object b;
    private static volatile a c;
    private static boolean d;
    private static final AtomicBoolean e;
    private static Map<String, b> f;
    private static c h;
    private static final Random o;
    private List<e> g;
    private HandlerThread i;
    private a j;
    private Map<String, Integer> k;
    private final Object l;
    private final Object m;
    private final Object n;

    // compiled from: TelemetryComponent.java
    private final class a extends Handler {
        private String b;
        private String c;
        private d d;
        private int e;
        private int f;
        private int g;
        private AtomicBoolean h;
        private int i;
        private int j;
        private boolean k;
        private List<e> l;

        public a(Looper looper) {
            super(looper);
            this.h = new AtomicBoolean(false);
            this.i = 0;
            this.l = new ArrayList();
            this.c = null;
            a();
        }

        private void a() {
            this.e = h.i();
            this.b = h.f();
            this.f = h.k() * 1000;
            this.g = h.g() * 1000;
            this.j = h.j();
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    a.this.c((e) message.obj);
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    removeMessages(1);
                    if (!this.h.compareAndSet(false, true)) {
                        return;
                    }
                    if (com.inmobi.commons.a.a.e() && c.a()) {
                        a();
                        b();
                        return;
                    }
                    Logger.a(InternalLogLevel.INTERNAL, a, "App not in foreground or No Network available ");
                    this.h.set(false);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    c();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.h.set(false);
                    sendEmptyMessageDelayed(1, (long) this.f);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    a.a().j();
                    a.a().g();
                    a.a().i();
                default:
                    break;
            }
        }

        private void b() {
            Logger.a(InternalLogLevel.INTERNAL, a, "Begin reporting");
            this.d = new d();
            List a = this.d.a();
            if (!a.isEmpty()) {
                this.k = true;
                this.c = b(a);
            } else if (this.c == null || this.c.length() == 0) {
                if (this.c == null || this.c.equals(com.umeng.a.d)) {
                    this.k = false;
                    if (this.l.isEmpty()) {
                        this.l = this.d.a(this.e);
                    }
                    if (this.l.isEmpty()) {
                        Logger.a(InternalLogLevel.INTERNAL, a, "No events to report");
                        sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
                        return;
                    }
                    this.c = a(this.l);
                }
            }
            sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }

        private void c() {
            NetworkRequest networkRequest = new NetworkRequest(RequestType.POST, this.b, true, new d(h.o().a()));
            Map hashMap = new HashMap();
            if (this.k) {
                hashMap.put("metric", this.c);
            } else {
                hashMap.put("telemetry", this.c);
            }
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Telemetry Payload: ").append(this.c).toString());
            networkRequest.d(hashMap);
            com.inmobi.commons.core.network.c a = new com.inmobi.commons.core.network.d(networkRequest).a();
            if (a.a()) {
                this.i++;
                if (this.i > this.j) {
                    this.i = 0;
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Unable to send telemetry events to server: ").append(a.b()).append(" . And retry count exhausted. Will Discard Events").toString());
                    this.l.clear();
                    this.c = null;
                    sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
                    return;
                }
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Unable to send telemetry events to server: ").append(a.b()).append(". Will retry").toString());
                this.h.set(false);
                sendEmptyMessageDelayed(1, (long) this.g);
                return;
            }
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Successfully sent events to server: ").append(a.b()).toString());
            this.c = null;
            this.l.clear();
            if (this.d.c() > this.e) {
                this.h.set(false);
                sendEmptyMessage(1);
                return;
            }
            sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
        }

        private String a(List<e> list) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < list.size()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("componentType", ((e) list.get(i)).a());
                    jSONObject.put("eventType", ((e) list.get(i)).b());
                    if (!((e) list.get(i)).c().trim().isEmpty()) {
                        jSONObject.put("payload", ((e) list.get(i)).c());
                    }
                    jSONObject.put(MsgConstant.KEY_TS, ((e) list.get(i)).d());
                    jSONArray.put(jSONObject);
                    i++;
                } catch (JSONException e) {
                    return com.umeng.a.d;
                }
            }
            return jSONArray.toString();
        }

        private String b(List<ContentValues> list) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < list.size()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("componentType", ((ContentValues) list.get(i)).getAsString("componentType"));
                    jSONObject.put("eventType", ((ContentValues) list.get(i)).getAsString("eventType"));
                    jSONObject.put("payload", ((ContentValues) list.get(i)).getAsString("payload"));
                    jSONArray.put(jSONObject);
                    i++;
                } catch (JSONException e) {
                    return com.umeng.a.d;
                }
            }
            return jSONArray.toString();
        }
    }

    static {
        a = a.class.getSimpleName();
        b = new Object();
        d = false;
        e = new AtomicBoolean(false);
        o = new Random(System.currentTimeMillis());
    }

    public static a a() {
        a aVar = c;
        if (aVar == null) {
            synchronized (b) {
                aVar = c;
                if (aVar == null) {
                    aVar = new a();
                    c = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
        this.l = new Object();
        this.m = new Object();
        this.n = new Object();
        this.g = new ArrayList();
        f = new HashMap();
        this.k = new HashMap();
        h = new c();
        e.set(c.a());
        com.inmobi.commons.core.configs.b.a().a(h, (b) this);
        a(h.a(), h.m());
        e.a().a("android.net.conn.CONNECTIVITY_CHANGE", new e.b() {
            public void b(boolean z) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Network status changed ").append(z).toString());
                if (z && !e.get() && com.inmobi.commons.a.a.e()) {
                    a.a().a((int) R.styleable.AppCompatTheme_popupMenuStyle);
                }
                e.set(z);
            }
        });
    }

    public void a(com.inmobi.commons.core.configs.a aVar) {
        h = (c) aVar;
    }

    public final void a(String str, JSONObject jSONObject) {
        a(str, new b(str, jSONObject, h.m()));
    }

    private void a(String str, b bVar) {
        if (str == null || str.trim().equals(com.umeng.a.d)) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Component type provided while registering is null or empty!");
        } else if (bVar != null) {
            f.put(str, bVar);
        } else {
            f.put(str, new b(str, null, h.m()));
        }
    }

    b a(String str) {
        if (str != null && !str.trim().equals(com.umeng.a.d)) {
            return (b) f.get(str);
        }
        Logger.a(InternalLogLevel.INTERNAL, a, "Request null or empty Component type!");
        return null;
    }

    public void a(String str, String str2, Map<String, Object> map) {
        e eVar = new e(str, str2);
        if (!(map == null || map.isEmpty())) {
            try {
                JSONObject jSONObject = new JSONObject();
                for (Entry entry : map.entrySet()) {
                    jSONObject.put(entry.getKey().toString(), entry.getValue());
                }
                eVar.a(jSONObject.toString());
            } catch (JSONException e) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Error forming JSON payload for ").append(str2).append(" Error: ").append(e).toString());
            }
        }
        a().a(eVar);
    }

    public void a(e eVar) {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Event submitted to telemetry: ").append(eVar.b()).append(" - ").append(eVar.a()).toString());
        if (this.j != null) {
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.obj = eVar;
            this.j.sendMessage(obtainMessage);
        }
    }

    private void c(e eVar) {
        b g = g(eVar);
        if (g != null && g.b() && h.e()) {
            d(eVar);
            e(eVar);
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Telemetry service is not enabled or registered for component: ").append(eVar.a()).toString());
    }

    private void d(e eVar) {
        if (h(eVar).c()) {
            b(eVar);
        }
    }

    private void e(e eVar) {
        int b = h(eVar).b();
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Event Sampling factor: ").append(b).toString());
        if (b <= 0) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Sampling factor is <=0 for this event!");
        } else if (o.nextInt(b) != 0) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Event ").append(eVar.b()).append(" is not lucky enough to be processed further").toString());
        } else {
            f(eVar);
        }
    }

    private void f(e eVar) {
        if (eVar instanceof com.inmobi.commons.core.a.b) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Got a crash event, will save it right away!");
            new d().a(eVar);
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Caching event ").append(eVar.b()).append(" in memory").toString());
        int h = h.h();
        synchronized (this.n) {
            this.g.add(eVar);
        }
        if (this.g.size() >= h) {
            g();
            h = new d().c();
            int l = h.l();
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Current event count: ").append(h).append(" Upper cap: ").append(l).toString());
            if (h > (l * 3) / 4) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Telemetry is more than 75% full. Begin reporting ");
                h();
            }
        }
    }

    private void g() {
        synchronized (this.n) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Adding events ").append(this.g.toString()).append("to persistence").toString());
            d dVar = new d();
            int l = h.l();
            int c = dVar.c();
            if ((this.g.size() + c) - l <= 0) {
                dVar.a(this.g);
            } else {
                l -= c;
                if (l <= 0) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Persistence is full, won't add events");
                } else {
                    dVar.a(this.g.subList(0, l));
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Persistence will overflow, will add ").append(l).append(" events to persistence").toString());
                }
            }
            this.g.clear();
        }
    }

    private b g(e eVar) {
        return a().a(eVar.a());
    }

    private a h(e eVar) {
        return g(eVar).a(eVar.b());
    }

    private void h() {
        a(0);
    }

    private void a(int i) {
        if (!com.inmobi.commons.a.a.e() || !c.a()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "App not in foreground or No Network available");
        } else if (this.j == null) {
        } else {
            if (i > 0) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Begin reporting after ").append(i).append(" seconds").toString());
                this.j.sendEmptyMessageDelayed(1, (long) (i * 1000));
                return;
            }
            this.j.sendEmptyMessage(1);
        }
    }

    public synchronized void b() {
        try {
            Logger.a(InternalLogLevel.INTERNAL, a, "start called");
            synchronized (this.m) {
                if (!d) {
                    d = true;
                    this.i = new HandlerThread("telemetry");
                    this.i.start();
                    this.j = new a(this.i.getLooper());
                }
            }
            a().h();
        } catch (Throwable th) {
        }
    }

    public synchronized void c() {
        Logger.a(InternalLogLevel.INTERNAL, a, "stop called");
        if (this.j != null) {
            this.j.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }

    private void i() {
        synchronized (this.m) {
            if (this.i != null) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Deiniting telemetry");
                this.i.getLooper().quit();
                this.i.interrupt();
                this.i = null;
                this.j = null;
                d = false;
            }
        }
    }

    void b(e eVar) {
        String a = eVar.a();
        String b = eVar.b();
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Metric collected: ").append(b).append(" - ").append(a).toString());
        b = a(a, b);
        synchronized (this.l) {
            if (this.k.containsKey(b)) {
                this.k.put(b, Integer.valueOf(((Integer) this.k.get(b)).intValue() + 1));
            } else {
                this.k.put(b, Integer.valueOf(1));
            }
        }
    }

    private String a(String str, String str2) {
        return str + "@$#$@" + str2;
    }

    private String[] b(String str) {
        return str.split("\\@\\$\\#\\$\\@");
    }

    private void j() {
        synchronized (this.l) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Saving metric to persistence");
            d dVar = new d();
            dVar.b();
            for (Entry entry : this.k.entrySet()) {
                String[] b = b((String) entry.getKey());
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ParamKey.COUNT, entry.getValue());
                    dVar.a(b[0], b[1], jSONObject.toString());
                } catch (JSONException e) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Error forming metric payload");
                }
            }
            this.k.clear();
        }
    }
}

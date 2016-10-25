package com.xunlei.XLStat;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.b.a;
import com.xunlei.XLStat.b.c;
import com.xunlei.XLStat.j.d;
import com.xunlei.XLStat.j.e;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class b {
    private static int A;
    private static int B;
    private static Object i;
    private static long j;
    private static long k;
    private static String q;
    private static int r;
    private static boolean s;
    private static Context t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;
    private String a;
    private Object b;
    private short c;
    private short d;
    private int e;
    private int f;
    private int g;
    private int h;
    private a l;
    private com.xunlei.XLStat.e.a m;
    private a n;
    private c o;
    private com.xunlei.XLStat.a.a p;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            XLStatLog.d(b.this + "handler", "handleMessage", new StringBuilder(" in data sender msg: ").append(message).toString());
            switch (message.arg1) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    b.this.j();
                case SimpleLog.LOG_LEVEL_DEBUG:
                    if (s) {
                        b.this.a(1, message.what);
                        return;
                    }
                    XLStatLog.i("wang.log.hubble.send", "sendDataWithTCP", "sendDataWithTCP");
                    b.this.a(0, message.what);
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    b.this.e();
                    XLStatLog.d(b.this + "handler", "handleMessage----xxxxxx", new StringBuilder(" sessionData").append(message).toString());
                default:
                    break;
            }
        }
    }

    static {
        j = 3000;
        k = 5000;
        s = false;
        u = 0;
        v = 0;
        w = 0;
        x = 0;
        y = 0;
        z = 0;
        A = 0;
        B = 10;
    }

    public b(Context context, com.xunlei.XLStat.e.a aVar, a aVar2, c cVar) {
        this.a = "DataSender";
        this.b = new Object();
        this.c = (short) 4;
        this.d = (short) 0;
        this.e = 0;
        this.f = 1;
        this.g = 2;
        this.h = 3;
        this.l = null;
        t = context;
        this.m = aVar;
        this.n = aVar2;
        this.o = cVar;
    }

    public synchronized void a(String str, int i) {
        XLStatLog.d(this.a, "setServer", new StringBuilder("ip: ").append(str).append(" : ").append(i).toString());
        q = str;
        r = i;
    }

    public synchronized void a(com.xunlei.XLStat.a.a aVar) {
        XLStatLog.d(this.a, "setMonitor", new StringBuilder("monitor: ").append(aVar).append(", monitor url: ").append(aVar.a("get")).toString());
        this.p = aVar;
    }

    public void a() {
        new Thread() {
            public void run() {
                super.run();
                i = new Object();
                Looper.prepare();
                b.this.l = new a(b.this, Looper.myLooper());
                XLStatLog.d(b.this, "onStart", new StringBuilder("[run] mHandler: ").append(b.this.l).toString());
                b.this.b();
                b.this.a(0);
                Looper.loop();
            }
        }.start();
    }

    private void j() {
        XLStatLog.d(this.a, "sendMonitorData", BuildConfig.VERSION_NAME);
        if (this.p != null) {
            a(this.p.e());
        } else {
            XLStatLog.d(this.a, "sendMonitorData", "monitor is not ready ... ");
        }
    }

    private void a(String str) {
        XLStatLog.d(this.a, "sendMonitorDataWithHttp", new StringBuilder("data: ").append(str).toString());
        if (this.p == null) {
            XLStatLog.d(this.a, "sendMonitorDataWithHttp", "monitor is not ready init ... ");
            return;
        }
        try {
            XLStatLog.d(this.a, "sendMonitorDataWithHttp", new StringBuilder("monitor url: ").append(this.p.a("get")).toString());
            int a = com.xunlei.XLStat.Net.a.a(this.p.a("get"), str);
            XLStatLog.d(this.a, "sendMonitorDataWithHttp", new StringBuilder("responseCode: ").append(a).toString());
            if (a != 200) {
                XLStatLog.d(this.a, "sendMonitorDataWithHttp", new StringBuilder("send monitor info failed ... response code: ").append(a).toString());
            } else if (this.p != null) {
                this.p.d();
            }
            c();
        } catch (IOException e) {
            XLStatLog.d(this.a, "sendMonitorDataWithHttp", "send monitor msg failed ... ");
            e.printStackTrace();
            c();
        }
    }

    public void b() {
        XLStatLog.d(this.a, "sendSystem", BuildConfig.VERSION_NAME);
        a((int) SimpleLog.LOG_LEVEL_DEBUG, 0, j);
        XLStatLog.d(this.a, "sendSystem", "add system triggerItems");
        if (this.p != null && this.p.a()) {
            this.p.a(1);
        }
    }

    public void a(int i) {
        XLStatLog.d(this.a, "sendData", new StringBuilder("reportStrategy: ").append(i).toString());
        a((int) SimpleLog.LOG_LEVEL_DEBUG, i, j);
    }

    public void c() {
        c((int) SimpleLog.LOG_LEVEL_DEBUG);
        if (this.p == null) {
            XLStatLog.d(this.a, "sendMonitor", "monitor is not ready init ... ");
        } else if (this.p.a()) {
            XLStatLog.d(this.a, "sendMonitor", new StringBuilder("this.mHandler: ").append(this.l).append(", send monitor delay time: ").append(this.p.f()).append("isMonitorValid: ").append(this.p.a()).toString());
            a(1, (int) SimpleLog.LOG_LEVEL_DEBUG, this.p.f());
        } else {
            XLStatLog.d(this.a, "sendMonitor", "monitor is invalid ... ");
        }
    }

    private boolean a(int i, int i2, long j) {
        XLStatLog.d(this.a, "injectMsg", new StringBuilder("send message to tcp reportStrategy: ").append(i2).append("  mHandler: ").append(this.l).toString());
        if (i2 > 2 || i2 < -2 || this.l == null) {
            return false;
        }
        Message obtainMessage = this.l.obtainMessage(i2, i, 0);
        XLStatLog.d(this.a, "injectMsg", new StringBuilder("msg: ").append(obtainMessage).append("  SEND_MSG_DELAY: ").append(j).append("  looper:").append(this.l.getLooper()).append(", servertype: ").append(i).toString());
        if (obtainMessage != null) {
            this.l.sendMessageDelayed(obtainMessage, j);
        } else {
            XLStatLog.d(this.a, "injectMsg", "msg or handler is null ... ");
        }
        return true;
    }

    private void c(int i) {
        XLStatLog.d(this.a, "removeMessage", new StringBuilder("what: ").append(i).toString());
        if (this.l != null) {
            this.l.removeMessages(i);
        }
    }

    public synchronized void b(int i) {
        XLStatLog.d(this.a, "notifyNetworkChange", new StringBuilder("nettype: ").append(i).toString());
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                c(0);
                c(1);
                c(-1);
                c((int) TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
                c((int) SimpleLog.LOG_LEVEL_DEBUG);
                h();
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                a(1);
                a(0);
                c();
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                a(0);
                c();
                break;
        }
    }

    private boolean a(int i, int i2) {
        XLStatLog.d(this.a, "sendDataByTCP", new StringBuilder("dataType: ").append(i).append("  reportStrategy: ").append(i2).toString());
        String a = a.a(t);
        XLStatLog.d(this.a, "sendDataByTCP", new StringBuilder("network is ").append(a).toString());
        switch (i2) {
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
            case SimpleLog.LOG_LEVEL_TRACE:
                if (!"WIFI".equals(a)) {
                    XLStatLog.d(this.a, "sendDataByTCP", new StringBuilder("network is not allowed. network: ").append(a).toString());
                    return false;
                }
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                if ("unknown".equals(a)) {
                    XLStatLog.d(this.a, "sendDataByTCP", new StringBuilder("network is not allowed. network: ").append(a).toString());
                    return false;
                }
        }
        Object arrayList = new ArrayList();
        Object arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Object arrayList4 = new ArrayList();
        synchronized (i) {
            byte[] a2 = a(i, i2, arrayList, arrayList2, arrayList3, arrayList4);
            if (((arrayList.size() + arrayList2.size()) + arrayList3.size()) + arrayList4.size() > 0 || !s) {
                u += arrayList.size();
                w += arrayList2.size();
                XLStatLog.d(this.a, "sendDataByteTCP", new StringBuilder("data type: ").append(i).append("  report strategy: ").append(i2).append("  event send count: ").append(u).append("  context send count: ").append(w).append("  events size: ").append(arrayList.size()).append("  contexts size: ").append(arrayList2.size()).append("  clicks size: ").append(arrayList3.size()).append("  heartbeats size: ").append(arrayList4.size()).toString());
                boolean a3 = new com.xunlei.XLStat.Net.c(q, r).a(a2);
                if (this.p != null && this.p.a()) {
                    this.p.d(a2.length);
                }
                if (a3) {
                    int a4;
                    if (arrayList.size() > 0) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            e eVar = (e) arrayList.get(i3);
                            XLStatLog.d(this.a, "sendDataByteTCP", new StringBuilder("event: ").append(eVar.b).append("  ").append(eVar.c).append(" ext:  ").append(eVar.i).toString());
                        }
                        a4 = this.m.a(0, arrayList);
                        v += a4;
                        XLStatLog.d("wang.log.query", "sendDataByteTCP", new StringBuilder("delete events raw: ").append(a4).append("  event delete count: ").append(v).toString());
                    }
                    if (arrayList2.size() > 0) {
                        a4 = this.m.a(1, arrayList2);
                        x += a4;
                        XLStatLog.d(this.a, "sendDataByteTCP", new StringBuilder("delete contexts raw: ").append(a4).append("  context delete count: ").append(x).toString());
                    }
                    arrayList3.size();
                    if (arrayList4.size() > 0) {
                        a4 = this.m.a((int) MqttConnectOptions.MQTT_VERSION_3_1, arrayList4);
                        v += a4;
                        XLStatLog.d(this.a, "sendDataByteTCP", new StringBuilder("delete heartbeats raw: ").append(a4).append(" heartbeat delete count: ").append(z).toString());
                    }
                    if (i == 0 || i == 2) {
                        s = true;
                        if (this.p != null && this.p.a()) {
                            this.p.b(1);
                        }
                    }
                    XLStatLog.d(this.a, "sendDataByteTCP", new StringBuilder("remove messages reportStrategy: ").append(i2).toString());
                    k = 1000;
                    A = 0;
                    if (this.p != null && this.p.a()) {
                        this.p.b((arrayList.size() + arrayList2.size()) + arrayList4.size());
                    }
                } else {
                    XLStatLog.d(this.a, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, "sendError----------------");
                    long j = k * 2;
                    k = j;
                    if (j >= 60000) {
                        k = 60000;
                    }
                    if (i == 0 || i == 2) {
                        s = false;
                    }
                    if (A < B) {
                        a((int) SimpleLog.LOG_LEVEL_DEBUG, i2, k);
                        A++;
                        XLStatLog.d(this.a, "sendDataByteTCP", new StringBuilder("resend time: ").append(A).toString());
                    } else {
                        c((int) TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
                        c((int) SimpleLog.LOG_LEVEL_DEBUG);
                        c(0);
                        c(-1);
                        c(1);
                        XLStatLog.d(this.a, "sendDataByteTCP", new StringBuilder("resend time over RESEND_TIME: ").append(B).append("  stop resend data ... ").toString());
                        A = 0;
                    }
                    if (this.p != null && this.p.a()) {
                        this.p.c(1);
                    }
                }
                return a3;
            }
            c(i2);
            XLStatLog.d(this.a, "sendDataByteTCP", "there is not data for send ... ");
            return true;
        }
    }

    public void d() {
        a((int) MqttConnectOptions.MQTT_VERSION_3_1, 0, 1000);
        XLStatLog.i("wang.log.heart", "heat------", "-----------");
    }

    public void e() {
        if ("WIFI".equals(a.a(t))) {
            synchronized (i) {
                ArrayList a = this.m.a();
                if (a.size() == 0) {
                    return;
                }
                Map hashMap = new HashMap();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    g gVar = (g) it.next();
                    ArrayList arrayList = (ArrayList) hashMap.get(Integer.valueOf(gVar.b));
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        arrayList.add(gVar);
                        hashMap.put(Integer.valueOf(gVar.b), arrayList);
                    } else {
                        arrayList.add(gVar);
                    }
                }
                a(hashMap);
            }
        }
    }

    public boolean a(Map<Integer, ArrayList<g>> map) {
        byte[] c = c((Map) map);
        com.xunlei.XLStat.Net.c cVar = new com.xunlei.XLStat.Net.c(q, r);
        cVar.a = true;
        boolean a = cVar.a(c);
        if (this.p != null && this.p.a()) {
            this.p.d(c.length);
        }
        if (a) {
            if (map.size() > 0) {
                for (Integer num : map.keySet()) {
                    XLStatLog.i(this.a, "delete", new StringBuilder("  :").append(this.m.a((int) MqttConnectOptions.MQTT_VERSION_3_1_1, map.get(num))).toString());
                }
            }
            XLStatLog.i("wang.log.hubble", "send sessionSuccess", "Success  Success  Success");
            if (this.p != null && this.p.a()) {
                this.p.b(1);
            }
        }
        return a;
    }

    private byte[] a(int i, int i2, ArrayList<e> arrayList, ArrayList<com.xunlei.XLStat.f.b> arrayList2, ArrayList<Object> arrayList3, ArrayList<f> arrayList4) {
        byte[] a;
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                a = a(i2, arrayList, arrayList2, arrayList3, arrayList4);
                XLStatLog.d(this.a, "constructData", new StringBuilder("events: ").append(arrayList).append("  events size: ").append(arrayList.size()).append("  contexts: ").append(arrayList2).append("  contexts size: ").append(arrayList2.size()).toString());
                return a;
            case SimpleLog.LOG_LEVEL_TRACE:
                a = b(i2, arrayList, arrayList2, arrayList3, arrayList4);
                XLStatLog.d(this.a, "constructData", new StringBuilder("events:").append(arrayList).append("  events size: ").append(arrayList.size()).append("  contexts: ").append(arrayList2).append("  contexts size: ").append(arrayList2.size()).toString());
                return a;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return d(i2);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return a(i2, (ArrayList) arrayList);
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return b(i2, arrayList2);
            default:
                return null;
        }
    }

    public byte[] b(Map<Integer, ArrayList<g>> map) {
        int size = map.size();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Integer num : map.keySet()) {
            Object b = d.b((ArrayList) map.get(num));
            arrayList.add(b);
            i = b.length + i;
        }
        Object obj = new Object[(i + 4)];
        System.arraycopy(com.xunlei.XLStat.j.b.a(size), 0, obj, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        i = 4;
        int i2 = 0;
        while (i2 < arrayList.size()) {
            byte[] bArr = (byte[]) arrayList.get(i2);
            System.arraycopy(bArr, 0, obj, i, bArr.length);
            i2++;
            i = bArr.length + i;
        }
        return obj;
    }

    public byte[] c(Map<Integer, ArrayList<g>> map) {
        Object a = this.n.a();
        Object obj = new Object[4];
        Arrays.fill(obj, (byte) 0);
        Object obj2 = new Object[4];
        Arrays.fill(obj2, (byte) 0);
        Object obj3 = new Object[4];
        Arrays.fill(obj3, (byte) 0);
        Object obj4 = new Object[4];
        Arrays.fill(obj4, (byte) 0);
        Object obj5 = new Object[4];
        Arrays.fill(obj3, (byte) 0);
        Object b = b((Map) map);
        Object obj6 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj6, 0, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(g()), 0, obj6, SimpleLog.LOG_LEVEL_DEBUG, SimpleLog.LOG_LEVEL_DEBUG);
        byte[] b2 = com.xunlei.XLStat.j.c.b(obj6);
        Object obj7 = new Object[((((((a.length + 4) + 4) + 4) + 4) + 4) + b.length)];
        System.arraycopy(a, 0, obj7, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(obj, 0, obj7, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(obj3, 0, obj7, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(obj2, 0, obj7, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(obj4, 0, obj7, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(obj5, 0, obj7, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(b, 0, obj7, length + 4, b.length);
        byte[] a2 = a(com.xunlei.XLStat.c.a.a(com.xunlei.XLStat.h.a.a(obj7), b2));
        this.d = (short) (this.d + 1);
        return a2;
    }

    private byte[] a(int i, ArrayList<e> arrayList, ArrayList<com.xunlei.XLStat.f.b> arrayList2, ArrayList<Object> arrayList3, ArrayList<f> arrayList4) {
        XLStatLog.d(this.a, "constructAll", new StringBuilder("send type: ").append(i).toString());
        Object a = this.n.a();
        ArrayList arrayList5 = (ArrayList) this.m.a(0, i);
        if (arrayList5 != null) {
            arrayList.addAll(arrayList5);
            XLStatLog.d(this.a, "constructALL", new StringBuilder("query events size: ").append(arrayList5.size()).append("  events size: ").append(arrayList.size()).toString());
        } else {
            XLStatLog.d(this.a, "constructAll", "query events is null ... ");
        }
        Object a2 = d.a(arrayList);
        arrayList5 = (ArrayList) this.m.a(1, i);
        if (arrayList5 != null) {
            arrayList2.addAll(arrayList5);
            XLStatLog.d(this.a, "constructAll", new StringBuilder("query contexts size: ").append(arrayList5.size()).append(" contexts size: ").append(arrayList2.size()).toString());
        } else {
            XLStatLog.d(this.a, "constructAll", "query contexts is null ... ");
        }
        Object c = d.c(arrayList2);
        Object obj = new Object[4];
        Arrays.fill(obj, (byte) 0);
        arrayList5 = (ArrayList) this.m.a((int) MqttConnectOptions.MQTT_VERSION_3_1, i);
        if (arrayList5 != null) {
            arrayList4.addAll(arrayList5);
            XLStatLog.d(this.a, "constructAll", new StringBuilder("query heartbeat size: ").append(arrayList5.size()).append(" heartbeat size: ").append(arrayList4.size()).toString());
        } else {
            XLStatLog.d(this.a, "constructAll", "query heartbeats is null ... ");
        }
        Object d = d.d(arrayList5);
        XLStatLog.d(this.a, "constructAll", new StringBuilder("heartbeatbytes: ").append(e.a(d)).append(", heartbeatbytes len: ").append(d.length).toString());
        this.o.c();
        Object d2 = this.o.d();
        Arrays.fill(new byte[4], (byte) 0);
        Object obj2 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj2, 0, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(g()), 0, obj2, SimpleLog.LOG_LEVEL_DEBUG, SimpleLog.LOG_LEVEL_DEBUG);
        byte[] b = com.xunlei.XLStat.j.c.b(obj2);
        XLStatLog.d(this.a, "DataSender", new StringBuilder("key: ").append(e.a(b)).toString());
        Object obj3 = new Object[(((((a.length + a2.length) + 4) + c.length) + d2.length) + d.length)];
        System.arraycopy(a, 0, obj3, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(a2, 0, obj3, length, a2.length);
        length += a2.length;
        System.arraycopy(obj, 0, obj3, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(c, 0, obj3, length, c.length);
        length += c.length;
        System.arraycopy(d2, 0, obj3, length, d2.length);
        System.arraycopy(d, 0, obj3, length + d2.length, d.length);
        byte[] a3 = a(com.xunlei.XLStat.c.a.a(com.xunlei.XLStat.h.a.a(obj3), b));
        this.d = (short) (this.d + 1);
        return a3;
    }

    private byte[] b(int i, ArrayList<e> arrayList, ArrayList<com.xunlei.XLStat.f.b> arrayList2, ArrayList<Object> arrayList3, ArrayList<f> arrayList4) {
        Object a = this.n.a();
        ArrayList arrayList5 = (ArrayList) this.m.a(0, i);
        if (arrayList5 != null) {
            arrayList.addAll(arrayList5);
            XLStatLog.d(this.a, "constructNonSys", new StringBuilder("query events size: ").append(arrayList5.size()).append("  events size: ").append(arrayList.size()).toString());
        } else {
            XLStatLog.d(this.a, "constructNonSys", "query events is null ... ");
        }
        Object a2 = d.a(arrayList);
        arrayList5 = (ArrayList) this.m.a(1, i);
        if (arrayList5 != null) {
            arrayList2.addAll(arrayList5);
            XLStatLog.d(this.a, "constructNonSys", new StringBuilder("query contexts size: ").append(arrayList5.size()).append("  contexts size: ").append(arrayList2.size()).toString());
        } else {
            XLStatLog.d(this.a, "constructNonSys", "query contexts is null ... ");
        }
        Object c = d.c(arrayList2);
        Object obj = new Object[4];
        Arrays.fill(obj, (byte) 0);
        arrayList5 = (ArrayList) this.m.a((int) MqttConnectOptions.MQTT_VERSION_3_1, i);
        if (arrayList5 != null) {
            arrayList4.addAll(arrayList5);
            XLStatLog.d(this.a, "constructAll", new StringBuilder("query heartbeat size: ").append(arrayList5.size()).append(" heartbeat size: ").append(arrayList4.size()).toString());
        } else {
            XLStatLog.d(this.a, "constructAll", "query heartbeats is null ... ");
        }
        Object d = d.d(arrayList5);
        Object obj2 = new Object[4];
        Arrays.fill(obj2, (byte) 0);
        Arrays.fill(new byte[4], (byte) 0);
        Object obj3 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj3, 0, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(g()), 0, obj3, SimpleLog.LOG_LEVEL_DEBUG, SimpleLog.LOG_LEVEL_DEBUG);
        byte[] b = com.xunlei.XLStat.j.c.b(obj3);
        Object obj4 = new Object[(((((a.length + a2.length) + 4) + c.length) + 4) + d.length)];
        System.arraycopy(a, 0, obj4, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(a2, 0, obj4, length, a2.length);
        length += a2.length;
        System.arraycopy(obj, 0, obj4, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(c, 0, obj4, length, c.length);
        length += c.length;
        System.arraycopy(obj2, 0, obj4, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(d, 0, obj4, length + 4, d.length);
        XLStatLog.d(this.a, "constructNonSys", new StringBuilder("before zlib and encrypt constrcut data: ").append(e.a(obj4)).toString());
        byte[] a3 = a(com.xunlei.XLStat.c.a.a(com.xunlei.XLStat.h.a.a(obj4), b));
        this.d = (short) (this.d + 1);
        return a3;
    }

    private byte[] d(int i) {
        XLStatLog.d(this.a, "constructSys", new StringBuilder("send type: ").append(i).toString());
        Object a = this.n.a();
        Object obj = new Object[4];
        Arrays.fill(obj, (byte) 0);
        Object obj2 = new Object[4];
        Arrays.fill(obj2, (byte) 0);
        Object obj3 = new Object[4];
        Arrays.fill(obj3, (byte) 0);
        Object obj4 = new Object[4];
        Arrays.fill(obj3, (byte) 0);
        Object d = this.o.d();
        Arrays.fill(new byte[4], (byte) 0);
        Object obj5 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj5, 0, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(g()), 0, obj5, SimpleLog.LOG_LEVEL_DEBUG, SimpleLog.LOG_LEVEL_DEBUG);
        byte[] b = com.xunlei.XLStat.j.c.b(obj5);
        Object obj6 = new Object[(((((a.length + 4) + 4) + 4) + d.length) + 4)];
        System.arraycopy(a, 0, obj6, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(obj, 0, obj6, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(obj3, 0, obj6, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(obj2, 0, obj6, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(d, 0, obj6, length, d.length);
        System.arraycopy(obj4, 0, obj6, length + d.length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        byte[] a2 = a(com.xunlei.XLStat.c.a.a(com.xunlei.XLStat.h.a.a(obj6), b));
        this.d = (short) (this.d + 1);
        return a2;
    }

    private byte[] a(int i, ArrayList<e> arrayList) {
        XLStatLog.d(this.a, "constructEvent", new StringBuilder("send type: ").append(i).append("  events: ").append(arrayList).toString());
        Object a = this.n.a();
        ArrayList arrayList2 = (ArrayList) this.m.a(0, i);
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
            XLStatLog.d(this.a, "constructEvent", new StringBuilder("query events size: ").append(arrayList2.size()).append("  events size: ").append(arrayList.size()).toString());
        } else {
            XLStatLog.d(this.a, "constructEvent", "query evnets is null ... ");
        }
        Object a2 = d.a(arrayList);
        Object obj = new Object[4];
        Arrays.fill(obj, (byte) 0);
        Object obj2 = new Object[4];
        Arrays.fill(obj2, (byte) 0);
        Object obj3 = new Object[4];
        Arrays.fill(obj2, (byte) 0);
        Object obj4 = new Object[4];
        Arrays.fill(obj4, (byte) 0);
        Arrays.fill(new byte[4], (byte) 0);
        Object obj5 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj5, 0, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(g()), 0, obj5, SimpleLog.LOG_LEVEL_DEBUG, SimpleLog.LOG_LEVEL_DEBUG);
        byte[] b = com.xunlei.XLStat.j.c.b(obj5);
        Object obj6 = new Object[(((((a.length + a2.length) + 4) + 4) + 4) + 4)];
        System.arraycopy(a, 0, obj6, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(a2, 0, obj6, length, a2.length);
        int length2 = a2.length + length;
        System.arraycopy(obj2, 0, obj6, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length2 += 4;
        System.arraycopy(obj, 0, obj6, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length2 += 4;
        System.arraycopy(obj4, 0, obj6, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj3, 0, obj6, length2 + 4, MqttConnectOptions.MQTT_VERSION_3_1_1);
        byte[] a3 = a(com.xunlei.XLStat.c.a.a(com.xunlei.XLStat.h.a.a(obj6), b));
        this.d = (short) (this.d + 1);
        return a3;
    }

    private byte[] b(int i, ArrayList<com.xunlei.XLStat.f.b> arrayList) {
        XLStatLog.d(this.a, "constructContext", new StringBuilder("send type: ").append(i).append("  contexts: ").append(arrayList).toString());
        Object a = this.n.a();
        Object obj = new Object[4];
        Arrays.fill(obj, (byte) 0);
        ArrayList arrayList2 = (ArrayList) this.m.a(1, i);
        if (arrayList2 == null || arrayList2.size() <= 0) {
            XLStatLog.d(this.a, "constructContext", "query contexts is null ... ");
        } else {
            arrayList.addAll(arrayList2);
            XLStatLog.d(this.a, "constructContext", new StringBuilder("query contexts size: ").append(arrayList2.size()).append("  contexts size: ").append(arrayList.size()).toString());
        }
        Object c = d.c(arrayList);
        Object obj2 = new Object[4];
        Arrays.fill(obj2, (byte) 0);
        Object obj3 = new Object[4];
        Arrays.fill(obj2, (byte) 0);
        Object obj4 = new Object[4];
        Arrays.fill(obj4, (byte) 0);
        Arrays.fill(new byte[4], (byte) 0);
        Object obj5 = new Object[4];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj5, 0, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(g()), 0, obj5, SimpleLog.LOG_LEVEL_DEBUG, SimpleLog.LOG_LEVEL_DEBUG);
        byte[] b = com.xunlei.XLStat.j.c.b(obj5);
        Object obj6 = new Object[(((((a.length + 4) + 4) + c.length) + 4) + 4)];
        System.arraycopy(a, 0, obj6, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(obj, 0, obj6, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(obj2, 0, obj6, length, MqttConnectOptions.MQTT_VERSION_3_1_1);
        length += 4;
        System.arraycopy(c, 0, obj6, length, c.length);
        int length2 = c.length + length;
        System.arraycopy(obj4, 0, obj6, length2, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj3, 0, obj6, length2 + 4, MqttConnectOptions.MQTT_VERSION_3_1_1);
        byte[] a2 = a(com.xunlei.XLStat.c.a.a(com.xunlei.XLStat.h.a.a(obj6), b));
        this.d = (short) (this.d + 1);
        return a2;
    }

    private byte[] a(byte[] bArr) {
        int length = bArr.length;
        int length2 = (((com.xunlei.XLStat.j.b.a(this.c).length + com.xunlei.XLStat.j.b.a(g()).length) + com.xunlei.XLStat.j.b.a(length).length) + com.xunlei.XLStat.j.b.a(this.h).length) + bArr.length;
        Object obj = new Object[length2];
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.c), 0, obj, 0, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(g()), 0, obj, SimpleLog.LOG_LEVEL_DEBUG, SimpleLog.LOG_LEVEL_DEBUG);
        System.arraycopy(com.xunlei.XLStat.j.b.a(length), 0, obj, MqttConnectOptions.MQTT_VERSION_3_1_1, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(com.xunlei.XLStat.j.b.a(this.h), 0, obj, SpdyProtocol.PUBKEY_SEQ_ADASH, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(bArr, 0, obj, R.styleable.Toolbar_titleMargins, bArr.length);
        XLStatLog.d(this.a, "constructContext", new StringBuilder("templen: ").append(bArr.length + 12).append("  totalLen: ").append(length2).append("\nsendData: ").append(e.a(obj)).toString());
        return obj;
    }

    public void f() {
        if (this.l != null) {
            c((int) TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
            c((int) SimpleLog.LOG_LEVEL_DEBUG);
            c(0);
            c(-1);
            c(1);
            this.l.getLooper().quit();
        }
    }

    public short g() {
        if (this.d > (short) 30000) {
            this.d = (short) 0;
        }
        return this.d;
    }

    public void h() {
        s = false;
        XLStatLog.d(this.a, "resetSystemFlag", new StringBuilder("mIsSendSys: ").append(s).toString());
    }
}

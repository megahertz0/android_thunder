package com.xunlei.XLStat;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.c.a;
import com.xunlei.XLStat.f.b;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class c {
    private String a;
    private a b;
    private ArrayList<e> c;
    private ArrayList<b> d;
    private ArrayList<f> e;
    private ArrayList<g> f;
    private int g;
    private com.xunlei.XLStat.e.a h;
    private b i;
    private com.xunlei.XLStat.a.a j;
    private int k;
    private int l;
    private HashMap<Integer, f> m;
    private HashMap<Integer, f> n;
    private boolean o;
    private boolean p;
    private boolean q;
    private Timer r;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            XLStatLog.d(c.this, "handleMessage", new StringBuilder("msg.what: ").append(message.what).toString());
            switch (message.what) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    c.this.c(message.arg1);
                case SimpleLog.LOG_LEVEL_TRACE:
                    c.this.d(message.arg1);
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    c.this.e(message.arg1);
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    if (message.obj != null && (message.obj instanceof g)) {
                        c.this.a((g) message.obj);
                    }
                default:
                    break;
            }
        }
    }

    public c(com.xunlei.XLStat.e.a aVar, b bVar, com.xunlei.XLStat.a.a aVar2) {
        this.a = "StoreHelper";
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 300;
        this.k = 180000;
        this.l = this.k / 2;
        this.m = null;
        this.n = null;
        this.o = true;
        this.p = true;
        this.q = true;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.m = new HashMap();
        this.n = new HashMap();
        this.h = aVar;
        this.i = bVar;
        this.j = aVar2;
        this.r = new Timer(true);
        XLStatLog.d(this.a, "XLStatStoreHepler", new StringBuilder("mEventList: ").append(this.c).append(",  mContextList: ").append(this.d).append(",  mDBManager: ").append(this.h).append(",  mTCPHelper: ").append(this.i).append(", mHeartbeatTimer: ").append(this.r).toString());
    }

    public void a() {
        new Thread() {
            public void run() {
                super.run();
                Looper.prepare();
                c.this.b = new a(c.this, Looper.myLooper());
                XLStatLog.d(c.this, "onStart", new StringBuilder("[run] mHandler: ").append(c.this.b).toString());
                c.this.a(0);
                Looper.loop();
            }
        }.start();
    }

    public void b() {
        XLStatLog.d(this.a, "quit", new StringBuilder("mHandler: ").append(this.b).toString());
        if (this.b != null) {
            this.b.removeMessages(0);
            this.b.removeMessages(-1);
            this.b.removeMessages(1);
            this.b.removeMessages(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
            this.b.removeMessages(SimpleLog.LOG_LEVEL_DEBUG);
            this.b.getLooper().quit();
        }
    }

    public void a(com.xunlei.XLStat.g.c cVar, int i, String str, String str2, String str3, int i2, int i3, int i4, int i5, String str4) {
        XLStatLog.d(this.a, "storeEvent", new StringBuilder("xmlHelper: ").append(cVar).append("  processid: ").append(i).append("  event name: ").append(str).append("  attribute1: ").append(str2).append("  attribute2: ").append(str3).append("  cost1: ").append(i2).append("  cost2: ").append(i3).append("  cost3: ").append(i4).append("  cost4: ").append(i5).append("  extData: ").append(str4).toString());
        int a = cVar.a(str);
        XLStatLog.d(this.a, "storeEvent", new StringBuilder("event id: ").append(a).toString());
        if (a <= 0) {
            XLStatLog.d(this.a, "storeEvent", new StringBuilder("invalide event id: ").append(a).toString());
            return;
        }
        int a2 = cVar.a(cVar.b(str));
        if (a2 == -2) {
            XLStatLog.d(this.a, "storeEvent", "invalid reportTime ... ");
            return;
        }
        a(new e(i, a, str2, str3, i2, i3, i4, i5, str4, a2));
        a(0, a2);
    }

    private void a(e eVar) {
        XLStatLog.d(this.a, "add2EventList", BuildConfig.VERSION_NAME);
        synchronized (this.c) {
            this.c.add(eVar);
        }
    }

    private boolean c(int i) {
        XLStatLog.d(this.a, "storeEvent2DB", new StringBuilder("report strategy: ").append(i).toString());
        if (i > 2 || i <= -2) {
            XLStatLog.d(this.a, "storeEvent2DB", new StringBuilder(" invalide reportStrategy: ").append(i).toString());
            return false;
        }
        synchronized (this.c) {
            Object obj = this.c;
            this.c = new ArrayList();
        }
        try {
            this.h.a(obj, 0);
            XLStatLog.d(this.a, "storeEvent2DB", "add event triggerItems");
            if (this.j != null && this.j.a()) {
                this.j.a(obj.size());
            }
        } catch (Exception e) {
            XLStatLog.d(this.a, "storeEvent2DB", "add event exception ... ");
            e.printStackTrace();
        }
        if (i >= 0) {
            a(i);
        }
        return true;
    }

    public void a(b bVar) {
        XLStatLog.d(this.a, "storeStartContext", BuildConfig.VERSION_NAME);
        b(bVar);
        a(1, bVar.h);
    }

    private void b(b bVar) {
        XLStatLog.d(this.a, "add2StartContextList", BuildConfig.VERSION_NAME);
        synchronized (this.d) {
            this.d.add(bVar);
        }
    }

    private boolean d(int i) {
        XLStatLog.d(this.a, "storeStartContext", new StringBuilder("report strategy: ").append(i).toString());
        if (i <= 0 || i >= -2) {
            Object obj;
            synchronized (this.d) {
                obj = this.d;
                this.d = new ArrayList();
            }
            try {
                this.h.a(obj, 1);
                XLStatLog.d(this.a, "storeStartContext2DB", "add context triggerItems");
                if (this.j != null && this.j.a()) {
                    this.j.a(obj.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            a(i);
            return true;
        }
        XLStatLog.d(this.a, "storeStartContext2DB", new StringBuilder(" invalide reportStrategy: ").append(i).toString());
        return false;
    }

    public void a(com.xunlei.XLStat.g.c cVar, int i, int i2, String str, int i3, String str2, boolean z) {
        XLStatLog.d(this.a, "storeHeartbeat", new StringBuilder("xmlHelper: ").append(cVar).append(", processid: ").append(i).append(", seqid: ").append(i2).append(", heartbeatTag: ").append(str).append(", status: ").append(i3).append(", extData: ").append(str2).append(", autoHeartbeat:").append(z).toString());
        int i4 = -1;
        if ("ProductDownloadStat".equalsIgnoreCase(str)) {
            i4 = XLYunboMassage.MSG_TASKBEGINEXEC;
        } else if ("ProductOnlineStat".equalsIgnoreCase(str)) {
            i4 = XLYunboMassage.MSG_TASKCREATED;
        } else if ("ProductPlayStat".equalsIgnoreCase(str)) {
            i4 = XLYunboMassage.MSG_TASKFINISHED;
        } else {
            XLStatLog.d(this.a, "storeHeartbeat", "heartbeat tag is error ...");
        }
        XLStatLog.d(this.a, "storeHeartbeat", new StringBuilder("heartbeat id: ").append(i4).toString());
        f fVar = new f(i, i2, i4, i3, str2, 0);
        a(fVar);
        if (i3 == 0 && z) {
            synchronized (this.m) {
                if (this.o && "ProductDownloadStat".equals(str)) {
                    this.m.put(Integer.valueOf(i2), fVar);
                    XLStatLog.d(this.a, "storeHeartbeat", "begin download heartbeat");
                }
                if (this.p && "ProductOnlineStat".equals(str)) {
                    this.m.put(Integer.valueOf(i2), fVar);
                    XLStatLog.d(this.a, "storeHeartbeat", "begin online heartbeat");
                }
                if (this.q && "ProductPlayStat".equals(str)) {
                    this.m.put(Integer.valueOf(i2), fVar);
                    XLStatLog.d(this.a, "storeHeartbeat", "begin play heartbeat");
                }
            }
        }
        synchronized (this.m) {
            XLStatLog.d(this.a, "storeHeartbeat", new StringBuilder("this: ").append(this).append(", innerHeartbeatList: ").append(this.m).toString());
            if (i3 == 2 && this.m != null && this.m.size() > 0 && this.m.containsKey(Integer.valueOf(i2))) {
                this.m.remove(Integer.valueOf(i2));
                XLStatLog.d(this.a, "storeHeartbeat", new StringBuilder("end ").append(str).toString());
            }
        }
        synchronized (this.n) {
            if (i3 == 2) {
                if (this.n.size() > 0 && this.n.containsKey(Integer.valueOf(i2))) {
                    this.n.remove(Integer.valueOf(i2));
                    XLStatLog.d(this.a, "storeHeartbeat", new StringBuilder("end ").append(str).toString());
                }
            }
        }
        a((int) MqttConnectOptions.MQTT_VERSION_3_1, fVar.h);
    }

    private void a(f fVar) {
        XLStatLog.d(this.a, "add2HeartbeatList", BuildConfig.VERSION_NAME);
        synchronized (this.e) {
            this.e.add(fVar);
        }
    }

    private boolean e(int i) {
        XLStatLog.d(this.a, "storeHeartbeat2DB", new StringBuilder("report strategy: ").append(i).toString());
        if (i <= 0 || i >= -2) {
            Object obj;
            synchronized (this.e) {
                obj = this.e;
                this.e = new ArrayList();
            }
            try {
                this.h.a(obj, (int) MqttConnectOptions.MQTT_VERSION_3_1);
                XLStatLog.d(this.a, "storeHeartbeat2DB", "add heartbeat triggerItems");
                if (this.j != null && this.j.a()) {
                    this.j.a(obj.size());
                }
            } catch (Exception e) {
                XLStatLog.d(this.a, "storeHeartbeat2DB", "add heartbeat exception ... ");
                e.printStackTrace();
            }
            XLStatLog.d(this.a, "storeHeartbeat2DB", new StringBuilder("this: ").append(this).toString());
            a(i);
            return true;
        }
        XLStatLog.d(this.a, "storeHeartbeat2DB", new StringBuilder("invalid reportStrategy: ").append(i).toString());
        return false;
    }

    private boolean a(g gVar) {
        Object arrayList = new ArrayList();
        arrayList.add(gVar);
        try {
            this.h.a(arrayList, (int) MqttConnectOptions.MQTT_VERSION_3_1_1);
            XLStatLog.d(this.a, "storeSessionData2DB", "add sessionData triggerItems");
            if (this.j != null && this.j.a()) {
                this.j.a(arrayList.size());
            }
        } catch (Exception e) {
            XLStatLog.d(this.a, "storeSessionData2DB", "add sessionData exception ... ");
            e.printStackTrace();
        }
        XLStatLog.d(this.a, "storeSessionData2DB", new StringBuilder("this: ").append(this).toString());
        return true;
    }

    public void a(int i) {
        XLStatLog.d(this.a, "sendMsg2TCP", new StringBuilder("reportStrategy: ").append(i).append(", this.mDataSender: ").append(this.i).toString());
        if (this.i == null) {
            XLStatLog.d(this.a, "sendMsg2DataSender", "mDataSender is uninited ... ");
        } else {
            this.i.a(i);
        }
    }

    private boolean a(int i, int i2) {
        XLStatLog.d(this.a, "injectMsg", new StringBuilder("type: ").append(i).append("  reportStrategy: ").append(i2).toString());
        if (this.b == null) {
            XLStatLog.d(this.a, "injectMsg", "mHandler is not inited ... ");
            return false;
        }
        Message obtainMessage = this.b.obtainMessage(i, i2, 0);
        if (obtainMessage != null) {
            this.b.sendMessageDelayed(obtainMessage, (long) this.g);
            return true;
        }
        XLStatLog.d(this.a, "injectMsg", new StringBuilder("null msg: ").append(obtainMessage).toString());
        return false;
    }

    public boolean a(g gVar, int i) {
        if (this.f == null) {
            this.f = new ArrayList();
            this.f.add(gVar);
        }
        Message obtainMessage = this.b.obtainMessage(i, gVar);
        if (obtainMessage != null) {
            this.b.sendMessageDelayed(obtainMessage, (long) this.g);
        }
        return true;
    }

    public void b(int i) {
        XLStatLog.d(this.a, "setHeartbeatInterval", new StringBuilder("interval: ").append(i).toString());
        if (i < this.k) {
            this.l = this.k;
        } else {
            this.l = i;
        }
        if (this.l > 0) {
            if (this.r != null) {
                this.r.cancel();
            }
            this.r = new Timer(true);
            this.r.schedule(new TimerTask() {
                public void run() {
                    c.this.i.d();
                    c.this.i.a(-1);
                    XLStatLog.d("[TimerTask]", "run", new StringBuilder("innerHeartbeatList size: ").append(c.this.m.size()).append(", heartbeatInterval: ").append(c.this.l).toString());
                    if (c.this.m != null && c.this.m.size() > 0) {
                        synchronized (c.this.m) {
                            try {
                                synchronized (c.this.e) {
                                    for (Integer num : c.this.m.keySet()) {
                                        f fVar = (f) c.this.m.get(num);
                                        fVar.d = 1;
                                        fVar.f = com.xunlei.XLStat.b.c.e();
                                        fVar.e = BuildConfig.VERSION_NAME;
                                        c.this.e.add(fVar);
                                        c.this.a((int) MqttConnectOptions.MQTT_VERSION_3_1, fVar.h);
                                    }
                                }
                            } catch (Throwable th) {
                            }
                        }
                    }
                }
            }, (long) this.l, (long) this.l);
        }
    }

    public void c() {
        if (this.r != null) {
            this.r.cancel();
        }
    }
}

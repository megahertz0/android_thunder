package com.xunlei.XLStat;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.xunlei.XLStat.CommonStruct.XLStatInitStruct;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.b.b;
import com.xunlei.XLStat.b.c;
import com.xunlei.XLStat.d.a;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class d {
    public a a;
    Timer b;
    TimerTask c;
    private String d;
    private String e;
    private boolean f;
    private a g;
    private c h;
    private c i;
    private com.xunlei.XLStat.g.c j;
    private com.xunlei.XLStat.e.a k;
    private b l;
    private com.xunlei.XLStat.f.a m;
    private com.xunlei.XLStat.a.a n;
    private Context o;
    private String p;
    private boolean q;
    private IntentFilter r;
    private BroadcastReceiver s;
    private int t;
    private int u;

    public d() {
        this.d = "XLStatCenter";
        this.e = "xlstat.INTENT_ACTIONS_SEND_SYSTEMINFO";
        this.f = false;
        this.q = false;
        this.t = 60000;
        this.u = 0;
        this.b = new Timer();
        this.c = new TimerTask() {
            public void run() {
                d.this.f();
                XLStatLog.i("wang.log.hubbleTimer", "-------session", "timer");
            }
        };
    }

    public String a() {
        return com.xunlei.XLStat.i.a.a(this.o).c();
    }

    public boolean a(Context context, XLStatInitStruct xLStatInitStruct) {
        XLStatLog.d(this.d, "init", new StringBuilder("context: ").append(context).append("  init params: ").append(xLStatInitStruct).toString());
        XLStatLog.logThreadID("XLStatCertier", "init");
        if (xLStatInitStruct == null) {
            return false;
        }
        if (!a(xLStatInitStruct)) {
            XLStatLog.d(this.d, "init", "check initParams failed ... ");
            return false;
        } else if (a.a(xLStatInitStruct.productName, xLStatInitStruct.productKey)) {
            XLStatLog.d(this.d, "init", new StringBuilder("config path: ").append(xLStatInitStruct.configPath).toString());
            if (xLStatInitStruct.configPath == null) {
                return false;
            }
            this.p = xLStatInitStruct.configPath;
            this.o = context;
            this.k = new com.xunlei.XLStat.e.a();
            if (this.k == null || !this.k.a(this.o, xLStatInitStruct.dataTag)) {
                XLStatLog.d(this.d, "init", "init mDBManager failed... ");
                this.f = false;
                return this.f;
            }
            this.f = true;
            this.k.a(xLStatInitStruct.dataTag);
            XLStatLog.d(this.d, "init", new StringBuilder("mDBManager: ").append(this.k).toString());
            this.g = new a(context, xLStatInitStruct);
            this.g.a();
            XLStatLog.d(this.d, "init", new StringBuilder(" mBusinessFields: ").append(this.g).toString());
            this.h = new c(context, xLStatInitStruct);
            this.f = this.h.a();
            XLStatLog.d(this.d, "init", new StringBuilder("mSystemHelper: ").append(this.h).toString());
            this.j = new com.xunlei.XLStat.g.c(this.p);
            this.f = this.j.a(this.o);
            XLStatLog.d(this.d, "init", new StringBuilder("mXMLHelper: ").append(this.j).toString());
            this.n = new com.xunlei.XLStat.a.a(this.g);
            this.n.a("get", BuildConfig.VERSION_NAME, 0);
            this.l = new b(this.o, this.k, this.g, this.h);
            this.l.a(this.j.b(), this.j.c());
            this.l.a(this.n);
            this.i = new c(this.k, this.l, this.n);
            this.i.b(xLStatInitStruct.heartbeatInterval);
            this.i.a();
            this.l.a();
            XLStatLog.d(this.d, "init", "start up store and send threads");
            h();
            XLStatLog.d(this.d, "init", "regist broadcast reciver successful ");
            b(k());
            this.m = new com.xunlei.XLStat.f.a(this.j);
            XLStatLog.d(this.d, "init", new StringBuilder("mContextManager: ").append(this.m).toString());
            this.u = (int) System.currentTimeMillis();
            this.a = new a(this.g.c(), context, this.i);
            return this.f;
        } else {
            XLStatLog.d(this.d, "init", "productName or productKey is invalid ... ");
            return false;
        }
    }

    public void a(String str, String str2, String str3, int i, int i2, int i3, int i4, String str4) {
        XLStatLog.d(this.d, "traceEvent", new StringBuilder("event name: ").append(str).append("  attribute1: ").append(str2).append("  attribute2: ").append(str3).append("  cost1: ").append(i).append("  cost2: ").append(i2).append("  cost3: ").append(i3).append("  cost4: ").append(i4).append("  extData: ").append(str4).toString());
        if (str != null && !BuildConfig.VERSION_NAME.equalsIgnoreCase(str)) {
            this.i.a(this.j, b.f(this.o), str, str2 == null ? BuildConfig.VERSION_NAME : str2, str3 == null ? BuildConfig.VERSION_NAME : str3, i, i2, i3, i4, str4);
        }
    }

    public void a(String str, String str2, ArrayList<String> arrayList) {
        XLStatLog.d(this.d, "startContext", new StringBuilder("context name: ").append(str).append("  parentContextName: ").append(str2).append("  extData: ").append(arrayList).toString());
        if (str != null && !BuildConfig.VERSION_NAME.equalsIgnoreCase(str)) {
            this.m.a(this.i, str, str2 == null ? BuildConfig.VERSION_NAME : str2, a((ArrayList) arrayList), b.f(this.o));
        }
    }

    public void a(Context context, String str, ArrayList<String> arrayList) {
        XLStatLog.d(this.d, "startContext", new StringBuilder("context: ").append(this.o).append("  parentContextName: ").append(str).append("  extData: ").append(arrayList).toString());
        if (context != null) {
            this.m.a(this.i, a(context), str == null ? BuildConfig.VERSION_NAME : str, a((ArrayList) arrayList), b.f(this.o));
        }
    }

    public void b() {
    }

    public int a(String str, String str2, boolean z) {
        XLStatLog.d(this.d, "beginHeartbeat", new StringBuilder("heartbeatTag: ").append(str).append(", extdata: ").append(str2).toString());
        if (str == null) {
            return -1;
        }
        int d = d();
        if (str == null) {
            return d;
        }
        this.i.a(this.j, b.f(this.o), d, str, 0, str2, z);
        return d;
    }

    public void a(String str, int i, String str2, boolean z) {
        XLStatLog.d(this.d, "beginHeartbeat", new StringBuilder("heartbeatTag: ").append(str).append(", seqid: ").append(i).append(", extdata: ").append(str2).append(", autoHeartbeat: ").append(z).toString());
        if (str != null) {
            this.i.a(this.j, b.f(this.o), i, str, 0, str2, z);
        }
    }

    public void a(String str, int i, String str2) {
        XLStatLog.d(this.d, "onHeartbeat", new StringBuilder("heartbeatTag: ").append(str).append(", seqid: ").append(i).append(", extdata: ").append(str2).toString());
        if (str != null) {
            this.i.a(this.j, b.f(this.o), i, str, 1, str2, false);
        } else {
            XLStatLog.d(this.d, "onHeartbeat", "illegle heartbeat event ... ");
        }
    }

    public void a(String str, int i, int i2, String str2) {
        XLStatLog.d(this.d, "onHeartbeatEx", new StringBuilder("heartbeatTag: ").append(str).append(", status: ").append(i).append(", seqid: ").append(i2).append(", extdata: ").append(str2).toString());
        if (str != null) {
            this.i.a(this.j, b.f(this.o), i2, str, i, str2, false);
        } else {
            XLStatLog.d(this.d, "onHeartbeatEx", "illegle heartbeat event ... ");
        }
    }

    public void b(String str, int i, String str2) {
        XLStatLog.d(this.d, "endHeartbeat", new StringBuilder("heartbeatTag: ").append(str).append(", seqid: ").append(i).append(", extdata: ").append(str2).toString());
        if (str != null) {
            this.i.a(this.j, b.f(this.o), i, str, SimpleLog.LOG_LEVEL_DEBUG, str2, false);
        } else {
            XLStatLog.d(this.d, "endHeartbeat", "illegle heartbeat event ... ");
        }
    }

    public static String a(ArrayList<String> arrayList) {
        String str = BuildConfig.VERSION_NAME;
        if (arrayList == null) {
            return BuildConfig.VERSION_NAME;
        }
        Iterator it = arrayList.iterator();
        String str2 = str;
        while (it.hasNext()) {
            str2 = str2 + URLEncoder.encode((String) it.next()) + ",";
        }
        return str2.length() > 1 ? str2.substring(0, str2.length() - 1) : str2;
    }

    public void c() {
        XLStatLog.d(this.d, "unInit", " ... ");
        XLStatLog.d(this.d, "unInit", "unregist broadcast receiver... ");
        i();
        if (this.k != null) {
            XLStatLog.d(this.d, "unInit", "uninit mDBManager ... ");
            this.k.b();
        }
        if (this.i != null) {
            XLStatLog.d(this.d, "unInit", "uninit mStoreHelper ... ");
            this.i.b();
        }
        if (this.l != null) {
            XLStatLog.d(this.d, "unInit", "uninit mTCPHelper ... ");
            this.l.f();
        }
        if (this.i != null) {
            XLStatLog.d(this.d, "unInit", "uninit innerHeartbeatTimer ... ");
            this.i.c();
        }
        this.o = null;
    }

    private void h() {
        XLStatLog.d(this.d, "registBroadcastReceiver", new StringBuilder("mBroadcastInit: ").append(this.q).append("  context: ").append(this.o).toString());
        if (!this.q && this.o != null) {
            if (this.r == null) {
                this.r = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                this.r.addAction(this.e);
            }
            XLStatLog.d(this.d, "registBroadcastReceiver", new StringBuilder("next report time: ").append(k()).append(", current time: ").append(System.currentTimeMillis()).append(", mNetChangeReceiver: ").append(this.s).toString());
            if (this.s == null) {
                this.s = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        XLStatLog.d(d.this.d, "BroadcastReceiver", new StringBuilder("onReceive context: ").append(context).append(", intent: ").append(intent).toString());
                        try {
                            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                                d.this.l.b(d.this.j());
                            } else if (d.this.e.equals(intent.getAction())) {
                                d.this.l.h();
                                d.this.l.b();
                                d.this.b(d.this.k());
                            }
                        } catch (Exception e) {
                            XLStatLog.d(d.this.d, "onReceive", new StringBuilder("Failed to handle the action ").append(intent.getAction()).toString());
                        }
                    }
                };
            }
            XLStatLog.d(this.d, "registBroadcastReceiver", new StringBuilder("receiver: ").append(this.o.registerReceiver(this.s, this.r)).toString());
            this.q = true;
        }
    }

    private void b(long j) {
        XLStatLog.d(this.d, "registerSystemInfoReportAlarm", new StringBuilder("regist system report alarm  nextReportTime: ").append(j).toString());
        if (this.o == null) {
            XLStatLog.d(this.d, "registerSystemInfoReportAlarm", new StringBuilder("context: ").append(this.o).append(", next report time: ").append(j).toString());
            return;
        }
        Intent intent = new Intent();
        intent.setAction(this.e);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.o, 0, intent, ClientDefaults.MAX_MSG_SIZE);
        AlarmManager alarmManager = (AlarmManager) this.o.getSystemService("alarm");
        alarmManager.cancel(broadcast);
        alarmManager.set(1, j, broadcast);
        XLStatLog.d(this.d, "registerSystemInfoReportAlarm", new StringBuilder("nextreporttime: ").append(j).append(", current time: ").append(c.e()).toString());
    }

    private void i() {
        XLStatLog.d(this.d, "unRegistBroadcastReceiver", new StringBuilder("mBroadcastInit: ").append(this.q).append("  context: ").append(this.o).toString());
        if (this.o != null && this.q && this.s != null) {
            this.o.unregisterReceiver(this.s);
        }
    }

    private int j() {
        try {
            if (this.o != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.o.getSystemService("connectivity");
                XLStatLog.d(this.d, "getNetType", new StringBuilder("cm: ").append(connectivityManager).toString());
                if (connectivityManager == null) {
                    return 3;
                }
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo == null && networkInfo2 == null) {
                    return 3;
                }
                State state;
                State state2;
                if (networkInfo != null) {
                    state = networkInfo.getState();
                } else {
                    Object obj = null;
                }
                if (networkInfo2 != null) {
                    state2 = networkInfo2.getState();
                } else {
                    state2 = null;
                }
                if (state != null && State.CONNECTED == state) {
                    XLStatLog.d(this.d, "getNetType", "network type: 1");
                    return 1;
                } else if (state2 == null || State.CONNECTED != state2) {
                    return 3;
                } else {
                    XLStatLog.d(this.d, "getNetType", "network type: 2");
                    return SimpleLog.LOG_LEVEL_DEBUG;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        XLStatLog.d(this.d, "getNetType", "network type: 0");
        return 0;
    }

    private long k() {
        Calendar instance = Calendar.getInstance();
        instance.add(SimpleLog.LOG_LEVEL_ERROR, 1);
        int i = instance.get(R.styleable.Toolbar_titleMargins);
        instance.set(SpdyProtocol.PUBKEY_PSEQ_OPEN, 0);
        instance.set(R.styleable.Toolbar_titleMargins, i % 10);
        return instance.getTimeInMillis();
    }

    public void a(long j) {
        this.g.a(j);
    }

    public void b(ArrayList<String> arrayList) {
        this.g.a(a((ArrayList) arrayList));
    }

    public String a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        return activityManager != null ? ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getClassName() : BuildConfig.VERSION_NAME;
    }

    public int d() {
        XLStatLog.d(this.d, "generateHeartbeatID", new StringBuilder("currentHeartbeatID: ").append(this.u).toString());
        int i = this.u;
        this.u = i + 1;
        return i;
    }

    public void a(String str, int i) {
        XLStatLog.d(this.d, "startMonitor", new StringBuilder("addr: ").append(str).append(", reportInterval: ").append(i).toString());
        if (i < this.t) {
            i = this.t;
        }
        this.n.a("get", str, i);
        this.n.b();
        if (this.l != null) {
            this.l.c();
        } else {
            XLStatLog.d(this.d, "startMonitor", "data sender is null ... ");
        }
    }

    public void e() {
        XLStatLog.d(this.d, "stopMonitor", BuildConfig.VERSION_NAME);
        if (this.n != null) {
            this.n.c();
            if (this.l != null) {
                this.l.c();
                this.n.c();
                return;
            }
            XLStatLog.d(this.d, "stopMonitor", "data sender is null ... ");
        }
    }

    public void b(Context context) {
        this.a.a(context);
    }

    public void c(Context context) {
        this.a.b(context);
    }

    public void f() {
        this.l.d();
        XLStatLog.i("DataSenderhandler", "DataSender", "xxxxxxxxxxxxx");
    }

    public void a(String str) {
        this.a.a(str);
    }

    public static String g() {
        return c.f();
    }

    private boolean a(XLStatInitStruct xLStatInitStruct) {
        if (xLStatInitStruct == null || xLStatInitStruct.configPath == null || BuildConfig.VERSION_NAME.equalsIgnoreCase(xLStatInitStruct.configPath) || xLStatInitStruct.productName == null || BuildConfig.VERSION_NAME.equalsIgnoreCase(xLStatInitStruct.productName) || xLStatInitStruct.productKey == null || BuildConfig.VERSION_NAME.equalsIgnoreCase(xLStatInitStruct.productKey)) {
            return false;
        }
        if (xLStatInitStruct.productVersion == null) {
            xLStatInitStruct.productVersion = BuildConfig.VERSION_NAME;
        }
        if (xLStatInitStruct.serviceName == null) {
            xLStatInitStruct.serviceName = BuildConfig.VERSION_NAME;
        }
        if (xLStatInitStruct.serviceKey == null) {
            xLStatInitStruct.serviceKey = BuildConfig.VERSION_NAME;
        }
        if (xLStatInitStruct.serviceVersion == null) {
            xLStatInitStruct.serviceVersion = BuildConfig.VERSION_NAME;
        }
        if (xLStatInitStruct.extData == null) {
            xLStatInitStruct.extData = new ArrayList();
        }
        if (xLStatInitStruct.peerID == null) {
            xLStatInitStruct.peerID = BuildConfig.VERSION_NAME;
        }
        if (xLStatInitStruct.installchannel == null) {
            xLStatInitStruct.installchannel = BuildConfig.VERSION_NAME;
        }
        if (xLStatInitStruct.startupchannel == null) {
            xLStatInitStruct.startupchannel = BuildConfig.VERSION_NAME;
        }
        if (xLStatInitStruct.dataTag == null) {
            xLStatInitStruct.dataTag = BuildConfig.VERSION_NAME;
        }
        return true;
    }
}

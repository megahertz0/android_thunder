package com.xunlei.XLStat.a;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.b.c;
import com.xunlei.xiazaibao.BuildConfig;

public class a {
    private static String j;
    private static com.xunlei.XLStat.a k;
    private String A;
    public int a;
    public int b;
    public int c;
    public long d;
    public String e;
    public String f;
    public String g;
    public long h;
    public long i;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private long p;
    private String q;
    private String r;
    private String s;
    private long t;
    private long u;
    private int v;
    private int w;
    private int x;
    private long y;
    private String z;

    static {
        j = "Monitor";
    }

    public a(com.xunlei.XLStat.a aVar) {
        this.l = false;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = BuildConfig.VERSION_NAME;
        this.r = BuildConfig.VERSION_NAME;
        this.s = BuildConfig.VERSION_NAME;
        this.t = 0;
        this.u = 0;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
        this.g = BuildConfig.VERSION_NAME;
        this.h = 0;
        this.i = 0;
        this.v = 0;
        this.w = 600;
        this.x = 0;
        this.y = (long) ((((this.v * 60) * 1000) + (this.w * 1000)) + this.x);
        this.z = BuildConfig.VERSION_NAME;
        this.A = BuildConfig.VERSION_NAME;
        k = aVar;
    }

    public void a(String str, String str2, int i) {
        XLStatLog.d(j, "initMonitor", new StringBuilder("requestType: ").append(str).append(", url: ").append(str2).append(", interval: ").append(i).append(", mMonitorValid: ").append(this.l).toString());
        this.t = c.e();
        a(str, str2);
        e(i);
        b();
    }

    public synchronized boolean a() {
        return this.l;
    }

    public synchronized void b() {
        this.l = true;
    }

    public synchronized void c() {
        this.l = false;
    }

    private void e(int i) {
        XLStatLog.d(j, "setInterval", new StringBuilder("interval: ").append(i).toString());
        if (this.l) {
            this.y = (long) i;
        }
    }

    private void a(String str, String str2) {
        if ("get".equalsIgnoreCase(str)) {
            this.z = str2;
        } else if ("post".equalsIgnoreCase(str)) {
            this.A = str2;
        } else {
            XLStatLog.d(j, "setURL", "requestType is invalid ... ");
        }
    }

    public synchronized void d() {
        XLStatLog.d(j, "updateMonitorState", BuildConfig.VERSION_NAME);
        XLStatLog.d(j, "updateMonitorState", new StringBuilder("before update: mMonitorStatTriggerItems: ").append(this.m).append(", mMonitorStatReportSuccessItems: ").append(this.n).append(", mMonitorStatRetryTimes: ").append(this.o).append(", mMonitorStatFlowRate: ").append(this.p).append(", mMonitorStatBeginTime: ").append(this.t).append(", mMonitorStatEndTime: ").append(this.u).toString());
        this.m -= this.a;
        this.n -= this.b;
        this.o -= this.c;
        this.p -= this.d;
        XLStatLog.d(j, "updateMonitorState", new StringBuilder("before update: mMonitorStatTriggerItems: ").append(this.m).append(", mMonitorStatReportSuccessItems: ").append(this.n).append(", mMonitorStatRetryTimes: ").append(this.o).append(", mMonitorStatFlowRate: ").append(this.p).append(", mMonitorStatBeginTime: ").append(this.t).append(", mMonitorStatEndTime: ").append(this.u).toString());
    }

    public synchronized String e() {
        String str;
        if (a()) {
            g();
            str = (((((((((BuildConfig.VERSION_NAME + "appid=" + k.b()) + "&sdkver=" + c.f()) + "&peerid=" + k.d()) + "&statnum=" + this.a) + "&retry=" + this.c) + "&errcode=" + this.e + "|" + this.f + "|" + this.g) + "&success=" + this.b) + "&length=" + this.d) + "&begin=" + this.h) + "&end=" + this.i;
            XLStatLog.d(j, "constructMonitorData", new StringBuilder("data: ").append(str).toString());
        } else {
            str = BuildConfig.VERSION_NAME;
        }
        return str;
    }

    private synchronized void g() {
        XLStatLog.d(j, "saveMonitorState", BuildConfig.VERSION_NAME);
        this.a = this.m;
        this.b = this.n;
        this.c = this.o;
        this.d = this.p;
        this.e = this.q;
        this.e = this.r;
        this.e = this.s;
        this.h = this.t;
        long e = c.e();
        this.u = e;
        this.i = e;
        this.t = c.e();
        this.u = 0;
    }

    public long f() {
        return this.y;
    }

    public String a(String str) {
        if (str == null || !"get".equalsIgnoreCase(str)) {
            return (str == null || !"post".equalsIgnoreCase(str)) ? BuildConfig.VERSION_NAME : this.A;
        } else {
            return this.z;
        }
    }

    public synchronized void a(int i) {
        if (this.l) {
            this.m += i;
            XLStatLog.d(j, "addMonitorStatTriggerItems", new StringBuilder("num: ").append(i).append(", total trigger time: ").append(this.m).toString());
        }
    }

    public synchronized void b(int i) {
        if (this.l) {
            this.n += i;
            XLStatLog.d(j, "addMonitorStatReportSuccessItems", new StringBuilder("num: ").append(i).append(", total report success items: ").append(this.n).toString());
        }
    }

    public synchronized void c(int i) {
        if (this.l) {
            this.o += i;
            XLStatLog.d(j, "addMonitorStatRetryTimes", new StringBuilder("times: ").append(i).append(", total retryTimes: ").append(this.o).toString());
        }
    }

    public synchronized void d(int i) {
        if (this.l) {
            this.p += (long) i;
            XLStatLog.d(j, "addMonitorStatFlowRate", new StringBuilder("flowRate: ").append(i).append(", total flowRate: ").append(this.p).toString());
        }
    }
}

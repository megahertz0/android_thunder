package com.xunlei.downloadprovider.model.protocol.report;

import android.content.Context;
import com.xunlei.XLStat.CommonStruct.XLStatInitStruct;
import com.xunlei.XLStat.XLStat;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.util.ArrayList;

@Deprecated
// compiled from: HubbleReportBox.java
public class b {
    private static final String a;
    private static Object b;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static long g;

    // compiled from: HubbleReportBox.java
    public static class a {
        ArrayList<String> a;

        public final com.xunlei.downloadprovider.model.protocol.report.b.a a(String str, String str2) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.add(new StringBuilder(str).append("=").append(str2).toString());
            return this;
        }

        public final com.xunlei.downloadprovider.model.protocol.report.b.a a(String str, long j) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.add(new StringBuilder(str).append("=").append(j).toString());
            return this;
        }
    }

    static {
        a = b.class.getSimpleName();
        c = 0;
        d = 1;
        e = 2;
        f = c;
        g = 0;
    }

    public static void a(Context context, long j) {
        if (context != null && b == null) {
            g = j;
            f = d;
            new Thread(new c(context)).start();
        }
    }

    public static void a() {
        if (b != null) {
            XLStat.unInitXLStat(b);
            f = c;
        }
    }

    public static Object b(Context context, long j) {
        XLStatInitStruct xLStatInitStruct = new XLStatInitStruct();
        xLStatInitStruct.productName = "xl_shoulei2";
        xLStatInitStruct.productKey = "eGxfc2hvdWxlaTIALQAB";
        xLStatInitStruct.productVersion = com.xunlei.downloadprovider.a.b.g(context);
        xLStatInitStruct.installchannel = com.xunlei.downloadprovider.a.b.d(context);
        xLStatInitStruct.startupchannel = com.xunlei.downloadprovider.a.b.d(context);
        xLStatInitStruct.configPath = "hubble/config.xml";
        xLStatInitStruct.dataTag = xLStatInitStruct.productKey;
        xLStatInitStruct.userID = j;
        XLStatLog.closeLog = true;
        return XLStat.initXLStat(context, xLStatInitStruct);
    }

    @Deprecated
    public static void a(long j) {
        g = j;
        if (b == null && f != d) {
            b = b(BrothersApplication.a(), g);
        }
        if (b != null) {
            XLStat.setUserID(b, j);
        }
    }

    @Deprecated
    public static String b() {
        return com.xunlei.downloadprovider.a.b.c();
    }

    @Deprecated
    public static void a(String str, String str2, a aVar) {
        ArrayList arrayList;
        if (b == null && f != d) {
            b = b(BrothersApplication.a(), g);
        }
        Object obj = b;
        if (aVar == null || aVar.a == null) {
            arrayList = null;
        } else {
            arrayList = aVar.a;
        }
        XLStat.traceEventCost(obj, str, str2, null, 0, 0, 0, 0, arrayList);
    }

    @Deprecated
    static void a(Context context) {
        if (b == null && f != d) {
            b = b(BrothersApplication.a(), g);
        }
        if (b != null) {
            XLStat.onResume(b, context);
        }
    }

    @Deprecated
    static void b(Context context) {
        if (b == null && f != d) {
            b = b(BrothersApplication.a(), g);
        }
        if (b != null) {
            XLStat.onPause(b, context);
        }
    }
}

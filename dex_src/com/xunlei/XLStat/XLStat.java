package com.xunlei.XLStat;

import android.content.Context;
import com.umeng.a;
import com.xunlei.XLStat.CommonStruct.XLStatInitStruct;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import java.util.ArrayList;

public class XLStat {
    private static String TAG;
    public static long kSessionContinueMillis;

    static {
        kSessionContinueMillis = 30000;
        TAG = "XLStat";
    }

    public static Object initXLStat(Context context, XLStatInitStruct xLStatInitStruct) {
        XLStatLog.d(TAG, "initXLStat", new StringBuilder("context: ").append(context).append("  initParamStruct: ").append(xLStatInitStruct).toString());
        if (xLStatInitStruct == null) {
            return null;
        }
        d dVar = new d();
        if (!dVar.a(context, xLStatInitStruct)) {
            return null;
        }
        XLStatLog.d(TAG, "initXLStat", new StringBuilder("center: ").append(dVar).toString());
        return dVar;
    }

    public static void traceEventCost(Object obj, String str, String str2, String str3, int i, int i2, int i3, int i4, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "traceEventCost", new StringBuilder("obj: ").append(obj).append(" event name: ").append(str).append("  attribute1: ").append(str2).append("  attribute2: ").append(str3).append("  cost1: ").append(i).append("  cost2: ").append(i2).append("  cost3: ").append(i3).append("  cost4: ").append(i4).append(" extData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "traceEventCost", "obj is null ... ");
            return;
        }
        ((d) obj).a(str, str2, str3, i, i2, i3, i4, d.a(arrayList));
    }

    public static void traceEventAttributes(Object obj, String str, String str2, String str3) {
        XLStatLog.d(TAG, "traceEvent", new StringBuilder("obj: ").append(obj).append(" event name: ").append(str).append(" attribute1: ").append(str2).append(" attribute2: ").append(str3).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "traceEvent", "obj is null ... ");
            return;
        }
        ((d) obj).a(str, str2, str3, 0, 0, 0, 0, a.d);
    }

    public static void traceEvent(Object obj, String str) {
        XLStatLog.d(TAG, "traceEvent", new StringBuilder("obj: ").append(obj).append(" eventName: ").append(str).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "traceEvent", "obj is null ... ");
            return;
        }
        ((d) obj).a(str, a.d, a.d, 0, 0, 0, 0, a.d);
    }

    public static String getGuid(Object obj) {
        if (obj != null) {
            return ((d) obj).a();
        }
        XLStatLog.d(TAG, "traceEvent", "obj is null ... ");
        return null;
    }

    public static void startContext(Object obj, String str, String str2, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "startCcontext", new StringBuilder("obj: ").append(obj).append(" context name: ").append(str).append("  parentContextName: ").append(str2).append("  extData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "startContext", "obj is null ... ");
        } else {
            ((d) obj).a(str, str2, arrayList);
        }
    }

    public static void startContext(Object obj, Context context, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "startContext", new StringBuilder("obj: ").append(obj).append("  context: ").append(context).append("  extData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "startContext", "obj is null ... ");
        } else {
            ((d) obj).a(context, null, arrayList);
        }
    }

    public static void endContext(Object obj, Context context) {
        XLStatLog.d(TAG, "endEvent", new StringBuilder("obj: ").append(obj).toString());
        if (obj != null) {
            ((d) obj).b();
        }
    }

    public static void endContext(Object obj, String str) {
    }

    @Deprecated
    public static int beginDownloadHeartbeat(Object obj, ArrayList<String> arrayList, boolean z) {
        XLStatLog.d(TAG, "beginDownload", new StringBuilder("obj: ").append(obj).append(", downloadExtData: ").append(arrayList).append(", autoHeartbeat: ").append(z).toString());
        if (obj != null) {
            return ((d) obj).a("ProductDownloadStat", d.a(arrayList), z);
        }
        XLStatLog.d(TAG, "beginDownload", "obj is null ... ");
        return -1;
    }

    public static void beginDownloadHeartbeatEx(Object obj, int i, ArrayList<String> arrayList, boolean z) {
        XLStatLog.d(TAG, "beginDownload", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", downloadExtData: ").append(arrayList).append(", autoHeartbeat: ").append(z).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "beginDownload", "obj is null ... ");
        } else {
            ((d) obj).a("ProductDownloadStat", i, d.a(arrayList), z);
        }
    }

    @Deprecated
    public static void onDownloadHeartbeat(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onDownload", new StringBuilder("obj: ").append(obj).append("seqid: ").append(i).append(", downloadExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onDownload", "obj is null ... ");
        } else {
            ((d) obj).a("ProductDownloadStat", i, d.a(arrayList));
        }
    }

    public static void onDownloadHeartbeatEx(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onDownload", new StringBuilder("obj: ").append(obj).append("seqid: ").append(i).append(", downloadExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onDownload", "obj is null ... ");
        } else {
            ((d) obj).a("ProductDownloadStat", i, d.a(arrayList));
        }
    }

    public static void onDownloadHeartbeatStatus(Object obj, int i, int i2, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onDownloadHeartbeatEx", new StringBuilder("obj: ").append(obj).append(", status: ").append(i).append(", seqid: ").append(i2).append(", downloadExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onDownloadHeartbeatEx", "obj is null ... ");
        } else {
            ((d) obj).a("ProductDownloadStat", i, i2, d.a(arrayList));
        }
    }

    @Deprecated
    public static void endDwonloadHeartbeat(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "endDwonload", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", downloadExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "endDwonload", "obj is null ... ");
        } else {
            ((d) obj).b("ProductDownloadStat", i, d.a(arrayList));
        }
    }

    public static void endDwonloadHeartbeatEx(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "endDwonload", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", downloadExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "endDwonload", "obj is null ... ");
        } else {
            ((d) obj).b("ProductDownloadStat", i, d.a(arrayList));
        }
    }

    @Deprecated
    public static int beginOnlineHeartbeat(Object obj, ArrayList<String> arrayList, boolean z) {
        XLStatLog.d(TAG, "beginOnline", new StringBuilder("obj: ").append(obj).append(", onlineExtData: ").append(arrayList).toString());
        if (obj != null) {
            return ((d) obj).a("ProductOnlineStat", d.a(arrayList), z);
        }
        XLStatLog.d(TAG, "beginOnline", "obj is null ... ");
        return -1;
    }

    public static void beginOnlineHeartbeatEx(Object obj, int i, ArrayList<String> arrayList, boolean z) {
        XLStatLog.d(TAG, "beginOnline", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", onlineExtData: ").append(arrayList).append(", autoHeartbeat: ").append(z).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "beginOnline", "obj is null ... ");
        } else {
            ((d) obj).a("ProductOnlineStat", i, d.a(arrayList), z);
        }
    }

    @Deprecated
    public static void onOnlineHeartbeat(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onOnline", new StringBuilder("obj: ").append(obj).append("seqid: ").append(i).append(", onlineExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onOnline", "obj is null ... ");
        } else {
            ((d) obj).a("ProductOnlineStat", i, d.a(arrayList));
        }
    }

    public static void onOnlineHeartbeatEx(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onOnline", new StringBuilder("obj: ").append(obj).append("seqid: ").append(i).append(", onlineExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onOnline", "obj is null ... ");
        } else {
            ((d) obj).a("ProductOnlineStat", i, d.a(arrayList));
        }
    }

    public static void onOnlineHeartbeatStatus(Object obj, int i, int i2, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onOnline", new StringBuilder("obj: ").append(obj).append(", status: ").append(i).append(", seqid: ").append(i2).append(", onlineExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onOnline", "obj is null ... ");
        } else {
            ((d) obj).a("ProductOnlineStat", i, i2, d.a(arrayList));
        }
    }

    @Deprecated
    public static void endOnlineHeartbeat(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "endOnline", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", onlineExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "endOnline", "obj is null ... ");
        } else {
            ((d) obj).b("ProductOnlineStat", i, d.a(arrayList));
        }
    }

    public static void endOnlineHeartbeatEx(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "endOnline", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", onlineExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "endOnline", "obj is null ... ");
        } else {
            ((d) obj).b("ProductOnlineStat", i, d.a(arrayList));
        }
    }

    @Deprecated
    public static int beginPlayHeartbeat(Object obj, ArrayList<String> arrayList, boolean z) {
        XLStatLog.d(TAG, "beginPlay", new StringBuilder("obj: ").append(obj).append(", playExtData: ").append(arrayList).toString());
        if (obj != null) {
            return ((d) obj).a("ProductPlayStat", d.a(arrayList), z);
        }
        XLStatLog.d(TAG, "beginPlay", "obj is null ... ");
        return -1;
    }

    public static void beginPlayHeartbeatEx(Object obj, int i, ArrayList<String> arrayList, boolean z) {
        XLStatLog.d(TAG, "beginPlay", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", playExtData: ").append(arrayList).append(", autoHeartbeat: ").append(z).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "beginPaly", "obj is null ... ");
        } else {
            ((d) obj).a("ProductPlayStat", i, d.a(arrayList), z);
        }
    }

    @Deprecated
    public static void onPlayHeartbeat(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onPlay", new StringBuilder("obj: ").append(obj).append("seqid: ").append(i).append(", playExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onPlay", "obj is null ... ");
        } else {
            ((d) obj).a("ProductPlayStat", i, d.a(arrayList));
        }
    }

    public static void onPlayHeartbeatEx(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onPlay", new StringBuilder("obj: ").append(obj).append("seqid: ").append(i).append(", playExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onPlay", "obj is null ... ");
        } else {
            ((d) obj).a("ProductPlayStat", i, d.a(arrayList));
        }
    }

    public static void onPlayHeartbeatStatus(Object obj, int i, int i2, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "onPlayHeartbeatEx", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i2).append(", playExData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "onPlayHeartbeatEx", "obj is null ... ");
        } else {
            ((d) obj).a("ProductPlayStat", i, i2, d.a(arrayList));
        }
    }

    @Deprecated
    public static void endPlayHeartbeat(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "endPlay", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", playExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "endPlay", "obj is null ... ");
        } else {
            ((d) obj).b("ProductPlayStat", i, d.a(arrayList));
        }
    }

    public static void endPlayHeartbeatEx(Object obj, int i, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "endPlay", new StringBuilder("obj: ").append(obj).append(", seqid: ").append(i).append(", playExtData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "endPlay", "obj is null ... ");
        } else {
            ((d) obj).b("ProductPlayStat", i, d.a(arrayList));
        }
    }

    public static void setUserID(Object obj, long j) {
        if (obj == null) {
            XLStatLog.d(TAG, "setUserId", "obj is null ... ");
        } else {
            ((d) obj).a(j);
        }
    }

    public static void setBusinessFieldsExtData(Object obj, ArrayList<String> arrayList) {
        XLStatLog.d(TAG, "setBusinessFiedlsExtData", new StringBuilder("extData: ").append(arrayList).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "setBusinessFieldsExtData", "obj is null ... ");
        } else {
            ((d) obj).b(arrayList);
        }
    }

    public static void startMonitor(Object obj, String str, int i) {
        XLStatLog.d(TAG, "startMonitor", new StringBuilder("obj: ").append(obj).append(", addr: ").append(str).append(", reportInterval: ").append(i).toString());
        if (obj == null) {
            XLStatLog.d(TAG, "startMonitor", "obj is null ... ");
        } else {
            ((d) obj).a(str, i);
        }
    }

    public static void stopMonitor(Object obj) {
        XLStatLog.d(TAG, "stopMonitor", a.d);
        if (obj == null) {
            XLStatLog.d(TAG, "stopMonitor", "obj is null ... ");
        } else {
            ((d) obj).e();
        }
    }

    public static void unInitXLStat(Object obj) {
        if (obj == null) {
            XLStatLog.d(TAG, "unInitXLStat", "obj is null ... ");
            return;
        }
        ((d) obj).c();
        XLStatLog.d(TAG, "unInitXLStat", "xlstat uninit successfully");
    }

    public static String getSdkVersion() {
        return d.g();
    }

    public static void closeLog() {
        XLStatLog.closeLog = true;
    }

    public static void openLog() {
        XLStatLog.closeLog = false;
    }

    public static void onResume(Object obj, Context context) {
        if (obj != null) {
            d dVar = (d) obj;
            if (dVar != null) {
                dVar.b(context);
            }
        }
    }

    public static void onPause(Object obj, Context context) {
        ((d) obj).c(context);
    }

    public static void setExtDataForActivity(Object obj, ArrayList<String> arrayList) {
        d dVar = (d) obj;
        String str = null;
        if (arrayList != null) {
            str = d.a(arrayList);
        }
        dVar.a(str);
    }
}

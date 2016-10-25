package com.tencent.wxop.stat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.ta.utdid2.android.utils.TimeUtils;
import com.taobao.accs.data.Message;
import com.tencent.a.a.a.a.g;
import com.tencent.stat.DeviceInfo;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.p;
import com.tencent.wxop.stat.common.q;
import com.umeng.a;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URI;
import java.util.Iterator;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public class StatConfig {
    private static String A;
    private static String B;
    private static String C;
    private static String D;
    private static int E;
    private static int F;
    private static long G;
    private static long H;
    private static volatile String I;
    private static int J;
    private static volatile int K;
    private static int L;
    private static int M;
    private static boolean N;
    private static int O;
    private static boolean P;
    private static String Q;
    private static boolean R;
    private static g S;
    static f a;
    static f b;
    static String c;
    static String d;
    static String e;
    static String f;
    static boolean g;
    static int h;
    static long i;
    public static boolean isAutoExceptionCaught;
    static boolean j;
    static volatile String k;
    static boolean l;
    static int m;
    static long n;
    static int o;
    private static StatLogger p;
    private static StatReportStrategy q;
    private static boolean r;
    private static boolean s;
    private static int t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;

    static {
        p = k.b();
        a = new f(2);
        b = new f(1);
        q = StatReportStrategy.APP_LAUNCH;
        r = false;
        s = true;
        t = 30000;
        u = 100000;
        v = 30;
        w = 10;
        x = 100;
        y = 30;
        z = 1;
        c = "__HIBERNATE__";
        d = "__HIBERNATE__TIME";
        e = "__MTA_KILL__";
        A = null;
        D = "mta_channel";
        f = a.d;
        E = 180;
        g = false;
        h = 100;
        i = 10000;
        F = 1024;
        j = true;
        G = 0;
        H = 300000;
        isAutoExceptionCaught = true;
        k = StatConstants.MTA_SERVER;
        I = StatConstants.MTA_REPORT_FULL_URL;
        J = 0;
        K = 0;
        L = 20;
        M = 0;
        N = false;
        O = 4096;
        P = false;
        Q = null;
        R = false;
        S = null;
        l = true;
        m = 0;
        n = 10000;
        o = 512;
    }

    static int a() {
        return v;
    }

    static String a(Context context) {
        return q.a(p.a(context, "_mta_ky_tag_", null));
    }

    static String a(String str, String str2) {
        try {
            String string = b.b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            p.w(new StringBuilder("can't find custom key:").append(str).toString());
            return str2;
        }
    }

    static synchronized void a(int i) {
        synchronized (StatConfig.class) {
            K = i;
        }
    }

    static void a(long j) {
        p.b(i.a(), c, j);
        setEnableStatService(false);
        p.warn("MTA is disable for current SDK version");
    }

    static void a(Context context, f fVar) {
        if (fVar.a == b.a) {
            b = fVar;
            a(fVar.b);
            if (!b.b.isNull("iplist")) {
                a.a(context).a(b.b.getString("iplist"));
            }
        } else if (fVar.a == a.a) {
            a = fVar;
        }
    }

    static void a(Context context, f fVar, JSONObject jSONObject) {
        Object obj = null;
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase(IXAdRequestInfo.V)) {
                    Object obj2;
                    int i = jSONObject.getInt(str);
                    if (fVar.d != i) {
                        int i2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    fVar.d = i;
                    obj = obj2;
                } else if (str.equalsIgnoreCase("c")) {
                    str = jSONObject.getString("c");
                    if (str.length() > 0) {
                        fVar.b = new JSONObject(str);
                    }
                } else if (str.equalsIgnoreCase("m")) {
                    fVar.c = jSONObject.getString("m");
                }
            }
            if (obj == 1) {
                au a = au.a(i.a());
                if (a != null) {
                    a.a(fVar);
                }
                if (fVar.a == b.a) {
                    a(fVar.b);
                    b(fVar.b);
                }
            }
            a(context, fVar);
        } catch (Throwable e) {
            p.e(e);
        } catch (Throwable e2) {
            p.e(e2);
        }
    }

    static void a(Context context, String str) {
        if (str != null) {
            p.b(context, "_mta_ky_tag_", q.b(str));
        }
    }

    static void a(Context context, JSONObject jSONObject) {
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase(Integer.toString(b.a))) {
                    a(context, b, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase(Integer.toString(a.a))) {
                    a(context, a, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase("rs")) {
                    StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt(str));
                    if (statReportStrategy != null) {
                        q = statReportStrategy;
                        if (isDebugEnable()) {
                            p.d(new StringBuilder("Change to ReportStrategy:").append(statReportStrategy.name()).toString());
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Throwable e) {
            p.e(e);
        }
    }

    static void a(JSONObject jSONObject) {
        try {
            StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt("rs"));
            if (statReportStrategy != null) {
                setStatSendStrategy(statReportStrategy);
            }
        } catch (JSONException e) {
            if (isDebugEnable()) {
                p.i("rs not found.");
            }
        }
    }

    static boolean a(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    private static boolean a(String str) {
        if (str == null) {
            return false;
        }
        if (B == null) {
            B = str;
            return true;
        } else if (B.contains(str)) {
            return false;
        } else {
            B += "|" + str;
            return true;
        }
    }

    static boolean a(JSONObject jSONObject, String str, String str2) {
        if (!jSONObject.isNull(str)) {
            String optString = jSONObject.optString(str);
            if (k.c(str2) && k.c(optString) && str2.equalsIgnoreCase(optString)) {
                return true;
            }
        }
        return false;
    }

    static void b() {
        M++;
    }

    static void b(int i) {
        if (i >= 0) {
            M = i;
        }
    }

    static void b(Context context, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString(e);
            if (k.c(optString)) {
                JSONObject jSONObject2 = new JSONObject(optString);
                if (jSONObject2.length() != 0) {
                    Object obj;
                    int intValue;
                    if (!jSONObject2.isNull("sm")) {
                        obj = jSONObject2.get("sm");
                        intValue = obj instanceof Integer ? ((Integer) obj).intValue() : obj instanceof String ? Integer.valueOf((String) obj).intValue() : 0;
                        if (intValue > 0) {
                            if (isDebugEnable()) {
                                p.i(new StringBuilder("match sleepTime:").append(intValue).append(" minutes").toString());
                            }
                            p.b(context, d, System.currentTimeMillis() + ((long) ((intValue * 60) * 1000)));
                            setEnableStatService(false);
                            p.warn("MTA is disable for current SDK version");
                        }
                    }
                    if (a(jSONObject2, com.alipay.sdk.sys.a.h, StatConstants.VERSION)) {
                        p.i("match sdk version:2.0.3");
                        intValue = 1;
                    } else {
                        obj = null;
                    }
                    if (a(jSONObject2, IXAdRequestInfo.TEST_MODE, Build.MODEL)) {
                        p.i(new StringBuilder("match MODEL:").append(Build.MODEL).toString());
                        intValue = 1;
                    }
                    if (a(jSONObject2, com.alipay.sdk.sys.a.k, k.j(context))) {
                        p.i(new StringBuilder("match app version:").append(k.j(context)).toString());
                        intValue = 1;
                    }
                    if (a(jSONObject2, "mf", Build.MANUFACTURER)) {
                        p.i(new StringBuilder("match MANUFACTURER:").append(Build.MANUFACTURER).toString());
                        intValue = 1;
                    }
                    if (a(jSONObject2, IXAdRequestInfo.OSV, VERSION.SDK_INT)) {
                        p.i(new StringBuilder("match android SDK version:").append(VERSION.SDK_INT).toString());
                        intValue = 1;
                    }
                    if (a(jSONObject2, "ov", VERSION.SDK_INT)) {
                        p.i(new StringBuilder("match android SDK version:").append(VERSION.SDK_INT).toString());
                        intValue = 1;
                    }
                    if (a(jSONObject2, DeviceInfo.TAG_IMEI, au.a(context).b(context).b())) {
                        p.i(new StringBuilder("match imei:").append(au.a(context).b(context).b()).toString());
                        intValue = 1;
                    }
                    if (a(jSONObject2, DeviceInfo.TAG_MID, getLocalMidOnly(context))) {
                        p.i(new StringBuilder("match mid:").append(getLocalMidOnly(context)).toString());
                        intValue = 1;
                    }
                    if (obj != null) {
                        a(k.b(StatConstants.VERSION));
                    }
                }
            }
        } catch (Throwable e) {
            p.e(e);
        }
    }

    static void b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                b(i.a(), jSONObject);
                String string = jSONObject.getString(c);
                if (isDebugEnable()) {
                    p.d(new StringBuilder("hibernateVer:").append(string).append(", current version:2.0.3").toString());
                }
                long b = k.b(string);
                if (k.b(StatConstants.VERSION) <= b) {
                    a(b);
                }
            } catch (JSONException e) {
                p.d("__HIBERNATE__ not found.");
            }
        }
    }

    static int c() {
        return M;
    }

    public static synchronized String getAppKey(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (B != null) {
                str = B;
            } else {
                if (context != null) {
                    if (B == null) {
                        B = k.f(context);
                    }
                }
                if (B == null || B.trim().length() == 0) {
                    p.error((Object) "AppKey can not be null or empty, please read Developer's Guide first!");
                }
                str = B;
            }
        }
        return str;
    }

    public static int getCurSessionStatReportCount() {
        return K;
    }

    public static g getCustomLogger() {
        return S;
    }

    public static String getCustomProperty(String str) {
        try {
            return a.b.getString(str);
        } catch (Throwable th) {
            p.e(th);
            return null;
        }
    }

    public static String getCustomProperty(String str, String str2) {
        try {
            String string = a.b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            p.e(th);
            return str2;
        }
    }

    public static String getCustomUserId(Context context) {
        if (context == null) {
            p.error((Object) "Context for getCustomUid is null.");
            return null;
        }
        if (Q == null) {
            Q = p.a(context, "MTA_CUSTOM_UID", a.d);
        }
        return Q;
    }

    public static long getFlushDBSpaceMS() {
        return n;
    }

    public static synchronized String getInstallChannel(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (C != null) {
                str = C;
            } else {
                str = p.a(context, D, a.d);
                C = str;
                if (str == null || C.trim().length() == 0) {
                    C = k.g(context);
                }
                if (C == null || C.trim().length() == 0) {
                    p.w("installChannel can not be null or empty, please read Developer's Guide first!");
                }
                str = C;
            }
        }
        return str;
    }

    public static String getLocalMidOnly(Context context) {
        return context != null ? g.E(context).p().a() : MessageService.MSG_DB_READY_REPORT;
    }

    public static int getMaxBatchReportCount() {
        return y;
    }

    public static int getMaxDaySessionNumbers() {
        return L;
    }

    public static int getMaxImportantDataSendRetryCount() {
        return x;
    }

    public static int getMaxParallelTimmingEvents() {
        return F;
    }

    public static int getMaxReportEventLength() {
        return O;
    }

    public static int getMaxSendRetryCount() {
        return w;
    }

    public static int getMaxSessionStatReportCount() {
        return J;
    }

    public static int getMaxStoreEventCount() {
        return u;
    }

    public static String getMid(Context context) {
        return getLocalMidOnly(context);
    }

    public static long getMsPeriodForMethodsCalledLimitClear() {
        return i;
    }

    public static int getNumEventsCachedInMemory() {
        return m;
    }

    public static int getNumEventsCommitPerSec() {
        return z;
    }

    public static int getNumOfMethodsCalledLimit() {
        return h;
    }

    public static String getQQ(Context context) {
        return p.a(context, "mta.acc.qq", f);
    }

    public static int getReportCompressedSize() {
        return o;
    }

    public static int getSendPeriodMinutes() {
        return E;
    }

    public static int getSessionTimoutMillis() {
        return t;
    }

    public static String getStatReportHost() {
        return k;
    }

    public static String getStatReportUrl() {
        return I;
    }

    public static StatReportStrategy getStatSendStrategy() {
        return q;
    }

    public static boolean isAutoExceptionCaught() {
        return isAutoExceptionCaught;
    }

    public static boolean isDebugEnable() {
        return r;
    }

    public static boolean isEnableConcurrentProcess() {
        return P;
    }

    public static boolean isEnableSmartReporting() {
        return j;
    }

    public static boolean isEnableStatService() {
        return s;
    }

    public static boolean isReportEventsByOrder() {
        return l;
    }

    public static boolean isXGProMode() {
        return R;
    }

    public static void setAppKey(Context context, String str) {
        if (context == null) {
            p.error((Object) "ctx in StatConfig.setAppKey() is null");
        } else if (str == null || str.length() > 256) {
            p.error((Object) "appkey in StatConfig.setAppKey() is null or exceed 256 bytes");
        } else {
            if (B == null) {
                B = a(context);
            }
            if ((a(str) | a(k.f(context))) != 0) {
                a(context, B);
            }
        }
    }

    public static void setAppKey(String str) {
        if (str == null) {
            p.error((Object) "appkey in StatConfig.setAppKey() is null");
        } else if (str.length() > 256) {
            p.error((Object) "The length of appkey cann't exceed 256 bytes.");
        } else {
            B = str;
        }
    }

    public static void setAutoExceptionCaught(boolean z) {
        isAutoExceptionCaught = z;
    }

    public static void setCustomLogger(g gVar) {
        S = gVar;
    }

    public static void setCustomUserId(Context context, String str) {
        if (context == null) {
            p.error((Object) "Context for setCustomUid is null.");
            return;
        }
        p.b(context, "MTA_CUSTOM_UID", str);
        Q = str;
    }

    public static void setDebugEnable(boolean z) {
        r = z;
        k.b().setDebugEnable(z);
    }

    public static void setEnableConcurrentProcess(boolean z) {
        P = z;
    }

    public static void setEnableSmartReporting(boolean z) {
        j = z;
    }

    public static void setEnableStatService(boolean z) {
        s = z;
        if (!z) {
            p.warn("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    public static void setFlushDBSpaceMS(long j) {
        if (j > 0) {
            n = j;
        }
    }

    public static void setInstallChannel(Context context, String str) {
        if (str.length() > 128) {
            p.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
            return;
        }
        C = str;
        p.b(context, D, str);
    }

    public static void setInstallChannel(String str) {
        if (str.length() > 128) {
            p.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
        } else {
            C = str;
        }
    }

    public static void setMaxBatchReportCount(int i) {
        if (a(i, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, (int) IHost.HOST_NOFITY_REFRESH_LIST)) {
            y = i;
        } else {
            p.error((Object) "setMaxBatchReportCount can not exceed the range of [2,1000].");
        }
    }

    public static void setMaxDaySessionNumbers(int i) {
        if (i <= 0) {
            p.e((Object) "maxDaySessionNumbers must be greater than 0.");
        } else {
            L = i;
        }
    }

    public static void setMaxImportantDataSendRetryCount(int i) {
        if (i > 100) {
            x = i;
        }
    }

    public static void setMaxParallelTimmingEvents(int i) {
        if (a(i, 1, (int) Message.FLAG_ERR)) {
            F = i;
        } else {
            p.error((Object) "setMaxParallelTimmingEvents can not exceed the range of [1, 4096].");
        }
    }

    public static void setMaxReportEventLength(int i) {
        if (i <= 0) {
            p.error((Object) "maxReportEventLength on setMaxReportEventLength() must greater than 0.");
        } else {
            O = i;
        }
    }

    public static void setMaxSendRetryCount(int i) {
        if (a(i, 1, (int) IHost.HOST_NOFITY_REFRESH_LIST)) {
            w = i;
        } else {
            p.error((Object) "setMaxSendRetryCount can not exceed the range of [1,1000].");
        }
    }

    public static void setMaxSessionStatReportCount(int i) {
        if (i < 0) {
            p.error((Object) "maxSessionStatReportCount cannot be less than 0.");
        } else {
            J = i;
        }
    }

    public static void setMaxStoreEventCount(int i) {
        if (a(i, 0, 500000)) {
            u = i;
        } else {
            p.error((Object) "setMaxStoreEventCount can not exceed the range of [0, 500000].");
        }
    }

    public static void setNumEventsCachedInMemory(int i) {
        if (i >= 0) {
            m = i;
        }
    }

    public static void setNumEventsCommitPerSec(int i) {
        if (i > 0) {
            z = i;
        }
    }

    public static void setNumOfMethodsCalledLimit(int i, long j) {
        h = i;
        if (j >= 1000) {
            i = j;
        }
    }

    public static void setQQ(Context context, String str) {
        p.b(context, "mta.acc.qq", str);
        f = str;
    }

    public static void setReportCompressedSize(int i) {
        if (i > 0) {
            o = i;
        }
    }

    public static void setReportEventsByOrder(boolean z) {
        l = z;
    }

    public static void setSendPeriodMinutes(int i) {
        if (a(i, 1, 10080)) {
            E = i;
        } else {
            p.error((Object) "setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
        }
    }

    public static void setSessionTimoutMillis(int i) {
        if (a(i, (int) IHost.HOST_NOFITY_REFRESH_LIST, (int) TimeUtils.TOTAL_M_S_ONE_DAY)) {
            t = i;
        } else {
            p.error((Object) "setSessionTimoutMillis can not exceed the range of [1000, 24 * 60 * 60 * 1000].");
        }
    }

    public static void setStatReportUrl(String str) {
        if (str == null || str.length() == 0) {
            p.error((Object) "statReportUrl cannot be null or empty.");
            return;
        }
        I = str;
        try {
            k = new URI(I).getHost();
        } catch (Exception e) {
            p.w(e);
        }
        if (isDebugEnable()) {
            p.i(new StringBuilder("url:").append(I).append(", domain:").append(k).toString());
        }
    }

    public static void setStatSendStrategy(StatReportStrategy statReportStrategy) {
        q = statReportStrategy;
        if (statReportStrategy != StatReportStrategy.PERIOD) {
            StatServiceImpl.c = 0;
        }
        if (isDebugEnable()) {
            p.d(new StringBuilder("Change to statSendStrategy: ").append(statReportStrategy).toString());
        }
    }

    public static void setXGProMode(boolean z) {
        R = z;
    }
}

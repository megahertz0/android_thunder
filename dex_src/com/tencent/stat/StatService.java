package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.stat.a.b;
import com.tencent.stat.a.c;
import com.tencent.stat.a.d;
import com.tencent.stat.a.e;
import com.tencent.stat.a.g;
import com.tencent.stat.a.h;
import com.tencent.stat.a.j;
import com.tencent.stat.common.StatConstants;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.f;
import com.tencent.stat.common.k;
import com.tencent.stat.common.p;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class StatService {
    private static Handler a;
    private static volatile Map<c, Long> b;
    private static volatile long c;
    private static volatile long d;
    private static volatile int e;
    private static volatile String f;
    private static volatile String g;
    private static Map<String, Long> h;
    private static StatLogger i;
    private static UncaughtExceptionHandler j;
    private static volatile boolean k;

    static {
        b = new ConcurrentHashMap();
        c = 0;
        d = 0;
        e = 0;
        f = a.d;
        g = a.d;
        h = new ConcurrentHashMap();
        i = k.b();
        j = null;
        k = true;
    }

    static int a(Context context, boolean z) {
        int i = 1;
        long currentTimeMillis = System.currentTimeMillis();
        if (!z || currentTimeMillis - c < ((long) StatConfig.getSessionTimoutMillis())) {
            boolean z2 = false;
        } else {
            int i2 = 1;
        }
        c = currentTimeMillis;
        if (d == 0) {
            d = k.c();
        }
        if (currentTimeMillis >= d) {
            d = k.c();
            if (n.a(context).b(context).getUserType() != 1) {
                n.a(context).b(context).b(1);
            }
            StatConfig.b(0);
            StatMid.a(context);
            i2 = 1;
        }
        if (!k) {
            i = i2;
        }
        if (i != 0) {
            if (StatConfig.e() < StatConfig.getMaxDaySessionNumbers()) {
                k.F(context);
                d(context);
            } else {
                i.e((Object) "Exceed StatConfig.getMaxDaySessionNumbers().");
            }
        }
        if (k) {
            f.b(context);
            testSpeed(context);
            e(context);
            k = false;
        }
        return e;
    }

    static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (StatConfig.b.d != 0) {
                jSONObject2.put(IXAdRequestInfo.V, StatConfig.b.d);
            }
            jSONObject.put(Integer.toString(StatConfig.b.a), jSONObject2);
            jSONObject2 = new JSONObject();
            if (StatConfig.a.d != 0) {
                jSONObject2.put(IXAdRequestInfo.V, StatConfig.a.d);
            }
            jSONObject.put(Integer.toString(StatConfig.a.a), jSONObject2);
        } catch (Exception e) {
            i.e(e);
        }
        return jSONObject;
    }

    static synchronized void a(Context context) {
        synchronized (StatService.class) {
            if (context != null) {
                if (a == null && b(context)) {
                    if (f.a(context)) {
                        HandlerThread handlerThread = new HandlerThread("StatService");
                        handlerThread.start();
                        a = new Handler(handlerThread.getLooper());
                        n.a(context);
                        d.a(context);
                        d.b();
                        StatConfig.getDeviceInfo(context);
                        j = Thread.getDefaultUncaughtExceptionHandler();
                        if (StatConfig.isAutoExceptionCaught()) {
                            Thread.setDefaultUncaughtExceptionHandler(new g(context.getApplicationContext()));
                        } else {
                            i.warn("MTA SDK AutoExceptionCaught is disable");
                        }
                        if (StatConfig.getStatSendStrategy() == StatReportStrategy.APP_LAUNCH && k.h(context)) {
                            n.a(context).a(-1);
                        }
                        i.d("Init MTA StatService success.");
                    } else {
                        i.e((Object) "ooh, Compatibility problem was found in this device!");
                        i.e((Object) "If you are on debug mode, please delete apk and try again.");
                        StatConfig.setEnableStatService(false);
                    }
                }
            }
        }
    }

    static void a(Context context, Throwable th) {
        try {
            if (!StatConfig.isEnableStatService()) {
                return;
            }
            if (context == null) {
                i.error((Object) "The Context of StatService.reportSdkSelfException() can not be null!");
                return;
            }
            e dVar = new d(context, a(context, false), 99, th);
            if (c(context) != null) {
                c(context).post(new k(dVar));
            }
        } catch (Throwable th2) {
            i.e(new StringBuilder("reportSdkSelfException error: ").append(th2).toString());
        }
    }

    static void a(Context context, Map<String, ?> map) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.sendAdditionEvent() can not be null!");
            return;
        }
        try {
            e aVar = new com.tencent.stat.a.a(context, a(context, false), map);
            if (c(context) != null) {
                c(context).post(new k(aVar));
            }
        } catch (Throwable th) {
            i.e((Object) th);
            a(context, th);
        }
    }

    static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    static boolean b(Context context) {
        if (k.b(StatConstants.VERSION) > p.a(context, StatConfig.c, 0)) {
            return true;
        }
        StatConfig.setEnableStatService(false);
        return false;
    }

    static Handler c(Context context) {
        if (a == null) {
            a(context);
        }
        return a;
    }

    public static void commitEvents(Context context, int i) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.commitEvents() can not be null!");
        } else if (i < -1 || i == 0) {
            i.error((Object) "The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.");
        } else {
            try {
                n.a(context).a(i);
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    static void d(Context context) {
        if (c(context) != null) {
            i.d("start new session.");
            e = k.a();
            StatConfig.a(0);
            StatConfig.d();
            c(context).post(new k(new com.tencent.stat.a.k(context, e, a())));
        }
    }

    static void e(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.reportNativeCrash() can not be null!");
            return;
        }
        try {
            if (c(context) != null) {
                c(context).post(new i(context));
            }
        } catch (Throwable th) {
            i.e((Object) th);
            a(context, th);
        }
    }

    public static void onPause(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.onPause() can not be null!");
        } else {
            trackEndPage(context, k.k(context));
        }
    }

    public static void onResume(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.onResume() can not be null!");
        } else {
            trackBeginPage(context, k.k(context));
        }
    }

    public static void reportAppMonitorStat(Context context, StatAppMonitor statAppMonitor) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.reportAppMonitorStat() can not be null!");
        } else if (statAppMonitor == null) {
            i.error((Object) "The StatAppMonitor of StatService.reportAppMonitorStat() can not be null!");
        } else if (statAppMonitor.getInterfaceName() == null) {
            i.error((Object) "The interfaceName of StatAppMonitor on StatService.reportAppMonitorStat() can not be null!");
        } else {
            try {
                e hVar = new h(context, a(context, false), statAppMonitor);
                if (c(context) != null) {
                    c(context).post(new k(hVar));
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void reportError(Context context, String str) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.reportError() can not be null!");
        } else if (a(str)) {
            i.error((Object) "Error message in StatService.reportError() is empty.");
        } else {
            try {
                e dVar = new d(context, a(context, false), str, 0, StatConfig.getMaxReportEventLength());
                if (c(context) != null) {
                    c(context).post(new k(dVar));
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void reportException(Context context, Throwable th) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.reportException() can not be null!");
        } else if (th == null) {
            i.error((Object) "The Throwable error message of StatService.reportException() can not be null!");
        } else {
            e dVar = new d(context, a(context, false), 1, th);
            if (c(context) != null) {
                c(context).post(new k(dVar));
            }
        }
    }

    public static void reportGameUser(Context context, StatGameUser statGameUser) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.reportGameUser() can not be null!");
        } else if (statGameUser == null) {
            i.error((Object) "The gameUser of StatService.reportGameUser() can not be null!");
        } else if (statGameUser.getAccount() == null || statGameUser.getAccount().length() == 0) {
            i.error((Object) "The account of gameUser on StatService.reportGameUser() can not be null or empty!");
        } else {
            try {
                e gVar = new g(context, a(context, false), statGameUser);
                if (c(context) != null) {
                    c(context).post(new k(gVar));
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void reportQQ(Context context, String str) {
        if (str == null) {
            str = a.d;
        }
        if (!StatConfig.d.equals(str)) {
            StatConfig.d = new String(str);
            a(context, null);
        }
    }

    public static void setEnvAttributes(Context context, Map<String, String> map) {
        if (map == null || map.size() > 512) {
            i.error((Object) "The map in setEnvAttributes can't be null or its size can't exceed 512.");
            return;
        }
        try {
            com.tencent.stat.common.a.a(context, map);
        } catch (Exception e) {
            i.e(e);
        }
    }

    public static void startNewSession(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.startNewSession() can not be null!");
            return;
        }
        try {
            stopSession();
            a(context, true);
        } catch (Throwable th) {
            i.e((Object) th);
            a(context, th);
        }
    }

    public static boolean startStatService(Context context, String str, String str2) {
        if (StatConfig.isEnableStatService()) {
            String str3 = StatConstants.VERSION;
            i.d(new StringBuilder("MTA SDK version, current: ").append(str3).append(" ,required: ").append(str2).toString());
            if (context == null || str2 == null) {
                str3 = "Context or mtaSdkVersion in StatService.startStatService() is null, please check it!";
                i.error((Object) str3);
                StatConfig.setEnableStatService(false);
                throw new MtaSDkException(str3);
            } else if (k.b(str3) < k.b(str2)) {
                str3 = new StringBuilder("MTA SDK version conflicted, current: ").append(str3).append(",required: ").append(str2).toString() + ". please delete the current SDK and download the latest one. official website: http://mta.qq.com/ or http://mta.oa.com/";
                i.error((Object) str3);
                StatConfig.setEnableStatService(false);
                throw new MtaSDkException(str3);
            } else {
                try {
                    str3 = StatConfig.getInstallChannel(context);
                    if (str3 == null || str3.length() == 0) {
                        StatConfig.setInstallChannel(SocializeConstants.OP_DIVIDER_MINUS);
                    }
                    if (str != null) {
                        StatConfig.setAppKey(context, str);
                    }
                    c(context);
                    a(context, false);
                    return true;
                } catch (Object th) {
                    i.e(th);
                    return false;
                }
            }
        }
        i.error((Object) "MTA StatService is disable.");
        return false;
    }

    public static void stopSession() {
        c = 0;
    }

    public static void testSpeed(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.testSpeed() can not be null!");
            return;
        }
        try {
            if (c(context) != null) {
                c(context).post(new j(context, null));
            }
        } catch (Throwable th) {
            i.e((Object) th);
            a(context, th);
        }
    }

    public static void testSpeed(Context context, Map<String, Integer> map) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.testSpeed() can not be null!");
        } else if (map == null || map.size() == 0) {
            i.error((Object) "The domainMap of StatService.testSpeed() can not be null or empty!");
        } else {
            try {
                if (c(context) != null) {
                    c(context).post(new j(context, map));
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void trackBeginPage(Context context, String str) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null || str == null || str.length() == 0) {
            i.error((Object) "The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
            return;
        }
        try {
            synchronized (h) {
                if (h.size() >= StatConfig.getMaxParallelTimmingEvents()) {
                    i.error(new StringBuilder("The number of page events exceeds the maximum value ").append(Integer.toString(StatConfig.getMaxParallelTimmingEvents())).toString());
                    return;
                }
                f = str;
                if (h.containsKey(f)) {
                    i.e(new StringBuilder("Duplicate PageID : ").append(f).append(", onResume() repeated?").toString());
                    return;
                }
                h.put(f, Long.valueOf(System.currentTimeMillis()));
                a(context, true);
            }
        } catch (Throwable th) {
            i.e((Object) th);
            a(context, th);
        }
    }

    public static void trackCustomBeginEvent(Context context, String str, String... strArr) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.trackCustomBeginEvent() can not be null!");
        } else if (a(str)) {
            i.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
        } else {
            try {
                c cVar = new c(str, strArr, null);
                if (b.containsKey(cVar)) {
                    i.error(new StringBuilder("Duplicate CustomEvent key: ").append(cVar.toString()).append(", trackCustomBeginEvent() repeated?").toString());
                } else if (b.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                    b.put(cVar, Long.valueOf(System.currentTimeMillis()));
                } else {
                    i.error(new StringBuilder("The number of timedEvent exceeds the maximum value ").append(Integer.toString(StatConfig.getMaxParallelTimmingEvents())).toString());
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void trackCustomBeginKVEvent(Context context, String str, Properties properties) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.trackCustomBeginEvent() can not be null!");
        } else if (a(str)) {
            i.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
        } else {
            try {
                c cVar = new c(str, null, properties);
                if (b.containsKey(cVar)) {
                    i.error(new StringBuilder("Duplicate CustomEvent key: ").append(cVar.toString()).append(", trackCustomBeginKVEvent() repeated?").toString());
                } else if (b.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                    b.put(cVar, Long.valueOf(System.currentTimeMillis()));
                } else {
                    i.error(new StringBuilder("The number of timedEvent exceeds the maximum value ").append(Integer.toString(StatConfig.getMaxParallelTimmingEvents())).toString());
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void trackCustomEndEvent(Context context, String str, String... strArr) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.trackCustomEndEvent() can not be null!");
        } else if (a(str)) {
            i.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
        } else {
            try {
                c cVar = new c(str, strArr, null);
                Long l = (Long) b.remove(cVar);
                if (l != null) {
                    e bVar = new b(context, a(context, false), str);
                    bVar.a(strArr);
                    l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                    bVar.a(Long.valueOf(l.longValue() == 0 ? 1 : l.longValue()).longValue());
                    if (c(context) != null) {
                        c(context).post(new k(bVar));
                        return;
                    }
                    return;
                }
                i.error(new StringBuilder("No start time found for custom event: ").append(cVar.toString()).append(", lost trackCustomBeginEvent()?").toString());
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void trackCustomEndKVEvent(Context context, String str, Properties properties) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.trackCustomEndEvent() can not be null!");
        } else if (a(str)) {
            i.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
        } else {
            try {
                c cVar = new c(str, null, properties);
                Long l = (Long) b.remove(cVar);
                if (l != null) {
                    e bVar = new b(context, a(context, false), str);
                    bVar.a(properties);
                    l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                    bVar.a(Long.valueOf(l.longValue() == 0 ? 1 : l.longValue()).longValue());
                    if (c(context) != null) {
                        c(context).post(new k(bVar));
                        return;
                    }
                    return;
                }
                i.error(new StringBuilder("No start time found for custom event: ").append(cVar.toString()).append(", lost trackCustomBeginKVEvent()?").toString());
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void trackCustomEvent(Context context, String str, String... strArr) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.trackCustomEvent() can not be null!");
        } else if (a(str)) {
            i.error((Object) "The event_id of StatService.trackCustomEvent() can not be null or empty.");
        } else {
            try {
                e bVar = new b(context, a(context, false), str);
                bVar.a(strArr);
                if (c(context) != null) {
                    c(context).post(new k(bVar));
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void trackCustomKVEvent(Context context, String str, Properties properties) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            i.error((Object) "The Context of StatService.trackCustomEvent() can not be null!");
        } else if (a(str)) {
            i.error((Object) "The event_id of StatService.trackCustomEvent() can not be null or empty.");
        } else {
            try {
                e bVar = new b(context, a(context, false), str);
                bVar.a(properties);
                if (c(context) != null) {
                    c(context).post(new k(bVar));
                }
            } catch (Throwable th) {
                i.e((Object) th);
                a(context, th);
            }
        }
    }

    public static void trackEndPage(Context context, String str) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null || str == null || str.length() == 0) {
            i.error((Object) "The Context or pageName of StatService.trackEndPage() can not be null or empty!");
            return;
        }
        try {
            synchronized (h) {
                Long l = (Long) h.remove(str);
            }
            if (l != null) {
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                if (valueOf.longValue() <= 0) {
                    valueOf = Long.valueOf(1);
                }
                String str2 = g;
                if (str2 != null && str2.equals(str)) {
                    str2 = SocializeConstants.OP_DIVIDER_MINUS;
                }
                if (c(context) != null) {
                    e jVar = new j(context, str2, str, a(context, false), valueOf);
                    if (!str.equals(f)) {
                        i.warn("Invalid invocation since previous onResume on diff page.");
                    }
                    c(context).post(new k(jVar));
                }
                g = str;
                return;
            }
            i.e(new StringBuilder("Starttime for PageID:").append(str).append(" not found, lost onResume()?").toString());
        } catch (Throwable th) {
            i.e((Object) th);
            a(context, th);
        }
    }
}

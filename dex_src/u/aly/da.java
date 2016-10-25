package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: EventTracker.java
public final class da {
    public cy a;
    public ct b;
    private final int c;
    private final int d;
    private Context e;

    public da(Context context) {
        this.c = 128;
        this.d = 256;
        if (context == null) {
            throw new RuntimeException("Context is null, can't track event");
        }
        boolean z;
        this.e = context.getApplicationContext();
        this.a = new cy(this.e);
        cy cyVar = this.a;
        if (AnalyticsConfig.ENABLE_MEMORY_BUFFER) {
            z = false;
        } else {
            z = true;
        }
        cyVar.a = z;
        this.b = ct.a(this.e);
    }

    public final void a(String str, Map<String, Object> map, long j) {
        try {
            if (a(str) && a((Map) map)) {
                this.b.a(new h(str, map, j, -1));
            }
        } catch (Throwable e) {
            v.b("Exception occurred in Mobclick.onEvent(). ", e);
        }
    }

    public final void a(String str, String str2, long j, int i) {
        if (a(str) && b(str2)) {
            Map hashMap = new HashMap();
            if (str2 == null) {
                str2 = BuildConfig.VERSION_NAME;
            }
            hashMap.put(str, str2);
            this.b.a(new h(str, hashMap, j, i));
        }
    }

    public static boolean a(String str) {
        if (str != null) {
            int length = str.trim().getBytes().length;
            if (length > 0 && length <= 128) {
                return true;
            }
        }
        v.c("Event id is empty or too long in tracking Event");
        return false;
    }

    public static boolean b(String str) {
        if (str == null || str.trim().getBytes().length <= 256) {
            return true;
        }
        v.c("Event label or value is empty or too long in tracking Event");
        return false;
    }

    public static boolean a(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            v.c("map is null or empty in onEvent");
            return false;
        }
        for (Entry entry : map.entrySet()) {
            if (!a((String) entry.getKey())) {
                return false;
            }
            if (entry.getValue() == null) {
                return false;
            }
            if ((entry.getValue() instanceof String) && !b(entry.getValue().toString())) {
                return false;
            }
        }
        return true;
    }
}

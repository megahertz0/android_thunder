package com.android.volley.toolbox;

import anet.channel.util.HttpConstant;
import com.alipay.sdk.util.h;
import com.android.volley.b.a;
import com.android.volley.l;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

// compiled from: HttpHeaderParser.java
public final class f {
    public static a a(l lVar) {
        Object obj;
        long j;
        long a;
        long a2;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = lVar.c;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        String str = (String) map.get("Date");
        if (str != null) {
            j2 = a(str);
        }
        str = (String) map.get(HttpConstant.CACHE_CONTROL);
        if (str != null) {
            String[] split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            int i = 0;
            obj = null;
            j = 0;
            j4 = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (!trim.equals("no-cache") && !trim.equals("no-store")) {
                    if (trim.startsWith("max-age=")) {
                        try {
                            j4 = Long.parseLong(trim.substring(XZBDevice.Wait));
                        } catch (Exception e) {
                        }
                    } else if (trim.startsWith("stale-while-revalidate=")) {
                        try {
                            j = Long.parseLong(trim.substring(R.styleable.Toolbar_titleTextColor));
                        } catch (Exception e2) {
                        }
                    } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                        obj = 1;
                    }
                    i++;
                }
                return null;
            }
            j3 = j4;
            j4 = j;
            int i2 = 1;
        } else {
            obj = null;
            Object obj2 = null;
        }
        str = (String) map.get("Expires");
        if (str != null) {
            a = a(str);
        } else {
            a = 0;
        }
        str = (String) map.get("Last-Modified");
        if (str != null) {
            a2 = a(str);
        } else {
            a2 = 0;
        }
        str = (String) map.get("ETag");
        if (obj2 != null) {
            j3 = currentTimeMillis + (1000 * j3);
            j = obj != null ? j3 : (1000 * j4) + j3;
        } else if (j2 <= 0 || a < j2) {
            j = 0;
            j3 = 0;
        } else {
            j = (a - j2) + currentTimeMillis;
            j3 = j;
        }
        a aVar = new a();
        aVar.a = lVar.b;
        aVar.b = str;
        aVar.f = j3;
        aVar.e = j;
        aVar.c = j2;
        aVar.d = a2;
        aVar.g = map;
        return aVar;
    }

    private static long a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0;
        }
    }

    public static String a(Map<String, String> map, String str) {
        String str2 = (String) map.get("Content-Type");
        if (str2 == null) {
            return str;
        }
        String[] split = str2.split(h.b);
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].trim().split("=");
            if (split2.length == 2 && split2[0].equals("charset")) {
                return split2[1];
            }
        }
        return str;
    }

    public static String a(Map<String, String> map) {
        return a(map, "ISO-8859-1");
    }
}

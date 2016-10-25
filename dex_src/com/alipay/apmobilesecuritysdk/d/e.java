package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.b.a.a.a.a.b;
import com.umeng.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class e {
    private static Map<String, String> a;
    private static final String[] b;

    static {
        a = null;
        b = new String[]{"AD1", "AD2", "AD3", "AD5", "AD6", "AD7", "AD8", "AD9", "AD10", "AD11", "AD12", "AD13", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD32", "AD34", "AA1", "AA2", "AA3", "AA4", "AA5", "AC4", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};
    }

    private static String a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        List arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (i < arrayList.size()) {
            String str = (String) arrayList.get(i);
            String str2 = (String) map.get(str);
            if (str2 == null) {
                str2 = a.d;
            }
            stringBuffer.append((i == 0 ? a.d : com.alipay.sdk.sys.a.b) + str + "=" + str2);
            i++;
        }
        return stringBuffer.toString();
    }

    public static synchronized Map<String, String> a(Context context, Map<String, String> map) {
        Map<String, String> map2;
        synchronized (e.class) {
            if (a == null) {
                c(context, map);
            }
            map2 = a;
        }
        return map2;
    }

    public static synchronized void a() {
        synchronized (e.class) {
            a = null;
        }
    }

    public static synchronized String b(Context context, Map<String, String> map) {
        Map treeMap;
        synchronized (e.class) {
            a(context, map);
            treeMap = new TreeMap();
            for (Object obj : b) {
                if (a.containsKey(obj)) {
                    treeMap.put(obj, a.get(obj));
                }
            }
        }
        return b.a(a(treeMap));
    }

    private static synchronized void c(Context context, Map<String, String> map) {
        synchronized (e.class) {
            Map treeMap = new TreeMap();
            a = treeMap;
            treeMap.putAll(b.a(context, map));
            a.putAll(d.a(context));
            a.putAll(c.a(context));
            a.putAll(a.a(context, map));
        }
    }
}

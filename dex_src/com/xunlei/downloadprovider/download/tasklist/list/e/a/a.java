package com.xunlei.downloadprovider.download.tasklist.list.e.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: RecommendAdConstant.java
public final class a {
    private static final List<String> a;
    private static final List<String> b;
    private static final List<String> c;
    private static final Map<Integer, List<String>> d;
    private static final Map<String, Integer> e;

    static {
        List arrayList = new ArrayList(3);
        a = arrayList;
        arrayList.add("1137");
        a.add("1138");
        a.add("1139");
        arrayList = new ArrayList(3);
        b = arrayList;
        arrayList.add("1140");
        b.add("1141");
        b.add("1142");
        arrayList = new ArrayList(3);
        c = arrayList;
        arrayList.add("1143");
        c.add("1144");
        c.add("1145");
        Map hashMap = new HashMap();
        d = hashMap;
        hashMap.put(Integer.valueOf(0), a);
        d.put(Integer.valueOf(1), b);
        d.put(Integer.valueOf(SimpleLog.LOG_LEVEL_DEBUG), c);
        hashMap = new HashMap();
        e = hashMap;
        hashMap.put("1137", Integer.valueOf(0));
        e.put("1138", Integer.valueOf(1));
        e.put("1139", Integer.valueOf(SimpleLog.LOG_LEVEL_DEBUG));
        e.put("1140", Integer.valueOf(0));
        e.put("1141", Integer.valueOf(1));
        e.put("1142", Integer.valueOf(SimpleLog.LOG_LEVEL_DEBUG));
        e.put("1143", Integer.valueOf(0));
        e.put("1144", Integer.valueOf(1));
        e.put("1145", Integer.valueOf(SimpleLog.LOG_LEVEL_DEBUG));
    }

    public static int a(String str) {
        Integer num = (Integer) e.get(str);
        return num == null ? -1 : num.intValue();
    }

    public static List<String> a(int i) {
        List<String> list = (List) d.get(Integer.valueOf(i));
        if (list != null) {
            return list;
        }
        throw new NullPointerException(new StringBuilder("plz set config for this pageIndex: ").append(i).toString());
    }

    public static int b(String str) {
        if (a.contains(str)) {
            return 0;
        }
        if (b.contains(str)) {
            return 1;
        }
        return c.contains(str) ? SimpleLog.LOG_LEVEL_DEBUG : 0;
    }
}

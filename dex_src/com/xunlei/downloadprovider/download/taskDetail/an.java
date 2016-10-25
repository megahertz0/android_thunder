package com.xunlei.downloadprovider.download.taskDetail;

import java.util.HashMap;
import java.util.Map;

// compiled from: TaskDetailAdReportManager.java
public final class an {
    private static an b;
    Map<Integer, Boolean> a;

    static {
        b = null;
    }

    private an() {
        this.a = null;
        this.a = new HashMap();
    }

    public static an a() {
        if (b == null) {
            b = new an();
        }
        return b;
    }

    public static void b() {
        b = null;
    }

    public final void a(int i) {
        this.a.put(Integer.valueOf(i), Boolean.valueOf(true));
    }

    public final boolean b(int i) {
        Boolean bool = (Boolean) this.a.get(Integer.valueOf(i));
        return bool == null ? false : bool.booleanValue();
    }
}

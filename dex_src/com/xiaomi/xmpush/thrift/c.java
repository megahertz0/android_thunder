package com.xiaomi.xmpush.thrift;

import org.apache.commons.logging.impl.SimpleLog;

public enum c {
    MISC_CONFIG(1),
    PLUGIN_CONFIG(2);
    private final int c;

    static {
        a = new c("MISC_CONFIG", 0, 1);
        b = new c("PLUGIN_CONFIG", 1, 2);
        d = new c[]{a, b};
    }

    private c(int i) {
        this.c = i;
    }

    public static c a(int i) {
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return a;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return b;
            default:
                return null;
        }
    }

    public final int a() {
        return this.c;
    }
}

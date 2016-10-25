package com.xunlei.tdlive.util;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.xiazaibao.BuildConfig;

public enum XLog$b {
    LOG_LEVEL_DEBUG(1),
    LOG_LEVEL_INFO(2),
    LOG_LEVEL_WARN(3),
    LOG_LEVEL_ERROR(4),
    LOG_LEVEL_OFF(5);
    private int f;

    static {
        a = new XLog$b("LOG_LEVEL_DEBUG", 0, 1);
        b = new XLog$b("LOG_LEVEL_INFO", 1, 2);
        c = new XLog$b("LOG_LEVEL_WARN", 2, 3);
        d = new XLog$b("LOG_LEVEL_ERROR", 3, 4);
        e = new XLog$b("LOG_LEVEL_OFF", 4, 5);
        g = new XLog$b[]{a, b, c, d, e};
    }

    private XLog$b(int i) {
        this.f = 0;
        this.f = i;
    }

    public final void a(String str) {
        if (str.equals("e") || str.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
            this.f = d.a();
        } else if (str.equals("w") || str.equals("warn")) {
            this.f = c.a();
        } else if (str.equals("i") || str.equals("info")) {
            this.f = b.a();
        } else if (str.equals("d") || str.equals("debug")) {
            this.f = a.a();
        }
    }

    public final int a() {
        return this.f;
    }

    public final String toString() {
        return a(true);
    }

    public final String a(boolean z) {
        if (a() == d.a()) {
            return z ? "E" : "ERROR";
        } else {
            if (a() == c.a()) {
                return z ? "W" : "WARN";
            } else {
                if (a() == a.a()) {
                    return z ? "D" : "DEBUG";
                } else {
                    if (a() == b.a()) {
                        return z ? "I" : "INFO";
                    } else {
                        return BuildConfig.VERSION_NAME;
                    }
                }
            }
        }
    }
}

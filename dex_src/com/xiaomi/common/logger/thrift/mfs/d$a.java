package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum d$a {
    IP((short) 1, "ip"),
    FAILED_COUNT((short) 2, "failed_count"),
    SUCCESS_COUNT((short) 3, "success_count"),
    DURATION((short) 4, "duration"),
    SIZE((short) 5, "size"),
    EXP_INFO((short) 6, "exp_info"),
    HTTP_INFO((short) 7, "http_info");
    private static final Map<String, d$a> h;
    private final short i;
    private final String j;

    static {
        String str = "ip";
        a = new d$a("IP", 0, (short) 1, "ip");
        String str2 = "failed_count";
        b = new d$a("FAILED_COUNT", 1, (short) 2, "failed_count");
        str2 = "success_count";
        c = new d$a("SUCCESS_COUNT", 2, (short) 3, "success_count");
        str2 = "duration";
        d = new d$a("DURATION", 3, (short) 4, "duration");
        str2 = "size";
        e = new d$a("SIZE", 4, (short) 5, "size");
        str = "exp_info";
        f = new d$a("EXP_INFO", 5, (short) 6, "exp_info");
        String str3 = "http_info";
        g = new d$a("HTTP_INFO", 6, (short) 7, "http_info");
        k = new d$a[]{a, b, c, d, e, f, g};
        h = new HashMap();
        Iterator it = EnumSet.allOf(d$a.class).iterator();
        while (it.hasNext()) {
            d$a com_xiaomi_common_logger_thrift_mfs_d_a = (d$a) it.next();
            h.put(com_xiaomi_common_logger_thrift_mfs_d_a.a(), com_xiaomi_common_logger_thrift_mfs_d_a);
        }
    }

    private d$a(short s, String str) {
        this.i = s;
        this.j = str;
    }

    public final String a() {
        return this.j;
    }
}

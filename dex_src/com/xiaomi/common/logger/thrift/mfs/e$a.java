package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum e$a {
    CONTRY((short) 1, "contry"),
    PROVINCE((short) 2, "province"),
    CITY((short) 3, "city"),
    ISP((short) 4, "isp");
    private static final Map<String, e$a> e;
    private final short f;
    private final String g;

    static {
        String str = "contry";
        a = new e$a("CONTRY", 0, (short) 1, "contry");
        str = "province";
        b = new e$a("PROVINCE", 1, (short) 2, "province");
        str = "city";
        c = new e$a("CITY", 2, (short) 3, "city");
        str = "isp";
        d = new e$a("ISP", 3, (short) 4, "isp");
        h = new e$a[]{a, b, c, d};
        e = new HashMap();
        Iterator it = EnumSet.allOf(e$a.class).iterator();
        while (it.hasNext()) {
            e$a com_xiaomi_common_logger_thrift_mfs_e_a = (e$a) it.next();
            e.put(com_xiaomi_common_logger_thrift_mfs_e_a.a(), com_xiaomi_common_logger_thrift_mfs_e_a);
        }
    }

    private e$a(short s, String str) {
        this.f = s;
        this.g = str;
    }

    public final String a() {
        return this.g;
    }
}

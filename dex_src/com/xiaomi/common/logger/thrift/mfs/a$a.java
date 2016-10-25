package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum a$a {
    HOST((short) 1, "host"),
    LAND_NODE_INFO((short) 2, "land_node_info");
    private static final Map<String, a$a> c;
    private final short d;
    private final String e;

    static {
        String str = "host";
        a = new a$a("HOST", 0, (short) 1, "host");
        str = "land_node_info";
        b = new a$a("LAND_NODE_INFO", 1, (short) 2, "land_node_info");
        f = new a$a[]{a, b};
        c = new HashMap();
        Iterator it = EnumSet.allOf(a$a.class).iterator();
        while (it.hasNext()) {
            a$a com_xiaomi_common_logger_thrift_mfs_a_a = (a$a) it.next();
            c.put(com_xiaomi_common_logger_thrift_mfs_a_a.a(), com_xiaomi_common_logger_thrift_mfs_a_a);
        }
    }

    private a$a(short s, String str) {
        this.d = s;
        this.e = str;
    }

    public final String a() {
        return this.e;
    }
}

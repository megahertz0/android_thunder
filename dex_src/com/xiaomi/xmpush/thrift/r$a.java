package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.agoo.common.AgooConstants;

public enum r$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, AgooConstants.MESSAGE_ID),
    APP_ID((short) 4, "appId"),
    TYPE((short) 5, AgooConstants.MESSAGE_TYPE),
    REQUIRE_ACK((short) 6, "requireAck"),
    PAYLOAD((short) 7, "payload"),
    EXTRA((short) 8, "extra"),
    PACKAGE_NAME((short) 9, "packageName"),
    CATEGORY((short) 10, "category"),
    BINARY_EXTRA((short) 14, "binaryExtra");
    private static final Map<String, r$a> l;
    private final short m;
    private final String n;

    static {
        String str = "debug";
        a = new r$a("DEBUG", 0, (short) 1, "debug");
        String str2 = "target";
        b = new r$a("TARGET", 1, (short) 2, "target");
        str2 = AgooConstants.MESSAGE_ID;
        c = new r$a("ID", 2, (short) 3, AgooConstants.MESSAGE_ID);
        str2 = "appId";
        d = new r$a("APP_ID", 3, (short) 4, "appId");
        str2 = AgooConstants.MESSAGE_TYPE;
        e = new r$a("TYPE", 4, (short) 5, AgooConstants.MESSAGE_TYPE);
        str = "requireAck";
        f = new r$a("REQUIRE_ACK", 5, (short) 6, "requireAck");
        String str3 = "payload";
        g = new r$a("PAYLOAD", 6, (short) 7, "payload");
        str3 = "extra";
        h = new r$a("EXTRA", 7, (short) 8, "extra");
        str3 = "packageName";
        i = new r$a("PACKAGE_NAME", 8, (short) 9, "packageName");
        str3 = "category";
        j = new r$a("CATEGORY", 9, (short) 10, "category");
        str3 = "binaryExtra";
        k = new r$a("BINARY_EXTRA", 10, (short) 14, "binaryExtra");
        o = new r$a[]{a, b, c, d, e, f, g, h, i, j, k};
        l = new HashMap();
        Iterator it = EnumSet.allOf(r$a.class).iterator();
        while (it.hasNext()) {
            r$a com_xiaomi_xmpush_thrift_r_a = (r$a) it.next();
            l.put(com_xiaomi_xmpush_thrift_r_a.a(), com_xiaomi_xmpush_thrift_r_a);
        }
    }

    private r$a(short s, String str) {
        this.m = s;
        this.n = str;
    }

    public final String a() {
        return this.n;
    }
}

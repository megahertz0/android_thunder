package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum j$a {
    CHANNEL_ID((short) 1, "channelId"),
    USER_ID((short) 2, "userId"),
    SERVER((short) 3, "server"),
    RESOURCE((short) 4, "resource"),
    IS_PREVIEW((short) 5, "isPreview");
    private static final Map<String, j$a> f;
    private final short g;
    private final String h;

    static {
        String str = "channelId";
        a = new j$a("CHANNEL_ID", 0, (short) 1, "channelId");
        str = "userId";
        b = new j$a("USER_ID", 1, (short) 2, "userId");
        str = "server";
        c = new j$a("SERVER", 2, (short) 3, "server");
        str = "resource";
        d = new j$a("RESOURCE", 3, (short) 4, "resource");
        String str2 = "isPreview";
        e = new j$a("IS_PREVIEW", 4, (short) 5, "isPreview");
        i = new j$a[]{a, b, c, d, e};
        f = new HashMap();
        Iterator it = EnumSet.allOf(j$a.class).iterator();
        while (it.hasNext()) {
            j$a com_xiaomi_xmpush_thrift_j_a = (j$a) it.next();
            f.put(com_xiaomi_xmpush_thrift_j_a.a(), com_xiaomi_xmpush_thrift_j_a);
        }
    }

    private j$a(short s, String str) {
        this.g = s;
        this.h = str;
    }

    public final String a() {
        return this.h;
    }
}

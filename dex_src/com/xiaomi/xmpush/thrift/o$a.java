package com.xiaomi.xmpush.thrift;

import com.umeng.socialize.utils.OauthHelper;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum o$a {
    ACTION((short) 1, "action"),
    ENCRYPT_ACTION((short) 2, "encryptAction"),
    IS_REQUEST((short) 3, "isRequest"),
    PUSH_ACTION((short) 4, "pushAction"),
    APPID((short) 5, OauthHelper.APP_ID),
    PACKAGE_NAME((short) 6, "packageName"),
    TARGET((short) 7, "target"),
    META_INFO((short) 8, "metaInfo");
    private static final Map<String, o$a> i;
    private final short j;
    private final String k;

    static {
        String str = "action";
        a = new o$a("ACTION", 0, (short) 1, "action");
        String str2 = "encryptAction";
        b = new o$a("ENCRYPT_ACTION", 1, (short) 2, "encryptAction");
        str2 = "isRequest";
        c = new o$a("IS_REQUEST", 2, (short) 3, "isRequest");
        str2 = "pushAction";
        d = new o$a("PUSH_ACTION", 3, (short) 4, "pushAction");
        str2 = OauthHelper.APP_ID;
        e = new o$a("APPID", 4, (short) 5, OauthHelper.APP_ID);
        str = "packageName";
        f = new o$a("PACKAGE_NAME", 5, (short) 6, "packageName");
        String str3 = "target";
        g = new o$a("TARGET", 6, (short) 7, "target");
        str3 = "metaInfo";
        h = new o$a("META_INFO", 7, (short) 8, "metaInfo");
        l = new o$a[]{a, b, c, d, e, f, g, h};
        i = new HashMap();
        Iterator it = EnumSet.allOf(o$a.class).iterator();
        while (it.hasNext()) {
            o$a com_xiaomi_xmpush_thrift_o_a = (o$a) it.next();
            i.put(com_xiaomi_xmpush_thrift_o_a.a(), com_xiaomi_xmpush_thrift_o_a);
        }
    }

    private o$a(short s, String str) {
        this.j = s;
        this.k = str;
    }

    public final String a() {
        return this.k;
    }
}

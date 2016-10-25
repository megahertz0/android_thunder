package com.xiaomi.common.logger.thrift.mfs;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum b$a {
    CATEGORY((short) 1, "category"),
    UUID((short) 2, "uuid"),
    VERSION((short) 3, "version"),
    NETWORK((short) 4, "network"),
    CLIENT_IP((short) 5, "client_ip"),
    LOCATION((short) 6, "location"),
    HOST_INFO((short) 7, "host_info"),
    VERSION_TYPE((short) 8, "version_type"),
    APP_NAME((short) 9, SocializeProtocolConstants.PROTOCOL_KEY_APP_NAME),
    APP_VERSION((short) 10, "app_version");
    private static final Map<String, b$a> k;
    private final short l;
    private final String m;

    static {
        String str = "category";
        a = new b$a("CATEGORY", 0, (short) 1, "category");
        String str2 = "uuid";
        b = new b$a("UUID", 1, (short) 2, "uuid");
        str2 = "version";
        c = new b$a("VERSION", 2, (short) 3, "version");
        str2 = "network";
        d = new b$a("NETWORK", 3, (short) 4, "network");
        str2 = "client_ip";
        e = new b$a("CLIENT_IP", 4, (short) 5, "client_ip");
        str = "location";
        f = new b$a("LOCATION", 5, (short) 6, "location");
        String str3 = "host_info";
        g = new b$a("HOST_INFO", 6, (short) 7, "host_info");
        str3 = "version_type";
        h = new b$a("VERSION_TYPE", 7, (short) 8, "version_type");
        str3 = SocializeProtocolConstants.PROTOCOL_KEY_APP_NAME;
        i = new b$a("APP_NAME", 8, (short) 9, SocializeProtocolConstants.PROTOCOL_KEY_APP_NAME);
        str3 = "app_version";
        j = new b$a("APP_VERSION", 9, (short) 10, "app_version");
        n = new b$a[]{a, b, c, d, e, f, g, h, i, j};
        k = new HashMap();
        Iterator it = EnumSet.allOf(b$a.class).iterator();
        while (it.hasNext()) {
            b$a com_xiaomi_common_logger_thrift_mfs_b_a = (b$a) it.next();
            k.put(com_xiaomi_common_logger_thrift_mfs_b_a.a(), com_xiaomi_common_logger_thrift_mfs_b_a);
        }
    }

    private b$a(short s, String str) {
        this.l = s;
        this.m = str;
    }

    public final String a() {
        return this.m;
    }
}

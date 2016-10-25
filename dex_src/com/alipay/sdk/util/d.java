package com.alipay.sdk.util;

import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

public enum d {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NONE(-1, IXAdSystemUtils.NT_NONE);
    public String p;
    private int q;

    static {
        String str = "WIFI";
        a = new d("WIFI", 0, 0, "WIFI");
        str = "unicom2G";
        b = new d("NETWORK_TYPE_1", 1, 1, "unicom2G");
        str = "mobile2G";
        c = new d("NETWORK_TYPE_2", 2, 2, "mobile2G");
        str = "telecom2G";
        d = new d("NETWORK_TYPE_4", 3, 4, "telecom2G");
        String str2 = "telecom3G";
        e = new d("NETWORK_TYPE_5", 4, 5, "telecom3G");
        String str3 = "telecom3G";
        f = new d("NETWORK_TYPE_6", 5, 6, "telecom3G");
        str3 = "telecom3G";
        g = new d("NETWORK_TYPE_12", 6, 12, "telecom3G");
        str3 = "unicom3G";
        h = new d("NETWORK_TYPE_8", 7, 8, "unicom3G");
        str2 = "unicom3G";
        i = new d("NETWORK_TYPE_3", 8, 3, "unicom3G");
        str3 = "LTE";
        j = new d("NETWORK_TYPE_13", 9, 13, "LTE");
        str3 = "IDEN";
        k = new d("NETWORK_TYPE_11", 10, 11, "IDEN");
        str3 = "HSUPA";
        l = new d("NETWORK_TYPE_9", 11, 9, "HSUPA");
        str3 = "HSPA";
        m = new d("NETWORK_TYPE_10", 12, 10, "HSPA");
        str3 = "HSPAP";
        n = new d("NETWORK_TYPE_15", 13, 15, "HSPAP");
        str3 = IXAdSystemUtils.NT_NONE;
        o = new d("NONE", 14, -1, IXAdSystemUtils.NT_NONE);
        r = new d[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o};
    }

    private d(int i, String str) {
        this.q = i;
        this.p = str;
    }

    private int a() {
        return this.q;
    }

    private String b() {
        return this.p;
    }

    public static d a(int i) {
        d[] values = values();
        int length = values.length;
        for (int i2 = 0; i2 < length; i2++) {
            d dVar = values[i2];
            if (dVar.q == i) {
                return dVar;
            }
        }
        return o;
    }
}

package com.tencent.mm.sdk.b;

import java.util.TimeZone;

public final class h {
    private static final TimeZone GMT;
    public static final long[] bh;
    private static final long[] bi;
    private static final char[] bj;
    private static final String[] bk;

    static {
        bh = new long[]{300, 200, 300, 200};
        bi = new long[]{300, 50, 300, 50};
        GMT = TimeZone.getTimeZone("GMT");
        bj = new char[]{'<', '>', '\"', '\'', '&', '\r', '\n', ' ', '\t'};
        bk = new String[]{"&lt;", "&gt;", "&quot;", "&apos;", "&amp;", "&#x0D;", "&#x0A;", "&#x20;", "&#x09;"};
    }

    public static boolean h(String str) {
        return str == null || str.length() <= 0;
    }

    public static f u() {
        return new f();
    }
}

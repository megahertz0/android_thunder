package com.tencent.stat.a;

public enum f {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(1000),
    ADDITION(1001),
    MONITOR_STAT(1002),
    MTA_GAME_USER(1003),
    NETWORK_MONITOR(1004);
    private int i;

    static {
        a = new f("PAGE_VIEW", 0, 1);
        b = new f("SESSION_ENV", 1, 2);
        c = new f("ERROR", 2, 3);
        d = new f("CUSTOM", 3, 1000);
        e = new f("ADDITION", 4, 1001);
        f = new f("MONITOR_STAT", 5, 1002);
        g = new f("MTA_GAME_USER", 6, 1003);
        h = new f("NETWORK_MONITOR", 7, 1004);
        j = new f[]{a, b, c, d, e, f, g, h};
    }

    private f(int i) {
        this.i = i;
    }

    public final int a() {
        return this.i;
    }
}

package com.xiaomi.smack.packet;

public enum f$a {
    chat,
    available,
    away,
    xa,
    dnd;

    static {
        a = new f$a("chat", 0);
        b = new f$a("available", 1);
        c = new f$a("away", 2);
        d = new f$a("xa", 3);
        e = new f$a("dnd", 4);
        f = new f$a[]{a, b, c, d, e};
    }
}

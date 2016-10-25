package com.xiaomi.smack.packet;

public class h$a {
    public static final h$a a;
    public static final h$a b;
    public static final h$a c;
    public static final h$a d;
    public static final h$a e;
    public static final h$a f;
    public static final h$a g;
    public static final h$a h;
    public static final h$a i;
    public static final h$a j;
    public static final h$a k;
    public static final h$a l;
    public static final h$a m;
    public static final h$a n;
    public static final h$a o;
    public static final h$a p;
    public static final h$a q;
    public static final h$a r;
    public static final h$a s;
    public static final h$a t;
    public static final h$a u;
    public static final h$a v;
    public static final h$a w;
    public static final h$a x;
    private String y;

    static {
        a = new h$a("internal-server-error");
        b = new h$a("forbidden");
        c = new h$a("bad-request");
        d = new h$a("conflict");
        e = new h$a("feature-not-implemented");
        f = new h$a("gone");
        g = new h$a("item-not-found");
        h = new h$a("jid-malformed");
        i = new h$a("not-acceptable");
        j = new h$a("not-allowed");
        k = new h$a("not-authorized");
        l = new h$a("payment-required");
        m = new h$a("recipient-unavailable");
        n = new h$a("redirect");
        o = new h$a("registration-required");
        p = new h$a("remote-server-error");
        q = new h$a("remote-server-not-found");
        r = new h$a("remote-server-timeout");
        s = new h$a("resource-constraint");
        t = new h$a("service-unavailable");
        u = new h$a("subscription-required");
        v = new h$a("undefined-condition");
        w = new h$a("unexpected-request");
        x = new h$a("request-timeout");
    }

    public h$a(String str) {
        this.y = str;
    }

    public String toString() {
        return this.y;
    }
}

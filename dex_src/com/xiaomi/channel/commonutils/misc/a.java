package com.xiaomi.channel.commonutils.misc;

public class a {
    public static final boolean a;
    public static final boolean b;
    public static final boolean c;
    public static final boolean d;
    public static boolean e;
    public static final boolean f;
    public static final boolean g;
    private static int h;

    static {
        boolean z = false;
        boolean contains = "@SHIP.TO.2A2FE0D7@".contains("2A2FE0D7");
        a = contains;
        contains = contains || "DEBUG".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
        b = contains;
        c = "LOGABLE".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
        d = "@SHIP.TO.2A2FE0D7@".contains("YY");
        e = "@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("TEST");
        f = "BETA".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
        if ("@SHIP.TO.2A2FE0D7@".startsWith("RC")) {
            z = true;
        }
        g = z;
        h = 1;
        if ("@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("SANDBOX")) {
            h = 2;
        } else if ("@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("ONEBOX")) {
            h = 3;
        } else {
            h = 1;
        }
    }

    public static void a(int i) {
        h = i;
    }

    public static boolean a() {
        return h == 2;
    }

    public static boolean b() {
        return h == 3;
    }

    public static int c() {
        return h;
    }
}

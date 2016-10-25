package com.xiaomi.network;

import java.net.InetSocketAddress;

public final class Host {
    private String a;
    private int b;

    public Host(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public static Host a(String str, int i) {
        int lastIndexOf = str.lastIndexOf(":");
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            try {
                lastIndexOf = Integer.parseInt(str.substring(lastIndexOf + 1));
                if (lastIndexOf > 0) {
                    i = lastIndexOf;
                }
                str = substring;
            } catch (NumberFormatException e) {
                str = substring;
            }
        }
        return new Host(str, i);
    }

    public static InetSocketAddress b(String str, int i) {
        Host a = a(str, i);
        return new InetSocketAddress(a.b(), a.a());
    }

    public final int a() {
        return this.b;
    }

    public final String b() {
        return this.a;
    }

    public final String toString() {
        return this.b > 0 ? this.a + ":" + this.b : this.a;
    }
}

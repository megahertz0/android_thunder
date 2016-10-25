package com.xunlei.downloadprovider.player;

// compiled from: PlayerIdGenerator.java
public final class aa {
    private static int a;

    static {
        a = 10000;
    }

    public static int a() {
        int i = a + 1;
        a = i;
        return i;
    }
}

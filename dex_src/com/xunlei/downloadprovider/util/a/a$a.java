package com.xunlei.downloadprovider.util.a;

// compiled from: ConfigureManager.java
public class a$a {
    public static long a() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        return currentTimeMillis - (currentTimeMillis % 3600);
    }
}

package com.xunlei.downloadprovider.frame.advertisement.b.a;

// compiled from: LaunchTimeReportHelper.java
public final class a {
    private static a f;
    public long a;
    public boolean b;
    public long c;
    public long d;
    public long e;

    private a() {
    }

    public static a a() {
        if (f == null) {
            f = new a();
        }
        return f;
    }

    public final long b() {
        return System.currentTimeMillis() - this.a;
    }
}

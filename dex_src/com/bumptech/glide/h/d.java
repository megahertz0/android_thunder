package com.bumptech.glide.h;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.SystemClock;

// compiled from: LogTime.java
public final class d {
    private static final double a;

    static {
        double d = 1.0d;
        if (17 <= VERSION.SDK_INT) {
            d = 1.0d / Math.pow(10.0d, 6.0d);
        }
        a = d;
    }

    @TargetApi(17)
    public static long a() {
        return 17 <= VERSION.SDK_INT ? SystemClock.elapsedRealtimeNanos() : System.currentTimeMillis();
    }

    public static double a(long j) {
        return ((double) (a() - j)) * a;
    }
}

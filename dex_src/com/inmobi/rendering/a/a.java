package com.inmobi.rendering.a;

import android.os.SystemClock;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: Click.java
public final class a {
    int a;
    public String b;
    public Map<String, String> c;
    long d;
    long e;
    int f;
    AtomicBoolean g;
    boolean h;
    boolean i;

    public a(String str, boolean z, boolean z2, int i) {
        this(new Random().nextInt() & Integer.MAX_VALUE, str, new HashMap(), z, z2, i, System.currentTimeMillis(), SystemClock.elapsedRealtime());
    }

    public a(String str, Map<String, String> map, boolean z, boolean z2, int i) {
        this(new Random().nextInt() & Integer.MAX_VALUE, str, map, z, z2, i, System.currentTimeMillis(), SystemClock.elapsedRealtime());
    }

    public a(int i, String str, boolean z, boolean z2, int i2, long j, long j2) {
        this(i, str, new HashMap(), z, z2, i2, j, j2);
    }

    public a(int i, String str, Map<String, String> map, boolean z, boolean z2, int i2, long j, long j2) {
        this.a = i;
        this.b = str;
        this.c = map;
        this.d = j;
        this.e = j2;
        this.f = i2;
        this.g = new AtomicBoolean(false);
        this.i = z;
        this.h = z2;
    }

    public final boolean a(long j) {
        return SystemClock.elapsedRealtime() - this.e > 1000 * j;
    }
}

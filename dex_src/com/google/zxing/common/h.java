package com.google.zxing.common;

import com.google.zxing.j;

// compiled from: GridSampler.java
public abstract class h {
    private static h a;

    public abstract b a(b bVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws j;

    public abstract b a(b bVar, int i, int i2, j jVar) throws j;

    static {
        a = new e();
    }

    public static h a() {
        return a;
    }
}

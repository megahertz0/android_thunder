package com.google.zxing.common;

// compiled from: PerspectiveTransform.java
public final class j {
    final float a;
    final float b;
    final float c;
    final float d;
    final float e;
    final float f;
    final float g;
    final float h;
    final float i;

    private j(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.a = f;
        this.b = f4;
        this.c = f7;
        this.d = f2;
        this.e = f5;
        this.f = f8;
        this.g = f3;
        this.h = f6;
        this.i = f9;
    }

    private static j a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = ((f - f3) + f5) - f7;
        float f10 = ((f2 - f4) + f6) - f8;
        if (f9 == 0.0f && f10 == 0.0f) {
            return new j(f3 - f, f5 - f3, f, f4 - f2, f6 - f4, f2, 0.0f, 0.0f, 1.0f);
        }
        float f11 = f3 - f5;
        float f12 = f7 - f5;
        float f13 = f4 - f6;
        float f14 = f8 - f6;
        float f15 = (f11 * f14) - (f12 * f13);
        float f16 = ((f14 * f9) - (f12 * f10)) / f15;
        float f17 = ((f10 * f11) - (f9 * f13)) / f15;
        return new j((f3 - f) + (f16 * f3), (f7 - f) + (f17 * f7), f, (f16 * f4) + (f4 - f2), (f17 * f8) + (f8 - f2), f2, f16, f17, 1.0f);
    }

    public static j a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        j a = a(f, f2, f3, f4, f5, f6, f7, f8);
        j jVar = new j((a.e * a.i) - (a.f * a.h), (a.f * a.g) - (a.d * a.i), (a.d * a.h) - (a.e * a.g), (a.c * a.h) - (a.b * a.i), (a.a * a.i) - (a.c * a.g), (a.b * a.g) - (a.a * a.h), (a.b * a.f) - (a.c * a.e), (a.c * a.d) - (a.a * a.f), (a.a * a.e) - (a.d * a.b));
        a = a(f9, f10, f11, f12, f13, f14, f15, f16);
        return new j(((a.a * jVar.a) + (a.d * jVar.b)) + (a.g * jVar.c), ((a.a * jVar.d) + (a.d * jVar.e)) + (a.g * jVar.f), ((a.a * jVar.g) + (a.d * jVar.h)) + (a.g * jVar.i), ((a.b * jVar.a) + (a.e * jVar.b)) + (a.h * jVar.c), ((a.b * jVar.d) + (a.e * jVar.e)) + (a.h * jVar.f), ((a.b * jVar.g) + (a.e * jVar.h)) + (a.h * jVar.i), ((a.c * jVar.a) + (a.f * jVar.b)) + (a.i * jVar.c), ((a.c * jVar.d) + (a.f * jVar.e)) + (a.i * jVar.f), ((a.c * jVar.g) + (a.f * jVar.h)) + (jVar.i * a.i));
    }
}

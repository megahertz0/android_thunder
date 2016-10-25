package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class p extends LinearInterpolator {
    final /* synthetic */ float a;
    final /* synthetic */ float b;

    p(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final float getInterpolation(float f) {
        double asin = (((double) this.a) / 6.283185307179586d) * Math.asin((double) (1.0f / this.b));
        float f2 = 2.0f * f;
        if (f2 < 1.0f) {
            f2 -= 1.0f;
            return (float) ((Math.sin(((((double) f2) - asin) * 6.283185307179586d) / ((double) this.a)) * (((double) this.b) * Math.pow(2.0d, (double) (10.0f * f2)))) * -0.5d);
        }
        f2 -= 1.0f;
        return (float) (((Math.sin(((((double) f2) - asin) * 6.283185307179586d) / ((double) this.a)) * (((double) this.b) * Math.pow(2.0d, (double) (-10.0f * f2)))) * 0.5d) + 1.0d);
    }
}

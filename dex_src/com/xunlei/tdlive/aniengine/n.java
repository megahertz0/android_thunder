package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class n extends LinearInterpolator {
    final /* synthetic */ float a;
    final /* synthetic */ float b;

    n(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final float getInterpolation(float f) {
        if (f == 0.0f || f == 1.0f) {
            return f;
        }
        return (float) ((Math.sin(((((double) f) - ((((double) this.a) / 6.283185307179586d) * Math.asin((double) (1.0f / this.b)))) * 6.283185307179586d) / ((double) this.a)) * (((double) this.b) * Math.pow(2.0d, (double) (-10.0f * f)))) + 1.0d);
    }
}

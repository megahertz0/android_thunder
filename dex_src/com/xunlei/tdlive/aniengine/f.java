package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class f extends LinearInterpolator {
    final /* synthetic */ float a;

    f(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        float f2 = f * 2.0f;
        if (f2 < 1.0f) {
            return (float) (((double) (((f2 * (this.a + 1.0f)) - this.a) * (f2 * f2))) * 0.5d);
        }
        f2 -= 2.0f;
        return (float) (((double) ((((f2 * (this.a + 1.0f)) + this.a) * (f2 * f2)) + 2.0f)) * 0.5d);
    }
}

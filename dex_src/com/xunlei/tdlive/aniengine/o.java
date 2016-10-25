package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class o extends LinearInterpolator {
    final /* synthetic */ float a;

    o(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        if (this.a == 0.0f) {
            return f;
        }
        return this.a < 0.0f ? f * ((((-this.a) * f) + 1.0f) + this.a) : f * (((2.0f - f) * this.a) + (1.0f - this.a));
    }
}

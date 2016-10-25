package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class s extends LinearInterpolator {
    final /* synthetic */ float a;

    s(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        float f2 = f * 2.0f;
        return f2 < 1.0f ? (float) (Math.pow((double) f2, (double) this.a) * 0.5d) : (float) (1.0d - (Math.abs(Math.pow((double) (2.0f - f2), (double) this.a)) * 0.5d));
    }
}

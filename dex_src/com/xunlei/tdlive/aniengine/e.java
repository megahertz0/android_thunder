package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class e extends LinearInterpolator {
    final /* synthetic */ float a;

    e(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (((f2 * (this.a + 1.0f)) + this.a) * (f2 * f2)) + 1.0f;
    }
}

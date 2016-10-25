package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class r extends LinearInterpolator {
    final /* synthetic */ float a;

    r(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        return (float) (1.0d - Math.pow((double) (1.0f - f), (double) this.a));
    }
}

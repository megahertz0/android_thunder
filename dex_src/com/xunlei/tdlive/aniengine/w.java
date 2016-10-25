package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class w extends LinearInterpolator {
    final /* synthetic */ float a;

    w(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        return (f * f) * (((this.a + 1.0f) * f) - this.a);
    }
}

package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class d extends LinearInterpolator {
    final /* synthetic */ float a;

    d(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        return this.a * f;
    }
}

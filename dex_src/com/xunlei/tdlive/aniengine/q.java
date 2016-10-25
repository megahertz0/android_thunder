package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class q extends LinearInterpolator {
    final /* synthetic */ float a;

    q(float f) {
        this.a = f;
    }

    public final float getInterpolation(float f) {
        return (float) Math.pow((double) f, (double) this.a);
    }
}

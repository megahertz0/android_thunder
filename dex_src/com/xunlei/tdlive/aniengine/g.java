package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class g extends LinearInterpolator {
    g() {
    }

    public final float getInterpolation(float f) {
        return (float) (-(Math.sqrt((double) (1.0f - (f * f))) - 1.0d));
    }
}

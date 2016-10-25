package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class v extends LinearInterpolator {
    v() {
    }

    public final float getInterpolation(float f) {
        return (float) (-0.5d * (Math.cos(((double) f) * 3.141592653589793d) - 1.0d));
    }
}

package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class u extends LinearInterpolator {
    u() {
    }

    public final float getInterpolation(float f) {
        return (float) Math.sin((((double) f) * 3.141592653589793d) / 2.0d);
    }
}

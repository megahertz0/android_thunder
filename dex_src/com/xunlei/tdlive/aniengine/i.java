package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class i extends LinearInterpolator {
    i() {
    }

    public final float getInterpolation(float f) {
        float f2 = f * 2.0f;
        if (f2 < 1.0f) {
            return (float) ((Math.sqrt((double) (1.0f - (f2 * f2))) - 1.0d) * -0.5d);
        }
        f2 -= 2.0f;
        return (float) ((Math.sqrt((double) (1.0f - (f2 * f2))) + 1.0d) * 0.5d);
    }
}

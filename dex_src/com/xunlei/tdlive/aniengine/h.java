package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class h extends LinearInterpolator {
    h() {
    }

    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (float) Math.sqrt((double) (1.0f - (f2 * f2)));
    }
}

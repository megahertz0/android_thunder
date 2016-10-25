package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class l extends LinearInterpolator {
    l() {
    }

    public final float getInterpolation(float f) {
        return ((double) f) < 0.5d ? c.B.getInterpolation(f * 2.0f) * 0.5f : (c.A.getInterpolation((f * 2.0f) - 1.0f) * 0.5f) + 0.5f;
    }
}

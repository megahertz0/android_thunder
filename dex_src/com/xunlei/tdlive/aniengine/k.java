package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class k extends LinearInterpolator {
    k() {
    }

    public final float getInterpolation(float f) {
        return 1.0f - c.A.getInterpolation(1.0f - f);
    }
}

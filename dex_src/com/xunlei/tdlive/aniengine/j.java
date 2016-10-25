package com.xunlei.tdlive.aniengine;

import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
final class j extends LinearInterpolator {
    j() {
    }

    public final float getInterpolation(float f) {
        if (((double) f) < 0.36363636363636365d) {
            return (float) ((((double) f) * 7.5625d) * ((double) f));
        }
        float f2;
        if (((double) f) < 0.7272727272727273d) {
            f2 = (float) (((double) f) - 0.5454545454545454d);
            return (float) ((((double) f2) * (((double) f2) * 7.5625d)) + 0.75d);
        } else if (((double) f) < 0.9090909090909091d) {
            f2 = (float) (((double) f) - 0.8181818181818182d);
            return (float) ((((double) f2) * (((double) f2) * 7.5625d)) + 0.9375d);
        } else {
            f2 = (float) (((double) f) - 0.9545454545454546d);
            return (float) ((((double) f2) * (((double) f2) * 7.5625d)) + 0.984375d);
        }
    }
}

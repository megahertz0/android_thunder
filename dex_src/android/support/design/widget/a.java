package android.support.design.widget;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

// compiled from: AnimationUtils.java
final class a {
    static final Interpolator a;
    static final Interpolator b;
    static final Interpolator c;
    static final Interpolator d;
    static final Interpolator e;

    // compiled from: AnimationUtils.java
    static class a implements AnimationListener {
        a() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    static {
        a = new LinearInterpolator();
        b = new FastOutSlowInInterpolator();
        c = new FastOutLinearInInterpolator();
        d = new LinearOutSlowInInterpolator();
        e = new DecelerateInterpolator();
    }

    static float a(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    static int a(int i, int i2, float f) {
        return Math.round(((float) (i2 - i)) * f) + i;
    }
}

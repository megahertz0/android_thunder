package android.support.v7.widget;

import android.view.animation.Interpolator;

// compiled from: RecyclerView.java
final class bc implements Interpolator {
    bc() {
    }

    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
    }
}

package android.support.design.widget;

import android.animation.ValueAnimator;
import android.view.animation.Interpolator;

// compiled from: ValueAnimatorCompatImplHoneycombMr1.java
final class bk extends e {
    final ValueAnimator a;

    bk() {
        this.a = new ValueAnimator();
    }

    public final void a() {
        this.a.start();
    }

    public final boolean b() {
        return this.a.isRunning();
    }

    public final void a(Interpolator interpolator) {
        this.a.setInterpolator(interpolator);
    }

    public final void a(b bVar) {
        this.a.addUpdateListener(new bl(this, bVar));
    }

    public final void a(a aVar) {
        this.a.addListener(new bm(this, aVar));
    }

    public final void a(int i, int i2) {
        this.a.setIntValues(new int[]{i, i2});
    }

    public final int c() {
        return ((Integer) this.a.getAnimatedValue()).intValue();
    }

    public final void a(float f, float f2) {
        this.a.setFloatValues(new float[]{f, f2});
    }

    public final float d() {
        return ((Float) this.a.getAnimatedValue()).floatValue();
    }

    public final void a(int i) {
        this.a.setDuration((long) i);
    }

    public final void e() {
        this.a.cancel();
    }

    public final float f() {
        return this.a.getAnimatedFraction();
    }

    public final long g() {
        return this.a.getDuration();
    }
}

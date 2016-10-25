package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

// compiled from: ValueAnimatorCompatImplEclairMr1.java
final class bi extends e {
    private static final Handler a;
    private long b;
    private boolean c;
    private final int[] d;
    private final float[] e;
    private int f;
    private Interpolator g;
    private a h;
    private b i;
    private float j;
    private final Runnable k;

    bi() {
        this.d = new int[2];
        this.e = new float[2];
        this.f = 200;
        this.k = new bj(this);
    }

    static {
        a = new Handler(Looper.getMainLooper());
    }

    public final void a() {
        if (!this.c) {
            if (this.g == null) {
                this.g = new AccelerateDecelerateInterpolator();
            }
            this.b = SystemClock.uptimeMillis();
            this.c = true;
            a.postDelayed(this.k, 10);
        }
    }

    public final boolean b() {
        return this.c;
    }

    public final void a(Interpolator interpolator) {
        this.g = interpolator;
    }

    public final void a(a aVar) {
        this.h = aVar;
    }

    public final void a(b bVar) {
        this.i = bVar;
    }

    public final void a(int i, int i2) {
        this.d[0] = i;
        this.d[1] = i2;
    }

    public final int c() {
        return a.a(this.d[0], this.d[1], this.j);
    }

    public final void a(float f, float f2) {
        this.e[0] = f;
        this.e[1] = f2;
    }

    public final float d() {
        return a.a(this.e[0], this.e[1], this.j);
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void e() {
        this.c = false;
        a.removeCallbacks(this.k);
    }

    public final float f() {
        return this.j;
    }

    public final long g() {
        return (long) this.f;
    }

    static /* synthetic */ void a(bi biVar) {
        if (biVar.c) {
            float uptimeMillis = ((float) (SystemClock.uptimeMillis() - biVar.b)) / ((float) biVar.f);
            if (biVar.g != null) {
                uptimeMillis = biVar.g.getInterpolation(uptimeMillis);
            }
            biVar.j = uptimeMillis;
            if (biVar.i != null) {
                biVar.i.a();
            }
            if (SystemClock.uptimeMillis() >= biVar.b + ((long) biVar.f)) {
                biVar.c = false;
                if (biVar.h != null) {
                    biVar.h.a();
                }
            }
        }
        if (biVar.c) {
            a.postDelayed(biVar.k, 10);
        }
    }
}

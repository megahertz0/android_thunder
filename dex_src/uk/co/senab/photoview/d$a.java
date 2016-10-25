package uk.co.senab.photoview;

import android.view.View;

// compiled from: PhotoViewAttacher.java
private class d$a implements Runnable {
    final /* synthetic */ d a;
    private final float b;
    private final float c;
    private final long d;
    private final float e;
    private final float f;

    public d$a(d dVar, float f, float f2, float f3, float f4) {
        this.a = dVar;
        this.b = f3;
        this.c = f4;
        this.d = System.currentTimeMillis();
        this.e = f;
        this.f = f2;
    }

    public final void run() {
        View c = this.a.c();
        if (c != null) {
            float interpolation = a.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.d)) * 1.0f) / ((float) this.a.b)));
            float d = (this.e + ((this.f - this.e) * interpolation)) / this.a.d();
            d.b(this.a).postScale(d, d, this.b, this.c);
            d.c(this.a);
            if (interpolation < 1.0f) {
                a.a(c, this);
            }
        }
    }
}

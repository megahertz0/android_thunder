package android.support.design.widget;

import android.view.animation.Interpolator;

// compiled from: ValueAnimatorCompat.java
final class bf {
    final e a;

    // compiled from: ValueAnimatorCompat.java
    static interface c {
        void a(bf bfVar);
    }

    // compiled from: ValueAnimatorCompat.java
    static interface a {
        void a();
    }

    // compiled from: ValueAnimatorCompat.java
    static class b implements a {
        b() {
        }

        public void a() {
        }
    }

    // compiled from: ValueAnimatorCompat.java
    static interface d {
        bf a();
    }

    // compiled from: ValueAnimatorCompat.java
    static abstract class e {

        // compiled from: ValueAnimatorCompat.java
        static interface a {
            void a();
        }

        // compiled from: ValueAnimatorCompat.java
        static interface b {
            void a();
        }

        abstract void a();

        abstract void a(float f, float f2);

        abstract void a(int i);

        abstract void a(int i, int i2);

        abstract void a(a aVar);

        abstract void a(b bVar);

        abstract void a(Interpolator interpolator);

        abstract boolean b();

        abstract int c();

        abstract float d();

        abstract void e();

        abstract float f();

        abstract long g();

        e() {
        }
    }

    bf(e eVar) {
        this.a = eVar;
    }

    public final void a(Interpolator interpolator) {
        this.a.a(interpolator);
    }

    public final void a(c cVar) {
        this.a.a(new bg(this, cVar));
    }

    public final void a(int i, int i2) {
        this.a.a(i, i2);
    }

    public final void a(float f, float f2) {
        this.a.a(f, f2);
    }

    public final void a(int i) {
        this.a.a(i);
    }
}

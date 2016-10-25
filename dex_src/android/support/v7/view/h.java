package android.support.v7.view;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: ViewPropertyAnimatorCompatSet.java
public final class h {
    public final ArrayList<ViewPropertyAnimatorCompat> a;
    ViewPropertyAnimatorListener b;
    boolean c;
    private long d;
    private Interpolator e;
    private final ViewPropertyAnimatorListenerAdapter f;

    public h() {
        this.d = -1;
        this.f = new i(this);
        this.a = new ArrayList();
    }

    public final h a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.c) {
            this.a.add(viewPropertyAnimatorCompat);
        }
        return this;
    }

    public final void a() {
        if (!this.c) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) it.next();
                if (this.d >= 0) {
                    viewPropertyAnimatorCompat.setDuration(this.d);
                }
                if (this.e != null) {
                    viewPropertyAnimatorCompat.setInterpolator(this.e);
                }
                if (this.b != null) {
                    viewPropertyAnimatorCompat.setListener(this.f);
                }
                viewPropertyAnimatorCompat.start();
            }
            this.c = true;
        }
    }

    public final void b() {
        if (this.c) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((ViewPropertyAnimatorCompat) it.next()).cancel();
            }
            this.c = false;
        }
    }

    public final h c() {
        if (!this.c) {
            this.d = 250;
        }
        return this;
    }

    public final h a(Interpolator interpolator) {
        if (!this.c) {
            this.e = interpolator;
        }
        return this;
    }

    public final h a(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.c) {
            this.b = viewPropertyAnimatorListener;
        }
        return this;
    }
}

package com.xunlei.tdlive.aniengine;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

// compiled from: Ease.java
public class c {
    public static Interpolator A;
    public static Interpolator B;
    public static Interpolator C;
    public static Interpolator D;
    public static Interpolator E;
    public static Interpolator F;
    public static Interpolator a;
    public static Interpolator b;
    public static Interpolator c;
    public static Interpolator d;
    public static Interpolator e;
    public static Interpolator f;
    public static Interpolator g;
    public static Interpolator h;
    public static Interpolator i;
    public static Interpolator j;
    public static Interpolator k;
    public static Interpolator l;
    public static Interpolator m;
    public static Interpolator n;
    public static Interpolator o;
    public static Interpolator p;
    public static Interpolator q;
    public static Interpolator r;
    public static Interpolator s;
    public static Interpolator t;
    public static Interpolator u;
    public static Interpolator v;
    public static Interpolator w;
    public static Interpolator x;
    public static Interpolator y;
    public static Interpolator z;

    static {
        a = new LinearInterpolator();
        b = new LinearInterpolator();
        c = new AccelerateInterpolator();
        d = new DecelerateInterpolator();
        e = new AccelerateDecelerateInterpolator();
        f = d(2.0f);
        g = e(2.0f);
        h = f(2.0f);
        i = d(3.0f);
        j = e(3.0f);
        k = f(3.0f);
        l = d(4.0f);
        m = e(4.0f);
        n = f(4.0f);
        o = d(5.0f);
        p = e(5.0f);
        q = f(5.0f);
        r = new t();
        s = new u();
        t = new v();
        u = g(1.7f);
        v = h(1.7f);
        w = i(1.7f);
        x = new g();
        y = new h();
        z = new i();
        A = new j();
        B = new k();
        C = new l();
        D = a(1.0f, 0.3f);
        E = b(1.0f, 0.3f);
        F = c(1.0f, 0.45000002f);
    }

    public static Interpolator a(float f) {
        return new d(f);
    }

    public static Interpolator b(float f) {
        return new DecelerateInterpolator(f);
    }

    public static Interpolator c(float f) {
        if (f < -1.0f) {
            f = -1.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        return new o(f);
    }

    public static Interpolator d(float f) {
        return new q(f);
    }

    public static Interpolator e(float f) {
        return new r(f);
    }

    public static Interpolator f(float f) {
        return new s(f);
    }

    public static Interpolator g(float f) {
        return new w(f);
    }

    public static Interpolator h(float f) {
        return new e(f);
    }

    public static Interpolator i(float f) {
        return new f(1.525f * f);
    }

    public static Interpolator a(float f, float f2) {
        return new m(f2, f);
    }

    public static Interpolator b(float f, float f2) {
        return new n(f2, f);
    }

    public static Interpolator c(float f, float f2) {
        return new p(f2, f);
    }
}

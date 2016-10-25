package com.xunlei.tdlive.aniengine;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import com.xunlei.tdlive.aniengine.aa.a;
import com.xunlei.tdlive.aniengine.aa.c;
import java.util.Iterator;
import java.util.LinkedList;

// compiled from: Tween.java
public class aa extends Animation {
    private c a;
    private LinkedList<c> b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private long i;
    private int j;
    private int k;
    private int l;
    private int m;

    // compiled from: Tween.java
    public static interface a {
        void a(c cVar);
    }

    // compiled from: Tween.java
    static class b implements a {
        boolean a;
        a b;

        b(a aVar) {
            this.b = aVar;
        }

        public void a(c cVar) {
            if (this.b != null && !this.a) {
                this.a = true;
                this.b.a(cVar);
            }
        }
    }

    // compiled from: Tween.java
    public static class c {
        a a;
        int b;
        long c;
        long d;
        float e;
        float f;
        float g;
        float h;
        float i;
        float j;
        boolean k;
        boolean l;
        float m;
        float n;
        String o;
        String p;
        Interpolator q;
        Interpolator r;
        Interpolator s;

        private c(long j) {
            this.c = j;
            this.b = 0;
            this.i = 1.0f;
            this.j = 1.0f;
            this.m = 1.0f;
            this.q = c.a;
            this.r = c.a;
            this.s = c.a;
        }

        public com.xunlei.tdlive.aniengine.aa.c a(Interpolator interpolator) {
            this.s = interpolator;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c b(Interpolator interpolator) {
            this.r = interpolator;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c c(Interpolator interpolator) {
            this.s = interpolator;
            this.r = interpolator;
            this.q = interpolator;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c a(float f) {
            this.m = f;
            this.b |= 2;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c b(float f) {
            this.n = f;
            this.b |= 16;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c a(float f, float f2) {
            this.i = f;
            this.j = f2;
            this.b |= 1;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c b(float f, float f2) {
            return a(String.valueOf(f), String.valueOf(f2));
        }

        public com.xunlei.tdlive.aniengine.aa.c a(String str, String str2) {
            this.o = str;
            this.p = str2;
            this.b |= 4;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c c(float f, float f2) {
            this.e = f;
            this.f = f2;
            this.b |= 32;
            return this;
        }

        public com.xunlei.tdlive.aniengine.aa.c a(a aVar) {
            this.a = new b(aVar);
            return this;
        }

        public static com.xunlei.tdlive.aniengine.aa.c a(long j) {
            return new com.xunlei.tdlive.aniengine.aa.c(j);
        }
    }

    public static aa a(y yVar) {
        return a(yVar, null);
    }

    public static aa a(y yVar, c cVar) {
        if (yVar == null) {
            return new aa(cVar);
        }
        try {
            aa aaVar = (aa) yVar.c();
            if (aaVar != null) {
                return aaVar;
            }
            Animation aaVar2 = new aa(cVar);
            yVar.a(aaVar2);
            return aaVar2;
        } catch (ClassCastException e) {
            aaVar2 = new aa(cVar);
            yVar.a(aaVar2);
            return aaVar2;
        }
    }

    private aa(c cVar) {
        this.a = c.a(0);
        this.b = new LinkedList();
        this.c = 1;
        if (cVar != null) {
            this.a = cVar;
        }
        this.i = -1;
        this.k = 1;
        this.j = 1;
        this.l = 0;
        this.m = 0;
    }

    public aa a(int i, int i2, int i3) {
        this.j = i2;
        this.k = i3;
        this.l = i;
        return this;
    }

    public aa a(c cVar) {
        this.b.add(cVar);
        return this;
    }

    public aa a(long j) {
        return a(j, null);
    }

    public aa a(long j, a aVar) {
        a(c.a(j).a(aVar));
        return this;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
    }

    public boolean hasEnded() {
        return this.j != -1 && this.m >= this.j;
    }

    public boolean getTransformation(long j, Transformation transformation) {
        if (hasEnded()) {
            return true;
        }
        Iterator descendingIterator;
        int i;
        c cVar;
        if (!this.h) {
            float f;
            float f2;
            this.h = true;
            float a = a(this.a.e, this.d);
            float a2 = a(this.a.f, this.e);
            float a3 = a(this.a.o, a, this.d, this.f);
            float a4 = a(this.a.p, a2, this.d, this.f);
            Matrix matrix = transformation.getMatrix();
            if (this.a.k) {
                f = -this.a.i;
            } else {
                f = this.a.i;
            }
            if (this.a.l) {
                f2 = -this.a.j;
            } else {
                f2 = this.a.j;
            }
            matrix.setScale(f, f2, a, a2);
            transformation.getMatrix().postSkew(this.a.g, this.a.h, a, a2);
            transformation.getMatrix().postRotate(this.a.n, a, a2);
            transformation.getMatrix().postTranslate(a3, a4);
            transformation.setAlpha(this.a.m);
        }
        if (this.c < 0) {
            int size = this.b.size() - 1;
            descendingIterator = this.b.descendingIterator();
        } else {
            Object obj = null;
            descendingIterator = this.b.iterator();
        }
        if (this.i == -1) {
            this.i = j;
            i = size;
            while (descendingIterator.hasNext()) {
                cVar = (c) descendingIterator.next();
                if (this.m > 0 && descendingIterator.hasNext()) {
                    if (this.c < 0) {
                        cVar = (c) descendingIterator.next();
                        i += this.c;
                    } else if (i == this.l) {
                        cVar = (c) descendingIterator.next();
                        i += this.c;
                    }
                }
                if (this.m <= 0 || i >= this.l) {
                    this.i += cVar.c;
                    cVar.d = this.i;
                }
                i = this.c + i;
            }
            if (this.c < 0) {
                size = this.b.size() - 1;
                descendingIterator = this.b.descendingIterator();
                i = size;
            } else {
                descendingIterator = this.b.iterator();
                i = 0;
            }
        } else {
            i = size;
        }
        while (descendingIterator.hasNext()) {
            cVar = (c) descendingIterator.next();
            if (this.m <= 0 || i >= this.l) {
                i += this.c;
                if (j <= cVar.d) {
                    a2 = a(cVar.c - (cVar.d - j), cVar.c);
                    float interpolation = cVar.q.getInterpolation(a2);
                    float interpolation2 = cVar.r.getInterpolation(a2);
                    float interpolation3 = cVar.s.getInterpolation(a2);
                    float a5 = a((cVar.b & 32) != 0 ? cVar.e : this.a.e, this.d);
                    float a6 = a((cVar.b & 32) != 0 ? cVar.f : this.a.f, this.e);
                    boolean z = (cVar.b & 64) != 0 ? cVar.k : this.a.k;
                    boolean z2 = (cVar.b & 64) != 0 ? cVar.l : this.a.l;
                    float f3 = this.a.i;
                    float f4 = f3 + ((((cVar.b & 1) != 0 ? cVar.i : this.a.i) - f3) * interpolation2);
                    f3 = this.a.j;
                    f3 += (((cVar.b & 1) != 0 ? cVar.j : this.a.j) - f3) * interpolation3;
                    float f5 = this.a.g;
                    f5 += (((cVar.b & 8) != 0 ? cVar.g : this.a.g) - f5) * interpolation2;
                    float f6 = this.a.h;
                    f6 += (((cVar.b & 8) != 0 ? cVar.h : this.a.h) - f6) * interpolation3;
                    float a7 = a(this.a.o, a5, this.d, this.f);
                    interpolation2 = a7 + ((a((cVar.b & 4) != 0 ? cVar.o : this.a.o, a5, this.d, this.f) - a7) * interpolation2);
                    a7 = a(this.a.p, a6, this.e, this.g);
                    interpolation3 = a7 + ((a((cVar.b & 4) != 0 ? cVar.p : this.a.p, a6, this.e, this.g) - a7) * interpolation3);
                    a7 = this.a.n;
                    a7 += (((cVar.b & 16) != 0 ? cVar.n : this.a.n) - a7) * interpolation;
                    float f7 = this.a.m;
                    interpolation = f7 + ((((cVar.b & 2) != 0 ? cVar.m : this.a.m) - f7) * interpolation);
                    Matrix matrix2 = transformation.getMatrix();
                    if (z) {
                        a4 = -f4;
                    } else {
                        a4 = f4;
                    }
                    if (z2) {
                        a2 = -f3;
                    } else {
                        a2 = f3;
                    }
                    matrix2.setScale(a4, a2, a5, a6);
                    transformation.getMatrix().postSkew(f5, f6, a5, a6);
                    transformation.getMatrix().postRotate(a7, a5, a6);
                    transformation.getMatrix().postTranslate(interpolation2, interpolation3);
                    transformation.setAlpha(interpolation);
                    if (j < cVar.d) {
                        return true;
                    }
                }
                if ((cVar.b & 64) != 0) {
                    this.a.k = cVar.k;
                    this.a.l = cVar.l;
                }
                if ((cVar.b & 32) != 0) {
                    this.a.e = cVar.e;
                    this.a.f = cVar.f;
                }
                if ((cVar.b & 1) != 0) {
                    this.a.i = cVar.i;
                    this.a.j = cVar.j;
                }
                if ((cVar.b & 8) != 0) {
                    this.a.g = cVar.g;
                    this.a.h = cVar.h;
                }
                if ((cVar.b & 4) != 0) {
                    this.a.o = cVar.o;
                    this.a.p = cVar.p;
                }
                if ((cVar.b & 16) != 0) {
                    this.a.n = cVar.n;
                }
                if ((cVar.b & 2) != 0) {
                    this.a.m = cVar.m;
                }
                if (cVar.a != null) {
                    cVar.a.a(cVar);
                }
            } else {
                i = this.c + i;
            }
        }
        this.i = -1;
        this.m++;
        if (this.k == 2) {
            this.c = 0 - this.c;
        }
        return true;
    }

    private float a(float f, int i) {
        return Math.abs(f) <= 1.0f ? f * ((float) i) : f;
    }

    private float a(String str, float f, int i, int i2) {
        try {
            if (str.endsWith("%p")) {
                return ((Float.valueOf(str.substring(0, str.length() - 2)).floatValue() / 100.0f) * ((float) i2)) - f;
            }
            return str.endsWith("%") ? (Float.valueOf(str.substring(0, str.length() - 1)).floatValue() / 100.0f) * ((float) i) : Float.valueOf(str).floatValue();
        } catch (Throwable th) {
            return 0.0f;
        }
    }

    private float a(long j, long j2) {
        return Math.max(Math.min(((float) j) / ((float) j2), 1.0f), 0.0f);
    }
}

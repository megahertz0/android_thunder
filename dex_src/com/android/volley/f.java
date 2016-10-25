package com.android.volley;

// compiled from: DefaultRetryPolicy.java
public final class f implements t {
    private int a;
    private int b;
    private final int c;
    private final float d;

    public f() {
        this(2500, 1, 1.0f);
    }

    public f(int i, int i2, float f) {
        this.a = i;
        this.c = i2;
        this.d = f;
    }

    public final int a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final void a(w wVar) throws w {
        Object obj;
        this.b++;
        this.a = (int) (((float) this.a) + (((float) this.a) * this.d));
        if (this.b <= this.c) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            throw wVar;
        }
    }
}

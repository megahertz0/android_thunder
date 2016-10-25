package com.umeng.socialize.view.a;

import android.content.Context;
import com.umeng.socialize.view.a.b.b;
import java.util.Timer;

// compiled from: ACProgressFlower.java
public class f extends a {
    private a a;
    private b b;
    private int c;
    private Timer d;

    // compiled from: ACProgressFlower.java
    public static class a {
        private Context a;
        private float b;
        private float c;
        private float d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private float j;
        private float k;
        private float l;
        private int m;
        private float n;
        private String o;
        private int p;
        private float q;
        private float r;
        private int s;
        private boolean t;

        public a(Context context) {
            this.b = 0.25f;
            this.c = 0.55f;
            this.d = 0.27f;
            this.e = -16777216;
            this.f = -1;
            this.g = -12303292;
            this.h = 12;
            this.i = 9;
            this.j = 0.5f;
            this.k = 20.0f;
            this.l = 0.5f;
            this.m = 100;
            this.n = 9.0f;
            this.o = null;
            this.p = -1;
            this.q = 0.5f;
            this.r = 40.0f;
            this.s = 40;
            this.t = true;
            this.a = context;
        }

        public com.umeng.socialize.view.a.f.a a(float f) {
            this.b = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a b(float f) {
            this.c = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a c(float f) {
            this.d = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a a(int i) {
            this.e = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a b(int i) {
            this.f = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a c(int i) {
            this.g = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a d(int i) {
            this.h = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a e(int i) {
            this.i = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a d(float f) {
            this.j = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a e(float f) {
            this.k = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a f(float f) {
            this.l = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a f(int i) {
            this.m = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a g(float f) {
            this.n = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a a(String str) {
            this.o = str;
            return this;
        }

        public com.umeng.socialize.view.a.f.a g(int i) {
            this.r = (float) i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a h(int i) {
            this.p = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a h(float f) {
            this.q = f;
            return this;
        }

        public com.umeng.socialize.view.a.f.a i(int i) {
            this.s = i;
            return this;
        }

        public com.umeng.socialize.view.a.f.a a(boolean z) {
            this.t = z;
            return this;
        }

        public f a() {
            return new f();
        }
    }

    static /* synthetic */ int e(f fVar) {
        int i = fVar.c;
        fVar.c = i + 1;
        return i;
    }

    private f(a aVar) {
        super(aVar.a);
        this.c = 0;
        this.a = aVar;
        setOnDismissListener(new g(this));
    }

    public void show() {
        if (this.b == null) {
            this.b = new b(this.a.a, (int) (((float) a(this.a.a)) * this.a.b), this.a.e, this.a.l, this.a.k, this.a.i, this.a.h, this.a.j, this.a.c, this.a.d, this.a.f, this.a.g, this.a.o, this.a.r, this.a.p, this.a.q, this.a.s, this.a.t);
        }
        super.setContentView(this.b);
        super.show();
        long s = (long) (1000.0f / this.a.n);
        this.d = new Timer();
        this.d.scheduleAtFixedRate(new h(this), s, s);
    }
}

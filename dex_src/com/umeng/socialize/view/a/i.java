package com.umeng.socialize.view.a;

import android.content.Context;
import com.umeng.socialize.view.a.b.c;
import java.util.Timer;

// compiled from: ACProgressPie.java
public class i extends a {
    private a a;
    private c b;
    private Timer c;
    private int d;

    // compiled from: ACProgressPie.java
    public static class a {
        private Context a;
        private float b;
        private int c;
        private float d;
        private float e;
        private int f;
        private float g;
        private float h;
        private int i;
        private int j;
        private float k;
        private float l;
        private float m;
        private int n;
        private int o;

        public a(Context context) {
            this.b = 0.25f;
            this.c = -16777216;
            this.d = 20.0f;
            this.e = 0.5f;
            this.f = -1;
            this.g = 0.9f;
            this.h = 0.2f;
            this.i = 3;
            this.j = -1;
            this.k = 0.9f;
            this.l = 0.08f;
            this.m = 6.67f;
            this.n = 100;
            this.o = 200;
            this.a = context;
        }

        public com.umeng.socialize.view.a.i.a a(float f) {
            this.b = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a a(int i) {
            this.c = i;
            return this;
        }

        public com.umeng.socialize.view.a.i.a b(float f) {
            this.e = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a c(float f) {
            this.d = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a b(int i) {
            this.f = i;
            return this;
        }

        public com.umeng.socialize.view.a.i.a d(float f) {
            this.g = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a e(float f) {
            this.h = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a c(int i) {
            this.i = i;
            return this;
        }

        public com.umeng.socialize.view.a.i.a d(int i) {
            this.j = i;
            return this;
        }

        public com.umeng.socialize.view.a.i.a f(float f) {
            this.k = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a g(float f) {
            this.l = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a h(float f) {
            this.m = f;
            return this;
        }

        public com.umeng.socialize.view.a.i.a e(int i) {
            this.n = i;
            return this;
        }

        public com.umeng.socialize.view.a.i.a f(int i) {
            this.o = i;
            return this;
        }

        public i a() {
            return new i();
        }
    }

    static /* synthetic */ int e(i iVar) {
        int i = iVar.d;
        iVar.d = i + 1;
        return i;
    }

    private i(a aVar) {
        super(aVar.a);
        this.d = 0;
        this.a = aVar;
        setOnDismissListener(new j(this));
    }

    public void show() {
        if (this.b == null) {
            this.b = new c(this.a.a, (int) (((float) a(this.a.a)) * this.a.b), this.a.c, this.a.e, this.a.d, this.a.h, this.a.l, this.a.i, this.a.f, this.a.g, this.a.j, this.a.k);
        }
        super.setContentView(this.b);
        super.show();
        if (this.a.o == 200) {
            long n = (long) (1000.0f / this.a.m);
            this.c = new Timer();
            this.c.scheduleAtFixedRate(new k(this), n, n);
        }
    }

    public void a(float f) {
        if (this.a.o == 201 && this.b != null) {
            this.b.a(360.0f * f);
        }
    }
}

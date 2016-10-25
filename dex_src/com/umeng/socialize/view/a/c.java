package com.umeng.socialize.view.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

// compiled from: ACProgressCustom.java
public final class c extends a {
    private static final int a = -1;
    private static final int b = 0;
    private static final int c = 1;
    private a d;
    private com.umeng.socialize.view.a.b.a e;
    private Timer f;
    private int g;
    private int h;
    private List<Bitmap> i;

    // compiled from: ACProgressCustom.java
    public static class a {
        private Context a;
        private float b;
        private List<Integer> c;
        private List<String> d;
        private int e;
        private float f;

        public a(Context context) {
            this.b = 0.2f;
            this.c = new ArrayList();
            this.d = new ArrayList();
            this.e = -1;
            this.f = 6.67f;
            this.a = context;
        }

        public com.umeng.socialize.view.a.c.a a(float f) {
            this.b = f;
            return this;
        }

        public com.umeng.socialize.view.a.c.a b(float f) {
            this.f = f;
            return this;
        }

        public com.umeng.socialize.view.a.c.a a(Integer... numArr) {
            if (!(numArr == null || numArr.length == 0)) {
                this.c.clear();
                Collections.addAll(this.c, numArr);
                this.e = 0;
            }
            return this;
        }

        public com.umeng.socialize.view.a.c.a a(String... strArr) {
            if (!(strArr == null || strArr.length == 0)) {
                this.d.clear();
                Collections.addAll(this.d, strArr);
                this.e = 1;
            }
            return this;
        }

        public c a() {
            return new c();
        }
    }

    static /* synthetic */ int f(c cVar) {
        int i = cVar.g;
        cVar.g = i + 1;
        return i;
    }

    private c(a aVar) {
        super(aVar.a);
        this.g = 0;
        this.d = aVar;
        setOnDismissListener(new d(this));
    }

    public final void show() {
        if (this.d.e != -1) {
            if (this.e == null) {
                this.i = new ArrayList();
                int a = (int) (((float) a(this.d.a)) * this.d.b);
                int i;
                if (this.d.e == 0) {
                    this.h = this.d.c.size();
                    for (i = 0; i < this.h; i++) {
                        this.i.add(BitmapFactory.decodeResource(this.d.a.getResources(), ((Integer) this.d.c.get(i)).intValue()));
                    }
                } else {
                    this.h = this.d.d.size();
                    for (i = 0; i < this.h; i++) {
                        this.i.add(BitmapFactory.decodeFile((String) this.d.d.get(i)));
                    }
                }
                this.e = new com.umeng.socialize.view.a.b.a(this.d.a, a, this.i);
            }
            super.setContentView(this.e);
            super.show();
            long f = (long) (1000.0f / this.d.f);
            this.f = new Timer();
            this.f.scheduleAtFixedRate(new e(this), f, f);
            return;
        }
        c.class.toString();
    }
}

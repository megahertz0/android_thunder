package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.b.d;

// compiled from: DisplayImageOptions.java
public final class c {
    final int a;
    final int b;
    final int c;
    final Drawable d;
    final Drawable e;
    final Drawable f;
    final boolean g;
    final boolean h;
    final boolean i;
    public final ImageScaleType j;
    public final Options k;
    final int l;
    public final boolean m;
    public final Object n;
    final com.nostra13.universalimageloader.core.e.a o;
    final com.nostra13.universalimageloader.core.e.a p;
    final com.nostra13.universalimageloader.core.b.a q;
    final Handler r;
    final boolean s;

    // compiled from: DisplayImageOptions.java
    public static class a {
        public int a;
        public int b;
        public int c;
        public Drawable d;
        public Drawable e;
        public Drawable f;
        public boolean g;
        public boolean h;
        public boolean i;
        ImageScaleType j;
        Options k;
        int l;
        public boolean m;
        Object n;
        com.nostra13.universalimageloader.core.e.a o;
        com.nostra13.universalimageloader.core.e.a p;
        public com.nostra13.universalimageloader.core.b.a q;
        public Handler r;
        public boolean s;

        public a() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = ImageScaleType.IN_SAMPLE_POWER_OF_2;
            this.k = new Options();
            this.l = 0;
            this.m = false;
            this.n = null;
            this.o = null;
            this.p = null;
            this.q = new d();
            this.r = null;
            this.s = false;
        }

        public final com.nostra13.universalimageloader.core.c.a a(Config config) {
            if (config == null) {
                throw new IllegalArgumentException("bitmapConfig can't be null");
            }
            this.k.inPreferredConfig = config;
            return this;
        }

        public final c b() {
            return new c();
        }

        @Deprecated
        public final com.nostra13.universalimageloader.core.c.a a() {
            this.i = true;
            return this;
        }

        public final com.nostra13.universalimageloader.core.c.a a(c cVar) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
            this.q = cVar.q;
            this.r = cVar.r;
            this.s = cVar.s;
            return this;
        }
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
        this.m = aVar.m;
        this.n = aVar.n;
        this.o = aVar.o;
        this.p = aVar.p;
        this.q = aVar.q;
        this.r = aVar.r;
        this.s = aVar.s;
    }

    public final boolean a() {
        return this.p != null;
    }
}

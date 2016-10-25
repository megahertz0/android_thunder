package com.bumptech.glide.load.engine.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.h.h;

// compiled from: AttributeStrategy.java
final class a implements g {
    private final b a;
    private final e<a, Bitmap> b;

    // compiled from: AttributeStrategy.java
    static class a implements h {
        int a;
        int b;
        Config c;
        private final b d;

        public a(b bVar) {
            this.d = bVar;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b && this.c == aVar.c;
        }

        public final int hashCode() {
            return (this.c != null ? this.c.hashCode() : 0) + (((this.a * 31) + this.b) * 31);
        }

        public final String toString() {
            return a.c(this.a, this.b, this.c);
        }

        public final void a() {
            this.d.a(this);
        }
    }

    // compiled from: AttributeStrategy.java
    static class b extends b<a> {
        b() {
        }

        public final a a(int i, int i2, Config config) {
            a aVar = (a) b();
            aVar.a = i;
            aVar.b = i2;
            aVar.c = config;
            return aVar;
        }

        protected final /* synthetic */ h a() {
            return new a(this);
        }
    }

    a() {
        this.a = new b();
        this.b = new e();
    }

    public final void a(Bitmap bitmap) {
        this.b.a(this.a.a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    public final Bitmap a(int i, int i2, Config config) {
        return (Bitmap) this.b.a(this.a.a(i, i2, config));
    }

    public final Bitmap a() {
        return (Bitmap) this.b.a();
    }

    public final String b(int i, int i2, Config config) {
        return c(i, i2, config);
    }

    public final int c(Bitmap bitmap) {
        return h.a(bitmap);
    }

    public final String toString() {
        return new StringBuilder("AttributeStrategy:\n  ").append(this.b).toString();
    }

    static String c(int i, int i2, Config config) {
        return new StringBuilder("[").append(i).append("x").append(i2).append("], ").append(config).toString();
    }

    public final String b(Bitmap bitmap) {
        return c(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }
}

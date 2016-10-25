package com.bumptech.glide.f.a;

import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

// compiled from: DrawableCrossFadeFactory.java
public final class a<T extends Drawable> implements d<T> {
    private final g<T> a;
    private final int b;
    private b<T> c;
    private b<T> d;

    // compiled from: DrawableCrossFadeFactory.java
    private static class a implements a {
        private final int a;

        a() {
            this.a = 300;
        }

        public final Animation a() {
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) this.a);
            return alphaAnimation;
        }
    }

    public a() {
        this((byte) 0);
    }

    private a(byte b) {
        this(new g(new a()));
    }

    private a(g<T> gVar) {
        this.a = gVar;
        this.b = 300;
    }

    public final c<T> a(boolean z, boolean z2) {
        if (z) {
            return e.b();
        }
        if (z2) {
            if (this.c == null) {
                this.c = new b(this.a.a(false, true), this.b);
            }
            return this.c;
        }
        if (this.d == null) {
            this.d = new b(this.a.a(false, false), this.b);
        }
        return this.d;
    }
}

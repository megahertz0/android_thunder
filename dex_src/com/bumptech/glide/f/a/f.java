package com.bumptech.glide.f.a;

import android.view.View;
import android.view.animation.Animation;

// compiled from: ViewAnimation.java
public final class f<R> implements c<R> {
    private final a a;

    // compiled from: ViewAnimation.java
    static interface a {
        Animation a();
    }

    f(a aVar) {
        this.a = aVar;
    }

    public final boolean a(R r, com.bumptech.glide.f.a.c.a aVar) {
        View a = aVar.a();
        if (a != null) {
            a.clearAnimation();
            a.startAnimation(this.a.a());
        }
        return false;
    }
}

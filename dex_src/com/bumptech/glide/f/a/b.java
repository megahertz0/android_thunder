package com.bumptech.glide.f.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.f.a.c.a;

// compiled from: DrawableCrossFadeViewAnimation.java
public final class b<T extends Drawable> implements c<T> {
    private final c<T> a;
    private final int b;

    public final /* synthetic */ boolean a(Object obj, a aVar) {
        Drawable drawable = (Drawable) obj;
        if (aVar.d() != null) {
            Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{aVar.d(), drawable});
            transitionDrawable.setCrossFadeEnabled(true);
            transitionDrawable.startTransition(this.b);
            aVar.a(transitionDrawable);
            return true;
        }
        this.a.a(drawable, aVar);
        return false;
    }

    public b(c<T> cVar, int i) {
        this.a = cVar;
        this.b = i;
    }
}

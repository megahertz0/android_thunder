package com.bumptech.glide.load.resource.a;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.j;

// compiled from: DrawableResource.java
public abstract class a<T extends Drawable> implements j<T> {
    public final T a;

    public a(T t) {
        if (t == null) {
            throw new NullPointerException("Drawable must not be null!");
        }
        this.a = t;
    }

    public final /* synthetic */ Object a() {
        return this.a.getConstantState().newDrawable();
    }
}

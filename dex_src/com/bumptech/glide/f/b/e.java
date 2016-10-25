package com.bumptech.glide.f.b;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.f.a.c;
import com.bumptech.glide.f.a.c.a;

// compiled from: ImageViewTarget.java
public abstract class e<Z> extends k<ImageView, Z> implements a {
    protected abstract void a(Z z);

    public e(ImageView imageView) {
        super(imageView);
    }

    public final Drawable d() {
        return ((ImageView) this.a).getDrawable();
    }

    public final void a(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public final void c(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public final void d(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public final void b(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void a(Z z, c<? super Z> cVar) {
        if (cVar == null || !cVar.a(z, this)) {
            a((Object) z);
        }
    }
}

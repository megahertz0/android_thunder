package com.bumptech.glide.f.b;

import android.widget.ImageView;
import com.bumptech.glide.f.a.c;
import com.bumptech.glide.load.resource.a.b;

// compiled from: GlideDrawableImageViewTarget.java
public final class d extends e<b> {
    private int b;
    private b c;

    protected final /* synthetic */ void a(Object obj) {
        ((ImageView) this.a).setImageDrawable((b) obj);
    }

    public final /* synthetic */ void a(Object obj, c cVar) {
        b bVar = (b) obj;
        if (!bVar.a()) {
            float intrinsicWidth = ((float) bVar.getIntrinsicWidth()) / ((float) bVar.getIntrinsicHeight());
            if (Math.abs((((float) ((ImageView) this.a).getWidth()) / ((float) ((ImageView) this.a).getHeight())) - 1.0f) <= 0.05f && Math.abs(intrinsicWidth - 1.0f) <= 0.05f) {
                i iVar = new i(bVar, ((ImageView) this.a).getWidth());
            }
        }
        super.a(obj, cVar);
        this.c = obj;
        obj.a(this.b);
        obj.start();
    }

    public d(ImageView imageView) {
        this(imageView, (byte) 0);
    }

    private d(ImageView imageView, byte b) {
        super(imageView);
        this.b = -1;
    }

    public final void b() {
        if (this.c != null) {
            this.c.start();
        }
    }

    public final void c() {
        if (this.c != null) {
            this.c.stop();
        }
    }
}

package com.bumptech.glide.load.resource.c;

import com.bumptech.glide.h.h;
import com.bumptech.glide.load.resource.a.a;

// compiled from: GifDrawableResource.java
public final class d extends a<b> {
    public d(b bVar) {
        super(bVar);
    }

    public final int b() {
        return h.a(((b) this.a).a.i) + ((b) this.a).a.b.length;
    }

    public final void c() {
        ((b) this.a).stop();
        b bVar = (b) this.a;
        bVar.d = true;
        bVar.a.h.a(bVar.a.i);
        bVar.c.a();
        bVar.c.c = false;
    }
}

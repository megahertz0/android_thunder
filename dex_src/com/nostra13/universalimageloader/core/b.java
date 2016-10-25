package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.c.a;

// compiled from: DisplayBitmapTask.java
final class b implements Runnable {
    private final Bitmap a;
    private final String b;
    private final a c;
    private final String d;
    private final com.nostra13.universalimageloader.core.b.a e;
    private final a f;
    private final f g;
    private final LoadedFrom h;

    public b(Bitmap bitmap, h hVar, f fVar, LoadedFrom loadedFrom) {
        this.a = bitmap;
        this.b = hVar.a;
        this.c = hVar.c;
        this.d = hVar.b;
        this.e = hVar.e.q;
        this.f = hVar.f;
        this.g = fVar;
        this.h = loadedFrom;
    }

    public final void run() {
        if (this.c.isCollected()) {
            c.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.d);
            this.f.onLoadingCancelled(this.b, this.c.getWrappedView());
            return;
        }
        int i;
        if (this.d.equals(this.g.a(this.c))) {
            i = 0;
        } else {
            i = 1;
        }
        if (i != 0) {
            c.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.d);
            this.f.onLoadingCancelled(this.b, this.c.getWrappedView());
            return;
        }
        c.a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.h, this.d);
        this.e.a(this.a, this.c, this.h);
        this.g.b(this.c);
        this.f.onLoadingComplete(this.b, this.c.getWrappedView(), this.a);
    }
}

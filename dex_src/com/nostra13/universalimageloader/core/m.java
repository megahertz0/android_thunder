package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;

// compiled from: ProcessAndDisplayImageTask.java
final class m implements Runnable {
    private final f a;
    private final Bitmap b;
    private final h c;
    private final Handler d;

    public m(f fVar, Bitmap bitmap, h hVar, Handler handler) {
        this.a = fVar;
        this.b = bitmap;
        this.c = hVar;
        this.d = handler;
    }

    public final void run() {
        c.a("PostProcess image before displaying [%s]", this.c.b);
        i.a(new b(this.c.e.p.a(), this.c, this.a, LoadedFrom.MEMORY_CACHE), this.c.e.s, this.d, this.a);
    }
}

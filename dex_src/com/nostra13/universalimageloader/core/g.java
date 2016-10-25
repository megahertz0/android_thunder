package com.nostra13.universalimageloader.core;

import java.io.File;

// compiled from: ImageLoaderEngine.java
final class g implements Runnable {
    final /* synthetic */ i a;
    final /* synthetic */ f b;

    g(f fVar, i iVar) {
        this.b = fVar;
        this.a = iVar;
    }

    public final void run() {
        File a = this.b.a.o.a(this.a.b);
        Object obj = (a == null || !a.exists()) ? null : 1;
        this.b.a();
        if (obj != null) {
            this.b.c.execute(this.a);
        } else {
            this.b.b.execute(this.a);
        }
    }
}

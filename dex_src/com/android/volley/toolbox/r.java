package com.android.volley.toolbox;

import com.android.volley.toolbox.i.c;

// compiled from: NetworkImageView.java
final class r implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ q b;

    r(q qVar, c cVar) {
        this.b = qVar;
        this.a = cVar;
    }

    public final void run() {
        this.b.a(this.a, false);
    }
}

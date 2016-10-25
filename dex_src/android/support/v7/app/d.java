package android.support.v7.app;

import android.view.View;

// compiled from: AlertController.java
final class d implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ a c;

    d(a aVar, View view, View view2) {
        this.c = aVar;
        this.a = view;
        this.b = view2;
    }

    public final void run() {
        a.a(this.c.w, this.a, this.b);
    }
}

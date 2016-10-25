package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

// compiled from: ThunderMediaPlayer.java
final class ac implements OnLayoutChangeListener {
    final /* synthetic */ View a;
    final /* synthetic */ ab b;

    ac(ab abVar, View view) {
        this.b = abVar;
        this.a = view;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.b.h != this.a.getWidth() || this.b.i != this.a.getHeight()) {
            this.b.h = this.a.getWidth();
            this.b.i = this.a.getHeight();
            ab.a(this.b, this.b.h, this.b.i, this.b.j, this.b.k);
        }
    }
}

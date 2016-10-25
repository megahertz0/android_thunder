package android.support.v7.widget;

import android.view.View;

// compiled from: ScrollingTabContainerView.java
final class bn implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ bm b;

    bn(bm bmVar, View view) {
        this.b = bmVar;
        this.a = view;
    }

    public final void run() {
        this.b.smoothScrollTo(this.a.getLeft() - ((this.b.getWidth() - this.a.getWidth()) / 2), 0);
        this.b.a = null;
    }
}

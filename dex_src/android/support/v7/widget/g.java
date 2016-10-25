package android.support.v7.widget;

import android.support.v4.view.ViewCompat;

// compiled from: ActionBarOverlayLayout.java
final class g implements Runnable {
    final /* synthetic */ ActionBarOverlayLayout a;

    g(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.a = actionBarOverlayLayout;
    }

    public final void run() {
        this.a.i();
        this.a.x = ViewCompat.animate(this.a.f).translationY((float) (-this.a.f.getHeight())).setListener(this.a.y);
    }
}

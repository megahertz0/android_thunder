package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;

// compiled from: ActionBarOverlayLayout.java
final class f implements Runnable {
    final /* synthetic */ ActionBarOverlayLayout a;

    f(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.a = actionBarOverlayLayout;
    }

    public final void run() {
        this.a.i();
        this.a.x = ViewCompat.animate(this.a.f).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED).setListener(this.a.y);
    }
}

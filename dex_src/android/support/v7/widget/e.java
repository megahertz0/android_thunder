package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

// compiled from: ActionBarOverlayLayout.java
final class e extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ ActionBarOverlayLayout a;

    e(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.a = actionBarOverlayLayout;
    }

    public final void onAnimationEnd(View view) {
        this.a.x = null;
        this.a.l = false;
    }

    public final void onAnimationCancel(View view) {
        this.a.x = null;
        this.a.l = false;
    }
}

package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

// compiled from: WindowDecorActionBar.java
final class ah extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ af a;

    ah(af afVar) {
        this.a = afVar;
    }

    public final void onAnimationEnd(View view) {
        this.a.H = null;
        this.a.q.requestLayout();
    }
}

package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;

// compiled from: WindowDecorActionBar.java
final class ai implements ViewPropertyAnimatorUpdateListener {
    final /* synthetic */ af a;

    ai(af afVar) {
        this.a = afVar;
    }

    public final void onAnimationUpdate(View view) {
        ((View) this.a.q.getParent()).invalidate();
    }
}

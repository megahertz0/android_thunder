package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

// compiled from: AppCompatDelegateImplV7.java
final class w extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public final void onAnimationEnd(View view) {
        ViewCompat.setAlpha(this.a.a.r, 1.0f);
        this.a.a.u.setListener(null);
        this.a.a.u = null;
    }

    public final void onAnimationStart(View view) {
        this.a.a.r.setVisibility(0);
    }
}

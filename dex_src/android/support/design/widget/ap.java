package android.support.design.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: Snackbar.java
final class ap implements AnimationListener {
    final /* synthetic */ Snackbar a;

    ap(Snackbar snackbar) {
        this.a = snackbar;
    }

    public final void onAnimationEnd(Animation animation) {
        this.a.b();
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}

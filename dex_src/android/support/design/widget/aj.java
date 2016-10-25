package android.support.design.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: Snackbar.java
final class aj implements AnimationListener {
    final /* synthetic */ int a;
    final /* synthetic */ Snackbar b;

    aj(Snackbar snackbar, int i) {
        this.b = snackbar;
        this.a = i;
    }

    public final void onAnimationEnd(Animation animation) {
        this.b.c();
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}

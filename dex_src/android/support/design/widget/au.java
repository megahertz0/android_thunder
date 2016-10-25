package android.support.design.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: StateListAnimator.java
final class au implements AnimationListener {
    final /* synthetic */ at a;

    au(at atVar) {
        this.a = atVar;
    }

    public final void onAnimationEnd(Animation animation) {
        if (this.a.c == animation) {
            this.a.c = null;
        }
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}

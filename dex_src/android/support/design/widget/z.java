package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// compiled from: FloatingActionButtonIcs.java
final class z extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ a b;
    final /* synthetic */ x c;

    z(x xVar) {
        this.c = xVar;
        this.a = false;
        this.b = null;
    }

    public final void onAnimationStart(Animator animator) {
        this.c.k.a(0, this.a);
    }

    public final void onAnimationEnd(Animator animator) {
    }
}

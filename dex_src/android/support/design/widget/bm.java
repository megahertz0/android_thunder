package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// compiled from: ValueAnimatorCompatImplHoneycombMr1.java
final class bm extends AnimatorListenerAdapter {
    final /* synthetic */ a a;
    final /* synthetic */ bk b;

    bm(bk bkVar, a aVar) {
        this.b = bkVar;
        this.a = aVar;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.a();
    }

    public final void onAnimationCancel(Animator animator) {
    }
}

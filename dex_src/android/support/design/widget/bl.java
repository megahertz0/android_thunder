package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

// compiled from: ValueAnimatorCompatImplHoneycombMr1.java
final class bl implements AnimatorUpdateListener {
    final /* synthetic */ b a;
    final /* synthetic */ bk b;

    bl(bk bkVar, b bVar) {
        this.b = bkVar;
        this.a = bVar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.a();
    }
}

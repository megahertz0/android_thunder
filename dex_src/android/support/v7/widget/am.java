package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;

// compiled from: DefaultItemAnimator.java
final class am extends c {
    final /* synthetic */ a a;
    final /* synthetic */ ViewPropertyAnimatorCompat b;
    final /* synthetic */ af c;

    am(af afVar, a aVar, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        this.c = afVar;
        this.a = aVar;
        this.b = viewPropertyAnimatorCompat;
        super();
    }

    public final void onAnimationStart(View view) {
    }

    public final void onAnimationEnd(View view) {
        this.b.setListener(null);
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setTranslationX(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        ViewCompat.setTranslationY(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.c.e(this.a.a);
        this.c.g.remove(this.a.a);
        this.c.c();
    }
}

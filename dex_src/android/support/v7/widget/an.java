package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;

// compiled from: DefaultItemAnimator.java
final class an extends c {
    final /* synthetic */ a a;
    final /* synthetic */ ViewPropertyAnimatorCompat b;
    final /* synthetic */ View c;
    final /* synthetic */ af d;

    an(af afVar, a aVar, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
        this.d = afVar;
        this.a = aVar;
        this.b = viewPropertyAnimatorCompat;
        this.c = view;
        super();
    }

    public final void onAnimationStart(View view) {
    }

    public final void onAnimationEnd(View view) {
        this.b.setListener(null);
        ViewCompat.setAlpha(this.c, 1.0f);
        ViewCompat.setTranslationX(this.c, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        ViewCompat.setTranslationY(this.c, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.d.e(this.a.b);
        this.d.g.remove(this.a.b);
        this.d.c();
    }
}

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.widget.RecyclerView.t;
import android.view.View;

// compiled from: DefaultItemAnimator.java
final class al extends c {
    final /* synthetic */ t a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ ViewPropertyAnimatorCompat d;
    final /* synthetic */ af e;

    al(af afVar, t tVar, int i, int i2, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        this.e = afVar;
        this.a = tVar;
        this.b = i;
        this.c = i2;
        this.d = viewPropertyAnimatorCompat;
        super();
    }

    public final void onAnimationStart(View view) {
    }

    public final void onAnimationCancel(View view) {
        if (this.b != 0) {
            ViewCompat.setTranslationX(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        if (this.c != 0) {
            ViewCompat.setTranslationY(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
    }

    public final void onAnimationEnd(View view) {
        this.d.setListener(null);
        this.e.e(this.a);
        this.e.e.remove(this.a);
        this.e.c();
    }
}

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView.t;
import android.view.View;

// compiled from: DefaultItemAnimator.java
final class ak extends c {
    final /* synthetic */ t a;
    final /* synthetic */ ViewPropertyAnimatorCompat b;
    final /* synthetic */ af c;

    ak(af afVar, t tVar, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        this.c = afVar;
        this.a = tVar;
        this.b = viewPropertyAnimatorCompat;
        super();
    }

    public final void onAnimationStart(View view) {
    }

    public final void onAnimationCancel(View view) {
        ViewCompat.setAlpha(view, 1.0f);
    }

    public final void onAnimationEnd(View view) {
        this.b.setListener(null);
        this.c.e(this.a);
        this.c.d.remove(this.a);
        this.c.c();
    }
}

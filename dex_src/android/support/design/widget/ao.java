package android.support.design.widget;

import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;

// compiled from: Snackbar.java
final class ao extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ Snackbar a;

    ao(Snackbar snackbar) {
        this.a = snackbar;
    }

    public final void onAnimationStart(View view) {
        SnackbarLayout d = this.a.b;
        ViewCompat.setAlpha(d.a, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        ViewCompat.animate(d.a).alpha(1.0f).setDuration(180).setStartDelay(70).start();
        if (d.b.getVisibility() == 0) {
            ViewCompat.setAlpha(d.b, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            ViewCompat.animate(d.b).alpha(1.0f).setDuration(180).setStartDelay(70).start();
        }
    }

    public final void onAnimationEnd(View view) {
        this.a.b();
    }
}

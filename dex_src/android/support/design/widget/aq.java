package android.support.design.widget;

import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;

// compiled from: Snackbar.java
final class aq extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ int a;
    final /* synthetic */ Snackbar b;

    aq(Snackbar snackbar, int i) {
        this.b = snackbar;
        this.a = i;
    }

    public final void onAnimationStart(View view) {
        SnackbarLayout d = this.b.b;
        ViewCompat.setAlpha(d.a, 1.0f);
        ViewCompat.animate(d.a).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED).setDuration(180).setStartDelay(0).start();
        if (d.b.getVisibility() == 0) {
            ViewCompat.setAlpha(d.b, 1.0f);
            ViewCompat.animate(d.b).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED).setDuration(180).setStartDelay(0).start();
        }
    }

    public final void onAnimationEnd(View view) {
        this.b.c();
    }
}

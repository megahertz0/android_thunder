package android.support.design.widget;

import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import org.android.spdy.SpdyAgent;

// compiled from: Snackbar.java
final class ai implements Callback {
    ai() {
    }

    public final boolean handleMessage(Message message) {
        Snackbar snackbar;
        switch (message.what) {
            case SpdyAgent.ACCS_TEST_SERVER:
                snackbar = (Snackbar) message.obj;
                if (snackbar.b.getParent() == null) {
                    LayoutParams layoutParams = snackbar.b.getLayoutParams();
                    if (layoutParams instanceof d) {
                        Behavior aVar = new a();
                        aVar.d = SwipeDismissBehavior.a(0.1f);
                        aVar.e = SwipeDismissBehavior.a(0.6f);
                        aVar.c = 0;
                        aVar.b = new ak(snackbar);
                        ((d) layoutParams).a(aVar);
                    }
                    snackbar.a.addView(snackbar.b);
                }
                snackbar.b.setOnAttachStateChangeListener(new al(snackbar));
                if (!ViewCompat.isLaidOut(snackbar.b)) {
                    snackbar.b.setOnLayoutChangeListener(new an(snackbar));
                } else if (snackbar.d()) {
                    snackbar.a();
                } else {
                    snackbar.b();
                }
                return true;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                snackbar = (Snackbar) message.obj;
                int i = message.arg1;
                if (!snackbar.d() || snackbar.b.getVisibility() != 0) {
                    snackbar.c();
                } else if (VERSION.SDK_INT >= 14) {
                    ViewCompat.animate(snackbar.b).translationY((float) snackbar.b.getHeight()).setInterpolator(b).setDuration(250).setListener(new aq(snackbar, i)).start();
                } else {
                    Animation loadAnimation = AnimationUtils.loadAnimation(snackbar.b.getContext(), R.anim.design_snackbar_out);
                    loadAnimation.setInterpolator(b);
                    loadAnimation.setDuration(250);
                    loadAnimation.setAnimationListener(new aj(snackbar, i));
                    snackbar.b.startAnimation(loadAnimation);
                }
                return true;
            default:
                return false;
        }
    }
}

package android.support.v7.widget;

import android.support.v4.widget.AutoScrollHelper;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: SwitchCompat.java
final class ch implements AnimationListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ SwitchCompat b;

    ch(SwitchCompat switchCompat, boolean z) {
        this.b = switchCompat;
        this.a = z;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        if (this.b.C == animation) {
            this.b.setThumbPosition(this.a ? 1.0f : AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.b.C = null;
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}

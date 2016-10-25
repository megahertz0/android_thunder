package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FloatingActionButtonIcs.java
final class y extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ a b;
    final /* synthetic */ x c;
    private boolean d;

    y(x xVar) {
        this.c = xVar;
        this.a = false;
        this.b = null;
    }

    public final void onAnimationStart(Animator animator) {
        this.c.n = true;
        this.d = false;
        this.c.k.a(0, this.a);
    }

    public final void onAnimationCancel(Animator animator) {
        this.c.n = false;
        this.d = true;
    }

    public final void onAnimationEnd(Animator animator) {
        this.c.n = false;
        if (!this.d) {
            this.c.k.a(XZBDevice.Wait, this.a);
        }
    }
}

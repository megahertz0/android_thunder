package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: WindowDecorActionBar.java
final class ag extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ af a;

    ag(af afVar) {
        this.a = afVar;
    }

    public final void onAnimationEnd(View view) {
        if (this.a.C && this.a.t != null) {
            ViewCompat.setTranslationY(this.a.t, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            ViewCompat.setTranslationY(this.a.q, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        this.a.q.setVisibility(XZBDevice.Wait);
        this.a.q.setTransitioning(false);
        this.a.H = null;
        af afVar = this.a;
        if (afVar.c != null) {
            afVar.c.a(afVar.b);
            afVar.b = null;
            afVar.c = null;
        }
        if (this.a.p != null) {
            ViewCompat.requestApplyInsets(this.a.p);
        }
    }
}

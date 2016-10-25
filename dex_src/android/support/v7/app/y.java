package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AppCompatDelegateImplV7.java
final class y extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ b a;

    y(b bVar) {
        this.a = bVar;
    }

    public final void onAnimationEnd(View view) {
        this.a.a.r.setVisibility(XZBDevice.Wait);
        if (this.a.a.s != null) {
            this.a.a.s.dismiss();
        } else if (this.a.a.r.getParent() instanceof View) {
            ViewCompat.requestApplyInsets((View) this.a.a.r.getParent());
        }
        this.a.a.r.removeAllViews();
        this.a.a.u.setListener(null);
        this.a.a.u = null;
    }
}

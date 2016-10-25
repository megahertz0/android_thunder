package android.support.design.widget;

import android.view.animation.Animation;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FloatingActionButtonEclairMr1.java
final class v extends a {
    final /* synthetic */ boolean a;
    final /* synthetic */ a b;
    final /* synthetic */ u c;

    v(u uVar) {
        this.c = uVar;
        this.a = false;
        this.b = null;
    }

    public final void onAnimationStart(Animation animation) {
        this.c.p = true;
    }

    public final void onAnimationEnd(Animation animation) {
        this.c.p = false;
        this.c.k.a(XZBDevice.Wait, this.a);
    }
}

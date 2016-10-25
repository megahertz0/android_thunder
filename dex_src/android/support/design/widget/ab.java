package android.support.design.widget;

import android.view.ViewTreeObserver.OnPreDrawListener;

// compiled from: FloatingActionButtonImpl.java
final class ab implements OnPreDrawListener {
    final /* synthetic */ aa a;

    ab(aa aaVar) {
        this.a = aaVar;
    }

    public final boolean onPreDraw() {
        this.a.g();
        return true;
    }
}

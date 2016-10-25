package android.support.v7.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: AppCompatSpinner.java
final class y implements OnGlobalLayoutListener {
    final /* synthetic */ b a;

    y(b bVar) {
        this.a = bVar;
    }

    public final void onGlobalLayout() {
        if (b.a(this.a, this.a.b)) {
            this.a.a();
            super.b();
            return;
        }
        this.a.d();
    }
}

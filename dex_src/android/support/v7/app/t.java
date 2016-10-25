package android.support.v7.app;

import android.graphics.Rect;
import android.support.v7.widget.ap.a;

// compiled from: AppCompatDelegateImplV7.java
final class t implements a {
    final /* synthetic */ AppCompatDelegateImplV7 a;

    t(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.a = appCompatDelegateImplV7;
    }

    public final void a(Rect rect) {
        rect.top = AppCompatDelegateImplV7.b(this.a, rect.top);
    }
}

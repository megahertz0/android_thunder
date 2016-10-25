package android.support.design.internal;

import android.graphics.Rect;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

// compiled from: ScrimInsetsFrameLayout.java
final class f implements OnApplyWindowInsetsListener {
    final /* synthetic */ ScrimInsetsFrameLayout a;

    f(ScrimInsetsFrameLayout scrimInsetsFrameLayout) {
        this.a = scrimInsetsFrameLayout;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        if (this.a.b == null) {
            this.a.b = new Rect();
        }
        this.a.b.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
        this.a.a(this.a.b);
        ScrimInsetsFrameLayout scrimInsetsFrameLayout = this.a;
        boolean z = this.a.b.isEmpty() || this.a.a == null;
        scrimInsetsFrameLayout.setWillNotDraw(z);
        ViewCompat.postInvalidateOnAnimation(this.a);
        return windowInsetsCompat.consumeSystemWindowInsets();
    }
}

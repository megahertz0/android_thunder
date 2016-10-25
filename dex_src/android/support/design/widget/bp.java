package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

// compiled from: ViewOffsetHelper.java
final class bp {
    int a;
    int b;
    private final View c;
    private int d;
    private int e;

    public bp(View view) {
        this.c = view;
    }

    public final void a() {
        this.d = this.c.getTop();
        this.e = this.c.getLeft();
        b();
    }

    final void b() {
        ViewCompat.offsetTopAndBottom(this.c, this.a - (this.c.getTop() - this.d));
        ViewCompat.offsetLeftAndRight(this.c, this.b - (this.c.getLeft() - this.e));
    }

    public final boolean a(int i) {
        if (this.a == i) {
            return false;
        }
        this.a = i;
        b();
        return true;
    }
}

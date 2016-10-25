package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.h;
import android.view.View;

// compiled from: OrientationHelper.java
final class ay extends ax {
    ay(h hVar) {
        super((byte) 0);
    }

    public final int c() {
        return this.a.y - this.a.q();
    }

    public final int d() {
        return this.a.y;
    }

    public final void a(int i) {
        this.a.f(i);
    }

    public final int b() {
        return this.a.o();
    }

    public final int c(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + (h.c(view) + layoutParams.leftMargin);
    }

    public final int d(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + (h.d(view) + layoutParams.topMargin);
    }

    public final int b(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + h.g(view);
    }

    public final int a(View view) {
        return h.e(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
    }

    public final int e() {
        return (this.a.y - this.a.o()) - this.a.q();
    }

    public final int f() {
        return this.a.q();
    }

    public final int g() {
        return this.a.w;
    }

    public final int h() {
        return this.a.x;
    }
}

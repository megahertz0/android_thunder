package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.h;
import android.view.View;

// compiled from: OrientationHelper.java
final class az extends ax {
    az(h hVar) {
        super((byte) 0);
    }

    public final int c() {
        return this.a.z - this.a.r();
    }

    public final int d() {
        return this.a.z;
    }

    public final void a(int i) {
        this.a.g(i);
    }

    public final int b() {
        return this.a.p();
    }

    public final int c(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + (h.d(view) + layoutParams.topMargin);
    }

    public final int d(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + (h.c(view) + layoutParams.leftMargin);
    }

    public final int b(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + h.h(view);
    }

    public final int a(View view) {
        return h.f(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
    }

    public final int e() {
        return (this.a.z - this.a.p()) - this.a.r();
    }

    public final int f() {
        return this.a.r();
    }

    public final int g() {
        return this.a.x;
    }

    public final int h() {
        return this.a.w;
    }
}

package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.e;
import android.support.v7.widget.RecyclerView.e.b;
import android.support.v7.widget.RecyclerView.t;
import android.view.View;

// compiled from: SimpleItemAnimator.java
public abstract class cb extends e {
    boolean m;

    public abstract boolean a(t tVar);

    public abstract boolean a(t tVar, int i, int i2, int i3, int i4);

    public abstract boolean a(t tVar, t tVar2, int i, int i2, int i3, int i4);

    public abstract boolean b(t tVar);

    public cb() {
        this.m = true;
    }

    public final boolean f(t tVar) {
        return !this.m || tVar.isInvalid();
    }

    public final boolean a(t tVar, b bVar, b bVar2) {
        int i = bVar.a;
        int i2 = bVar.b;
        View view = tVar.itemView;
        int left = bVar2 == null ? view.getLeft() : bVar2.a;
        int top = bVar2 == null ? view.getTop() : bVar2.b;
        if (tVar.isRemoved() || (i == left && i2 == top)) {
            return a(tVar);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return a(tVar, i, i2, left, top);
    }

    public final boolean b(t tVar, b bVar, b bVar2) {
        if (bVar == null || (bVar.a == bVar2.a && bVar.b == bVar2.b)) {
            return b(tVar);
        }
        return a(tVar, bVar.a, bVar.b, bVar2.a, bVar2.b);
    }

    public final boolean c(t tVar, b bVar, b bVar2) {
        if (bVar.a == bVar2.a && bVar.b == bVar2.b) {
            e(tVar);
            return false;
        }
        return a(tVar, bVar.a, bVar.b, bVar2.a, bVar2.b);
    }

    public final boolean a(t tVar, t tVar2, b bVar, b bVar2) {
        int i;
        int i2;
        int i3 = bVar.a;
        int i4 = bVar.b;
        if (tVar2.shouldIgnore()) {
            i = bVar.a;
            i2 = bVar.b;
        } else {
            i = bVar2.a;
            i2 = bVar2.b;
        }
        return a(tVar, tVar2, i3, i4, i, i2);
    }
}

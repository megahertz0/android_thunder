package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.q;
import android.view.View;

// compiled from: ScrollbarHelper.java
final class bl {
    static int a(q qVar, ax axVar, View view, View view2, h hVar, boolean z, boolean z2) {
        if (hVar.n() == 0 || qVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z2 ? Math.max(0, (qVar.a() - Math.max(h.a(view), h.a(view2))) - 1) : Math.max(0, Math.min(h.a(view), h.a(view2)));
        if (!z) {
            return max;
        }
        return Math.round((((float) max) * (((float) Math.abs(axVar.b(view2) - axVar.a(view))) / ((float) (Math.abs(h.a(view) - h.a(view2)) + 1)))) + ((float) (axVar.b() - axVar.a(view))));
    }

    static int a(q qVar, ax axVar, View view, View view2, h hVar, boolean z) {
        if (hVar.n() == 0 || qVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(h.a(view) - h.a(view2)) + 1;
        }
        return Math.min(axVar.e(), axVar.b(view2) - axVar.a(view));
    }

    static int b(q qVar, ax axVar, View view, View view2, h hVar, boolean z) {
        if (hVar.n() == 0 || qVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return qVar.a();
        }
        return (int) ((((float) (axVar.b(view2) - axVar.a(view))) / ((float) (Math.abs(h.a(view) - h.a(view2)) + 1))) * ((float) qVar.a()));
    }
}

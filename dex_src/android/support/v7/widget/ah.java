package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.widget.RecyclerView.e;
import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: DefaultItemAnimator.java
final class ah implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ af b;

    ah(af afVar, ArrayList arrayList) {
        this.b = afVar;
        this.a = arrayList;
    }

    public final void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            View view;
            a aVar = (a) it.next();
            e eVar = this.b;
            t tVar = aVar.a;
            View view2 = tVar == null ? null : tVar.itemView;
            t tVar2 = aVar.b;
            if (tVar2 != null) {
                view = tVar2.itemView;
            } else {
                view = null;
            }
            if (view2 != null) {
                ViewPropertyAnimatorCompat duration = ViewCompat.animate(view2).setDuration(eVar.l);
                eVar.g.add(aVar.a);
                duration.translationX((float) (aVar.e - aVar.c));
                duration.translationY((float) (aVar.f - aVar.d));
                duration.alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED).setListener(new am(eVar, aVar, duration)).start();
            }
            if (view != null) {
                duration = ViewCompat.animate(view);
                eVar.g.add(aVar.b);
                duration.translationX(AutoScrollHelper.RELATIVE_UNSPECIFIED).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED).setDuration(eVar.l).alpha(1.0f).setListener(new an(eVar, aVar, duration, view)).start();
            }
        }
        this.a.clear();
        this.b.c.remove(this.a);
    }
}

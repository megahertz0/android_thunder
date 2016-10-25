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
final class ag implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ af b;

    ag(af afVar, ArrayList arrayList) {
        this.b = afVar;
        this.a = arrayList;
    }

    public final void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            e eVar = this.b;
            t tVar = bVar.a;
            int i = bVar.b;
            int i2 = bVar.c;
            int i3 = bVar.d;
            int i4 = bVar.e;
            View view = tVar.itemView;
            i = i3 - i;
            i2 = i4 - i2;
            if (i != 0) {
                ViewCompat.animate(view).translationX(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            }
            if (i2 != 0) {
                ViewCompat.animate(view).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            }
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
            eVar.e.add(tVar);
            animate.setDuration(eVar.k).setListener(new al(eVar, tVar, i, i2, animate)).start();
        }
        this.a.clear();
        this.b.b.remove(this.a);
    }
}

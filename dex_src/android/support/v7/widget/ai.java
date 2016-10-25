package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView.e;
import android.support.v7.widget.RecyclerView.t;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: DefaultItemAnimator.java
final class ai implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ af b;

    ai(af afVar, ArrayList arrayList) {
        this.b = afVar;
        this.a = arrayList;
    }

    public final void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            t tVar = (t) it.next();
            e eVar = this.b;
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(tVar.itemView);
            eVar.d.add(tVar);
            animate.alpha(1.0f).setDuration(eVar.i).setListener(new ak(eVar, tVar, animate)).start();
        }
        this.a.clear();
        this.b.a.remove(this.a);
    }
}

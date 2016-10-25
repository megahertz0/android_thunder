package com.android.volley.toolbox;

import com.android.volley.toolbox.i.c;
import java.util.Iterator;

// compiled from: ImageLoader.java
final class l implements Runnable {
    final /* synthetic */ i a;

    l(i iVar) {
        this.a = iVar;
    }

    public final void run() {
        for (a aVar : this.a.d.values()) {
            Iterator it = aVar.c.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.b != null) {
                    if (aVar.b == null) {
                        cVar.a = aVar.a;
                        cVar.b.a(cVar, false);
                    } else {
                        cVar.b.onErrorResponse(aVar.b);
                    }
                }
            }
        }
        this.a.d.clear();
        this.a.e = null;
    }
}

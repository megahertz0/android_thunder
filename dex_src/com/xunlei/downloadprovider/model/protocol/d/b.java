package com.xunlei.downloadprovider.model.protocol.d;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import java.util.Iterator;

// compiled from: SearchBarHintManager.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(Message message) {
        if (a.a(this.a) != null) {
            Iterator it = a.a(this.a).iterator();
            while (it.hasNext()) {
                ((a.a) it.next()).a(a.b(this.a), a.c(this.a));
            }
        }
    }
}

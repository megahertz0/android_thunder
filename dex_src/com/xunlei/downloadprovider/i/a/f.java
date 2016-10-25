package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.io.File;

// compiled from: Update.java
final class f implements OnClickListener {
    final /* synthetic */ c a;

    f(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (c.n(this.a)) {
            c.b(this.a, 0);
            return;
        }
        this.a.a();
        c.a(this.a, false);
        c.a(this.a, c.a(this.a).a, false);
        File file = new File(c.j(this.a), c.k(this.a));
        if (file.exists()) {
            file.delete();
        }
    }
}

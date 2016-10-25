package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import java.io.File;

// compiled from: Update.java
final class g implements OnKeyListener {
    final /* synthetic */ c a;

    g(c cVar) {
        this.a = cVar;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (84 == i) {
            return true;
        }
        if (4 != i) {
            return false;
        }
        if (c.n(this.a)) {
            c.b(this.a, 0);
            return true;
        }
        this.a.a();
        c.a(this.a, false);
        c.a(this.a, c.a(this.a).a, false);
        File file = new File(c.j(this.a), c.k(this.a));
        if (!file.exists()) {
            return true;
        }
        file.delete();
        return true;
    }
}

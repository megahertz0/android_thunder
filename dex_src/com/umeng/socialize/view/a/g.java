package com.umeng.socialize.view.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: ACProgressFlower.java
class g implements OnDismissListener {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (f.a(this.a) != null) {
            f.a(this.a).cancel();
            f.a(this.a, null);
        }
        f.a(this.a, 0);
        f.a(this.a, null);
    }
}

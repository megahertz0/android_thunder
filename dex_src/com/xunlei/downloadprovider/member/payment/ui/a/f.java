package com.xunlei.downloadprovider.member.payment.ui.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: PayProblemAdapter.java
final class f implements OnClickListener {
    final /* synthetic */ c a;

    f(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        if (this.a.b != null) {
            this.a.b.a();
        }
    }
}

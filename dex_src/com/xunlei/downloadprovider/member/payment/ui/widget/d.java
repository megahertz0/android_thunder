package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: VoucherListDialog.java
final class d implements OnClickListener {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            this.a.c.a();
        }
        this.a.dismiss();
    }
}

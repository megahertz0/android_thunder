package com.xunlei.downloadprovider.commonview.dialog;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLAlertDialog.java
final class f implements OnClickListener {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    public final void onClick(View view) {
        if (this.a.h != null) {
            this.a.h.onClick(this.a, -1);
        } else {
            this.a.dismiss();
        }
    }
}

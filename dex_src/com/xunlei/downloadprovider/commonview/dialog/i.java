package com.xunlei.downloadprovider.commonview.dialog;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLCheckBoxDialog.java
final class i implements OnClickListener {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public final void onClick(View view) {
        if (h.a(this.a) == null) {
            this.a.dismiss();
        } else {
            h.a(this.a).onClick(this.a, 0);
        }
    }
}

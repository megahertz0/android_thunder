package com.xunlei.downloadprovider.commonview.dialog;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLCheckBoxDialog.java
final class j implements OnClickListener {
    final /* synthetic */ h a;

    j(h hVar) {
        this.a = hVar;
    }

    public final void onClick(View view) {
        if (this.a.e == null) {
            this.a.dismiss();
        } else {
            this.a.e.onClick(this.a, 1);
        }
    }
}

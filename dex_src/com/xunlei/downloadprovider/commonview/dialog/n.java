package com.xunlei.downloadprovider.commonview.dialog;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLImageButtonDialog.java
final class n implements OnClickListener {
    final /* synthetic */ k a;

    n(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        ((DialogInterface.OnClickListener) view.getTag()).onClick(this.a, 0);
    }
}

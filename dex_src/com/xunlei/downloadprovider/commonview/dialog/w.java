package com.xunlei.downloadprovider.commonview.dialog;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XLUpgradeDialog.java
final class w implements OnClickListener {
    final /* synthetic */ t a;

    w(t tVar) {
        this.a = tVar;
    }

    public final void onClick(View view) {
        ((DialogInterface.OnClickListener) view.getTag()).onClick(this.a, 0);
    }
}

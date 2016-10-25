package com.xunlei.downloadprovider.qrcode.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: QRCodeResultTextDialog.java
final class h implements OnDismissListener {
    final /* synthetic */ e a;

    h(e eVar) {
        this.a = eVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        e.c(this.a).obtainMessage(1044494).sendToTarget();
    }
}

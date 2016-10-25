package com.xunlei.downloadprovider.qrcode.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: QRCodeResultURLDialog.java
final class l implements OnDismissListener {
    final /* synthetic */ i a;

    l(i iVar) {
        this.a = iVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        if (this.a.getTag() == null) {
            i.b(this.a).obtainMessage(1044494).sendToTarget();
        }
    }
}

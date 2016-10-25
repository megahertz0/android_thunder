package com.xunlei.downloadprovider.qrcode.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: QRCodeResultQueryDialog.java
final class d implements OnDismissListener {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        Object tag = this.a.getTag();
        if ((tag instanceof Boolean) && ((Boolean) tag).booleanValue()) {
            a.b(this.a).obtainMessage(1044493, a.a(this.a)).sendToTarget();
        } else {
            a.b(this.a).obtainMessage(1044494).sendToTarget();
        }
        this.a.setTag(Boolean.valueOf(false));
    }
}

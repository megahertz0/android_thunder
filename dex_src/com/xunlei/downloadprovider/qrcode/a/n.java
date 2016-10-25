package com.xunlei.downloadprovider.qrcode.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.g.k;

// compiled from: QRCodeResultURLDialog.java
final class n implements OnClickListener {
    final /* synthetic */ k a;
    final /* synthetic */ i b;

    n(i iVar, k kVar) {
        this.b = iVar;
        this.a = kVar;
    }

    public final void onClick(View view) {
        this.b.setTag(this.a);
        i.b(this.b).obtainMessage(1044495, this.a).sendToTarget();
        this.b.dismiss();
        new StringBuilder("on create task clicked. XunleiScanCodeResult:").append(this.a);
    }
}

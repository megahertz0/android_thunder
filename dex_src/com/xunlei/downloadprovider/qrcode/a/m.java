package com.xunlei.downloadprovider.qrcode.a;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: QRCodeResultURLDialog.java
final class m implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ i b;

    m(i iVar, String str) {
        this.b = iVar;
        this.a = str;
    }

    public final void onClick(View view) {
        this.b.setTag(this.a);
        i.b(this.b).obtainMessage(1044495, this.a).sendToTarget();
        this.b.dismiss();
        new StringBuilder("on create task clicked. url:").append(this.a);
    }
}

package com.xunlei.downloadprovider.qrcode.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.qrcode.b.c;

// compiled from: QRCodeResultURLDialog.java
final class p implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ i b;

    p(i iVar, String str) {
        this.b = iVar;
        this.a = str;
    }

    public final void onClick(View view) {
        c.a(this.b.getContext(), this.a);
        this.b.dismiss();
    }
}

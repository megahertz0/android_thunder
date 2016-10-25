package com.xunlei.downloadprovider.qrcode.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: QRCodeResultURLDialog.java
final class o implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ i b;

    o(i iVar, String str) {
        this.b = iVar;
        this.a = str;
    }

    public final void onClick(View view) {
        BrowserUtil.a();
        BrowserUtil.a(this.b.getContext(), this.a, StartFromType.scan_qrcode);
        this.b.dismiss();
    }
}

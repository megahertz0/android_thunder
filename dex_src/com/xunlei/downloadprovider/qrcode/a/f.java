package com.xunlei.downloadprovider.qrcode.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.qrcode.b.c;

// compiled from: QRCodeResultTextDialog.java
final class f implements OnClickListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        if (e.a(this.a) != null) {
            e.a(this.a).a();
            this.a.dismiss();
            return;
        }
        c.a(this.a.getContext(), e.b(this.a));
        this.a.dismiss();
    }
}

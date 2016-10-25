package com.xunlei.downloadprovider.qrcode.a;

import android.os.Message;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.b.c;
import com.xunlei.downloadprovider.model.protocol.g.k;

// compiled from: QRCodeResultQueryDialog.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(Message message) {
        switch (message.what) {
            case XiaomiOAuthConstants.SCOPE_MI_CLOUD_GALLERY:
                c cVar = (c) message.obj;
                if (cVar.b instanceof k) {
                    a.a(this.a, cVar);
                } else {
                    a.a(this.a, message.arg1);
                }
            default:
                break;
        }
    }
}

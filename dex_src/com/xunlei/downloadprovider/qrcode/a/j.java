package com.xunlei.downloadprovider.qrcode.a;

import android.os.Message;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.b.c;

// compiled from: QRCodeResultURLDialog.java
final class j implements a {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final void a(Message message) {
        switch (message.what) {
            case XLYunboMassage.MSG_TASKFINISHED:
                c cVar = (c) message.obj;
                if (cVar.b != null) {
                    i.a(this.a, (String) cVar.b);
                    i.b(this.a, i.a(this.a));
                }
            default:
                break;
        }
    }
}

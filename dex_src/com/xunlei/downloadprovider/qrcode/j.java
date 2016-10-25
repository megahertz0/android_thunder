package com.xunlei.downloadprovider.qrcode;

import android.os.Handler;
import android.os.Message;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.b.c;
import com.xunlei.downloadprovider.model.protocol.g.k;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.tdlive.R;

// compiled from: LocalScancodeActivity.java
final class j extends Handler {
    final /* synthetic */ LocalScancodeActivity a;

    j(LocalScancodeActivity localScancodeActivity) {
        this.a = localScancodeActivity;
    }

    public final void handleMessage(Message message) {
        c cVar;
        LocalScancodeActivity localScancodeActivity;
        Object obj;
        switch (message.what) {
            case 10002:
                cVar = (c) message.obj;
                if (cVar.b != null) {
                    this.a.l = 0;
                    this.a.n = (String) cVar.b;
                    this.a.s = this.a.n;
                    this.a.l = this.a.l | 32;
                    this.a.l = this.a.l | 1;
                    localScancodeActivity = this.a;
                    this.a.w;
                    localScancodeActivity.m = com.xunlei.downloadprovider.qrcode.b.c.a(this.a.n);
                    this.a.o = false;
                    this.a.a(false);
                }
            case 10003:
                cVar = (c) message.obj;
                if (cVar.b != null) {
                    this.a.l = 0;
                    this.a.n = (String) cVar.b;
                    this.a.s = this.a.n;
                    this.a.m = R.drawable.ic_dl_apk;
                    this.a.l = this.a.l | 32;
                    this.a.l = this.a.l | 1;
                    localScancodeActivity = this.a;
                    this.a.w;
                    localScancodeActivity.m = com.xunlei.downloadprovider.qrcode.b.c.a(this.a.n);
                    this.a.o = false;
                    this.a.a(false);
                }
            case 11000:
                cVar = (c) message.obj;
                this.a.l = 0;
                if (cVar.b != null) {
                    this.a.l = 0;
                    this.a.C = (k) cVar.b;
                    this.a.q = this.a.C.a;
                    this.a.r = this.a.C.d;
                    this.a.n = this.a.s = this.a.C.b;
                    localScancodeActivity = this.a;
                    this.a.w;
                    localScancodeActivity.m = com.xunlei.downloadprovider.qrcode.b.c.a(this.a.q);
                    if (this.a.C.i == 1) {
                        this.a.l = this.a.l | 32;
                        this.a.l = this.a.l | 1;
                    } else if (this.a.C.i == 2) {
                        this.a.l = this.a.l | 32;
                        this.a.l = this.a.l | 2;
                    } else {
                        int i = this.a.C.i;
                    }
                    StatReporter.reportQRXunleiDecode("sucess", System.currentTimeMillis() - this.a.x, "localCode");
                } else {
                    this.a.l = this.a.l | 64;
                    this.a.l = this.a.l | 2;
                    StatReporter.reportQRXunleiDecode(MsgConstant.KEY_FAIL, System.currentTimeMillis() - this.a.x, "localCode");
                }
                this.a.a(true);
                obj = message.obj;
                if (obj instanceof String) {
                    LocalScancodeActivity.e(this.a, (String) obj);
                } else if (obj instanceof k) {
                    this.a.z = (k) obj;
                    LocalScancodeActivity.e(this.a, this.a.z.b);
                }
            case 1044495:
                obj = message.obj;
                if (obj instanceof String) {
                    LocalScancodeActivity.e(this.a, (String) obj);
                } else if (obj instanceof k) {
                    this.a.z = (k) obj;
                    LocalScancodeActivity.e(this.a, this.a.z.b);
                }
            default:
                break;
        }
    }
}

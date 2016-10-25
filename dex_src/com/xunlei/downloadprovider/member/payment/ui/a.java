package com.xunlei.downloadprovider.member.payment.ui;

import android.os.Handler;
import android.os.Message;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;

// compiled from: ActivationActivity.java
final class a extends Handler {
    final /* synthetic */ ActivationActivity a;

    a(ActivationActivity activationActivity) {
        this.a = activationActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 904:
                this.a.h.setVisibility(XZBDevice.Wait);
                if (message.obj == null) {
                    this.a.d.setImageDrawable(null);
                    if (b.a(this.a.getApplicationContext())) {
                        XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, "\u83b7\u53d6\u9a8c\u8bc1\u7801\u5931\u8d25");
                        return;
                    } else {
                        XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, this.a.getResources().getString(2131232509));
                        return;
                    }
                }
                this.a.j = (com.xunlei.downloadprovider.member.a.a) message.obj;
                this.a.d.setImageBitmap(this.a.j.a);
            case XLErrorCode.OAUTH_IP_LIMIT:
                ActivationActivity.d(this.a);
                LoginHelper.a().a(this.a.q);
                LoginHelper.a().s();
                LoginHelper.a().v();
            case 906:
                this.a.a();
                this.a.c.setText(com.umeng.a.d);
                this.a.k = (String) message.obj;
                if (this.a.k != null) {
                    this.a.k = this.a.k.replace("\u5151\u6362\u7801", "\u6fc0\u6d3b\u7801");
                    if (this.a.k.contains("\u6fc0\u6d3b\u7801")) {
                        this.a.o = false;
                    }
                }
                this.a.b();
                XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, this.a.k);
            default:
                break;
        }
    }
}

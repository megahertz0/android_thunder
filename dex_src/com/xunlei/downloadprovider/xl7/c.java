package com.xunlei.downloadprovider.xl7;

import android.os.Message;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;

// compiled from: XL7AccelerateDialogActivity.java
final class c implements a {
    final /* synthetic */ XL7AccelerateDialogActivity a;

    c(XL7AccelerateDialogActivity xL7AccelerateDialogActivity) {
        this.a = xL7AccelerateDialogActivity;
    }

    public final void a(Message message) {
        switch (message.what) {
            case 6000:
                XLToast.a(BrothersApplication.a, XLToastType.XLTOAST_TYPE_ALARM, this.a.getResources().getString(2131233237));
                this.a.finish();
            case XLErrorCode.ALI_AUTH_USER_CANCLE:
                this.a.finish();
            default:
                break;
        }
    }
}

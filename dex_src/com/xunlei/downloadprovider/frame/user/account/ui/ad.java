package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI.WXApp;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;

// compiled from: UserAccountSecurityActivity.java
final class ad implements OnClickListener {
    final /* synthetic */ UserAccountSecurityActivity a;

    ad(UserAccountSecurityActivity userAccountSecurityActivity) {
        this.a = userAccountSecurityActivity;
    }

    public final void onClick(View view) {
        if (b.a(this.a)) {
            switch (view.getId()) {
                case 2131755318:
                    if (c.a(WXApp.WXAPP_PACKAGE_NAME)) {
                        UserAccountSecurityActivity.a(this.a, view, XZBDevice.DOWNLOAD_LIST_FAILED, this.a.l);
                        return;
                    } else {
                        XLToast.b(2131232960.a, XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(r3));
                        return;
                    }
                case 2131755321:
                    if (c.a(Constants.PACKAGE_QQ)) {
                        UserAccountSecurityActivity.a(this.a, view, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, this.a.m);
                        return;
                    } else {
                        XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, 2131232963.a.getString(2131232963));
                        return;
                    }
                case 2131755324:
                    UserAccountSecurityActivity.a(this.a, view, XZBDevice.DOWNLOAD_LIST_RECYCLE, this.a.n);
                    return;
                default:
                    return;
            }
        }
        XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }
}

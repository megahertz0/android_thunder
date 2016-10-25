package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xllib.a.b;

// compiled from: UserAccountSecurityActivity.java
final class ag implements OnClickListener {
    final /* synthetic */ UserAccountSecurityActivity a;

    ag(UserAccountSecurityActivity userAccountSecurityActivity) {
        this.a = userAccountSecurityActivity;
    }

    public final void onClick(View view) {
        if (b.a(this.a)) {
            XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, 2131232962.a.getString(2131232962));
        } else {
            XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
        }
    }
}

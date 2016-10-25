package com.xunlei.downloadprovider.frame.user.account.ui;

import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.a.b;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper.SexType;

// compiled from: UserAccountInfoActivityNew.java
final class c implements b {
    final /* synthetic */ UserAccountInfoActivityNew a;

    c(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        this.a = userAccountInfoActivityNew;
    }

    public final void a(int i) {
        if (i == 0) {
            if (com.xunlei.xllib.a.b.a(this.a.getBaseContext())) {
                this.a.j.a(SexType.male);
                this.a.m = SexType.male;
            } else {
                XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
            }
        }
        if (i == 1) {
            if (com.xunlei.xllib.a.b.a(this.a.getBaseContext())) {
                this.a.j.a(SexType.female);
                this.a.m = SexType.female;
            } else {
                XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
            }
        }
        if (i == 2) {
            k.c("cancel", null);
        }
    }
}

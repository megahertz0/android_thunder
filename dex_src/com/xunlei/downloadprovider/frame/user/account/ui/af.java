package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import com.umeng.a;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper.l;

// compiled from: UserAccountSecurityActivity.java
final class af implements l {
    final /* synthetic */ View a;
    final /* synthetic */ UserAccountSecurityActivity b;

    af(UserAccountSecurityActivity userAccountSecurityActivity, View view) {
        this.b = userAccountSecurityActivity;
        this.a = view;
    }

    public final void a(int i) {
        UserAccountSecurityActivity.a(this.b, i, -1);
        UserAccountSecurityActivity.a(this.b, this.a, -1);
        UserAccountSecurityActivity.a(this.b, i, null);
        com.xunlei.downloadprovider.frame.user.account.l.a().b(i, a.d);
    }

    public final void a() {
        XLToast.b(this.b, XLToastType.XLTOAST_TYPE_ALARM, 2131232965.a.getString(2131232965));
    }
}

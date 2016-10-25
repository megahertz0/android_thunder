package com.xunlei.downloadprovider.frame.user.account.ui;

import android.support.v4.content.res.ResourcesCompat;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper.SexType;
import com.xunlei.downloadprovider.member.login.LoginHelper.r;

// compiled from: UserAccountInfoActivityNew.java
final class h implements r {
    final /* synthetic */ UserAccountInfoActivityNew a;

    h(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        this.a = userAccountInfoActivityNew;
    }

    public final void a(int i) {
        if (i == 0) {
            this.a.j.s();
            if (this.a.m == null) {
                return;
            }
            if (this.a.m.equals(SexType.male)) {
                this.a.f.getAccountItemTipText().setText(2131232976);
                this.a.f.setAccountItemTipIcon(ResourcesCompat.getDrawable(this.a.getResources(), 2130839579, null));
                this.a.l = 2131232976;
                k.c(MsgConstant.KEY_SUCCESS, "male");
                return;
            } else if (this.a.m.equals(SexType.female)) {
                this.a.f.getAccountItemTipText().setText(2131232975);
                this.a.f.setAccountItemTipIcon(ResourcesCompat.getDrawable(this.a.getResources(), 2130839586, null));
                this.a.l = 2131232975;
                k.c(MsgConstant.KEY_SUCCESS, "female");
                return;
            } else {
                return;
            }
        }
        XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u63d0\u4ea4\u5931\u8d25");
        k.c(MsgConstant.KEY_FAIL, null);
    }
}

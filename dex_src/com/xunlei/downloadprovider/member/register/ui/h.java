package com.xunlei.downloadprovider.member.register.ui;

import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.downloadprovider.member.register.view.e.a;

// compiled from: MobileSetupActivity.java
final class h implements a {
    final /* synthetic */ MobileSetupActivity a;

    h(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void a() {
        XLRegisterUtil.getInstance().getVerifyCodeByType(MobileSetupActivity.t(this.a));
    }

    public final void a(String str) {
        MobileSetupActivity.e(this.a, str);
    }
}

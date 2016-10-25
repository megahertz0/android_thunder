package com.xunlei.downloadprovider.member.register.ui;

import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;
import com.xunlei.downloadprovider.member.register.b;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.web.BrowserUtil;

// compiled from: MobileSetupActivity.java
final class g implements c {
    final /* synthetic */ boolean a;
    final /* synthetic */ MobileSetupActivity b;

    g(MobileSetupActivity mobileSetupActivity, boolean z) {
        this.b = mobileSetupActivity;
        this.a = z;
    }

    public final void a(int i) {
        int i2 = 1;
        new StringBuilder("userLoginWithSessionId() onLoginCompleted() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (MobileSetupActivity.b(this.b) == 1) {
            int i3 = 1;
        } else {
            boolean z = false;
        }
        this.b.f();
        LoginHelper.a().v = null;
        if (MobileSetupActivity.r(this.b)) {
            if (i3 != 0) {
                this.b.a(2131232139);
            }
            this.b.finish();
            MobileSetupActivity.q(this.b);
        } else if (MobileSetupActivity.s(this.b)) {
            BrowserUtil.a();
            BrowserUtil.a(this.b, "http://m.sjzhushou.com/v2/turnplate/index.html", this.b.getResources().getString(2131231029), 2069, null);
        }
        if (i == 0) {
            if (!MobileSetupActivity.r(this.b)) {
                if (i3 != 0) {
                    this.b.finish();
                    if (MobileSetupActivity.a) {
                        VodUtil.a(this.b, VodUtil.c, VodUtil.d);
                        MobileSetupActivity.a = false;
                    }
                } else {
                    this.b.a(2131232140);
                    this.b.finish();
                }
            }
            String j = MobileSetupActivity.j(this.b);
            int i4 = LoginHelper.a().h;
            if (!LoginHelper.a().f()) {
                i2 = 0;
            }
            String str = this.a ? MiPushClient.COMMAND_REGISTER : "login";
            String str2 = "phone_login_success";
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g a = com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g.a("android_phone_register", str2, str2);
            a.a("from_src", j);
            a.a("vip_type", (long) i4);
            a.a("is_vip", (long) i2);
            a.a("result_type", str);
            b.a(a);
            return;
        }
        this.b.b(2131232984);
        b.a(i);
    }
}

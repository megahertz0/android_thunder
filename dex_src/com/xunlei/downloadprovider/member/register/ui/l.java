package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.register.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: MobileSetupActivity.java
final class l implements OnClickListener {
    final /* synthetic */ MobileSetupActivity a;

    l(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onClick(View view) {
        if (MobileSetupActivity.a(this.a)) {
            this.a.b();
        }
        String str;
        if (MobileSetupActivity.b(this.a) == 2) {
            str = "phone_reg_passcode_get";
            b.a(g.a("android_phone_register", str, str));
        } else if (MobileSetupActivity.b(this.a) == 1) {
            str = "phone_login_passcode_get";
            b.a(g.a("android_phone_register", str, str));
        }
    }
}

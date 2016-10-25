package com.xunlei.downloadprovider.member.login.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.register.b;
import com.xunlei.downloadprovider.member.register.ui.MobileSetupActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: LoginActivity.java
final class m implements OnClickListener {
    final /* synthetic */ LoginActivity a;

    m(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onClick(View view) {
        MobileSetupActivity.a(this.a, 1, this.a.K);
        if (this.a.K.contains("\u4e91\u64ad")) {
            MobileSetupActivity.a = true;
        }
        StatReporter.reportMobilephoneLoginClick();
        b.a("login_home");
    }
}

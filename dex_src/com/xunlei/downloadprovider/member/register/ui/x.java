package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: RegisterSuccessActivity.java
final class x implements OnClickListener {
    final /* synthetic */ RegisterSuccessActivity a;

    x(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void onClick(View view) {
        this.a.l.a("phone_register_login");
        String str = "register_head_click";
        k.a(g.a("android_personal_account", str, str));
    }
}

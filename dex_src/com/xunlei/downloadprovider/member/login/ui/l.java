package com.xunlei.downloadprovider.member.login.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.register.b;
import com.xunlei.downloadprovider.member.register.ui.MobileSetupActivity;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LoginActivity.java
final class l implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ LoginActivity b;

    l(LoginActivity loginActivity, Dialog dialog) {
        this.b = loginActivity;
        this.a = dialog;
    }

    public final void onClick(View view) {
        MobileSetupActivity.a(this.b, XZBDevice.DOWNLOAD_LIST_RECYCLE, this.b.K);
        this.a.dismiss();
        String str = "phone_register";
        b.a(g.a("android_phone_register", str, str));
    }
}

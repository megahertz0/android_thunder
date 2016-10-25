package com.xunlei.downloadprovider.member.register.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.register.b;

// compiled from: MobileSetupActivity.java
final class j implements OnClickListener {
    final /* synthetic */ MobileSetupActivity a;

    j(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        MobileSetupActivity.a(this.a, 1, MobileSetupActivity.u(this.a).getText().toString());
        b.a("register_altert");
        dialogInterface.dismiss();
    }
}

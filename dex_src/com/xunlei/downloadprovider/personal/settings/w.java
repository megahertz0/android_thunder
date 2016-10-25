package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: SettingsIndexFragment.java
final class w implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ SettingsIndexFragment b;

    w(SettingsIndexFragment settingsIndexFragment, Context context) {
        this.b = settingsIndexFragment;
        this.a = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (SettingsIndexFragment.i(this.b) != null) {
            SettingsIndexFragment.i(this.b).dismiss();
            SettingsIndexFragment.j(this.b);
        }
        LoginHelper.a().a(new x(this));
        ((Activity) this.a).finish();
        if (new bx().a) {
            LoginHelper.a();
            LoginHelper.b();
        }
    }
}

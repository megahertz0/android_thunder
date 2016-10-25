package com.xunlei.downloadprovider.frame.user.account.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity;
import com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: UserAccountInfoActivityNew.java
final class a implements OnClickListener {
    final /* synthetic */ UserAccountInfoActivityNew a;

    a(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        this.a = userAccountInfoActivityNew;
    }

    public final void onClick(View view) {
        String str;
        switch (view.getId()) {
            case 2131755297:
                PersonalSpaceActivity.a(this.a, From.ACCOUNT_CENTER, LoginHelper.a().j, LoginHelper.a().i, LoginHelper.a().l);
            case 2131755298:
                UserAccountInfoActivityNew.a(this.a);
                str = "account_head_click";
                k.a(g.a("android_personal_account", str, str));
            case 2131755299:
                UserAccountInfoActivityNew.b(this.a);
                str = "account_nick_conf";
                k.a(g.a("android_personal_account", str, str));
            case 2131755300:
                UserAccountInfoActivityNew.c(this.a);
                str = "account_gender_conf";
                k.a(g.a("android_personal_account", str, str));
            case 2131755301:
                this.a.startActivity(new Intent(this.a, UserAccountSecurityActivity.class));
                str = "account_safety_conf";
                k.a(g.a("android_personal_account", str, str));
            case 2131755302:
                UserAccountInfoActivityNew.d(this.a);
                str = "account_vip_conf";
                k.a(g.a("android_personal_account", str, str));
            default:
                break;
        }
    }
}

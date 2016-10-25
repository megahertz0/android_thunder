package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: UserAccountNicknameActivity.java
final class k implements OnClickListener {
    final /* synthetic */ UserAccountNicknameActivity a;

    k(UserAccountNicknameActivity userAccountNicknameActivity) {
        this.a = userAccountNicknameActivity;
    }

    public final void onClick(View view) {
        com.xunlei.downloadprovider.frame.user.account.k.b("account_center", "cancel");
        this.a.finish();
    }
}

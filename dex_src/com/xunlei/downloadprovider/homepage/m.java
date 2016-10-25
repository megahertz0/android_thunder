package com.xunlei.downloadprovider.homepage;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;

// compiled from: LoginHeaderView.java
public final class m implements OnClickListener {
    final /* synthetic */ Context a;

    public m(Context context) {
        this.a = context;
    }

    public final void onClick(View view) {
        LoginActivity.a(this.a, "home_topbar");
        ChoicenessReporter.a("login", "login");
    }
}

package com.xunlei.downloadprovider.homepage;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.ui.HomeChoicenessFragment;

// compiled from: LoginHeaderView.java
public final class l implements OnClickListener {
    final /* synthetic */ HomeChoicenessFragment a;

    public l(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void onClick(View view) {
        new n().a.edit().putBoolean("is_closed", true).apply();
        this.a.b();
        ChoicenessReporter.a("login", "close");
    }
}

package com.xunlei.downloadprovider.homepage.interest.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.ui.HomeChoicenessFragment;
import com.xunlei.downloadprovider.homepage.interest.a.j;

// compiled from: InterestHeaderView.java
public final class c implements OnClickListener {
    final /* synthetic */ HomeChoicenessFragment a;

    public c(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void onClick(View view) {
        this.a.c();
        new j().c();
        ChoicenessReporter.e();
    }
}

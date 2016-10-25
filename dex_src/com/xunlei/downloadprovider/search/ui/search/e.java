package com.xunlei.downloadprovider.search.ui.search;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;

// compiled from: SearchActivity.java
final class e implements OnClickListener {
    final /* synthetic */ SearchActivity a;

    e(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public final void onClick(View view) {
        f.a(SearchActivity.g(this.a), "other", "cancel");
        this.a.a.a();
        this.a.finish();
        this.a.overridePendingTransition(2131034190, 2131034191);
    }
}

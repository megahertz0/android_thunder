package com.xunlei.downloadprovider.search.ui.search;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SearchActivity.java
final class f implements OnClickListener {
    final /* synthetic */ SearchActivity a;

    f(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public final void onClick(View view) {
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f.a(SearchActivity.g(this.a), "search_prepare", "delete");
    }
}

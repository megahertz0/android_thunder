package com.xunlei.downloadprovider.search.ui.search;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;

// compiled from: SearchActivity.java
final class d implements OnClickListener {
    final /* synthetic */ SearchActivity a;

    d(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public final void onClick(View view) {
        f.a(SearchActivity.g(this.a), "keybord", "bar");
    }
}

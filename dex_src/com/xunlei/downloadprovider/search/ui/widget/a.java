package com.xunlei.downloadprovider.search.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: HistoryView.java
final class a implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ int b;
    final /* synthetic */ HistoryView c;

    a(HistoryView historyView, View view, int i) {
        this.c = historyView;
        this.a = view;
        this.b = i;
    }

    public final void onClick(View view) {
        if (HistoryView.a(this.c) != null) {
            HistoryView.a(this.c).onItemClick(null, this.a, this.b, (long) this.c.getId());
        }
    }
}

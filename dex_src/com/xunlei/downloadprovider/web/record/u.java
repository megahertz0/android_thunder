package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: RecordPageView.java
final class u implements OnClickListener {
    final /* synthetic */ RecordPageView a;

    u(RecordPageView recordPageView) {
        this.a = recordPageView;
    }

    public final void onClick(View view) {
        if (RecordPageView.a(this.a).size() > 0 && RecordPageView.b(this.a) != null) {
            RecordPageView.b(this.a).a(RecordPageView.a(this.a).size());
        }
    }
}

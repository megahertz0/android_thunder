package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnLongClickListener;

// compiled from: RecordPageView.java
final class z implements OnLongClickListener {
    final /* synthetic */ t a;
    final /* synthetic */ RecordPageView$b b;

    z(RecordPageView$b recordPageView$b, t tVar) {
        this.b = recordPageView$b;
        this.a = tVar;
    }

    public final boolean onLongClick(View view) {
        if (!RecordPageView.n(this.b.a)) {
            this.a.a = true;
            RecordPageView.a(this.b.a).add(this.a);
            RecordPageView.a(this.b.a, this.a);
            RecordPageView.p(this.b.a);
            RecordPageView.o(this.b.a);
        }
        return true;
    }
}

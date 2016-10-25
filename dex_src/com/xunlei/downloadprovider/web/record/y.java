package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.o;

// compiled from: RecordPageView.java
final class y implements OnClickListener {
    final /* synthetic */ t a;
    final /* synthetic */ RecordPageView$b b;

    y(RecordPageView$b recordPageView$b, t tVar) {
        this.b = recordPageView$b;
        this.a = tVar;
    }

    public final void onClick(View view) {
        if (RecordPageView.n(this.b.a)) {
            if (this.a.a) {
                RecordPageView.a(this.b.a).remove(this.a);
            } else {
                RecordPageView.a(this.b.a).add(this.a);
            }
            this.a.a = !this.a.a;
            RecordPageView.g(this.b.a);
            RecordPageView.o(this.b.a);
        } else if (RecordPageView.b(this.b.a) != null) {
            String str;
            if ("favor".equals(RecordPageView.d(this.b.a))) {
                str = ((b) this.a.b).c;
            } else {
                str = ((o) this.a.b).b;
            }
            RecordPageView.b(this.b.a).a(str);
        }
    }
}

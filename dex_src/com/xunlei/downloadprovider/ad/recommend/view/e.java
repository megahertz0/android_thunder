package com.xunlei.downloadprovider.ad.recommend.view;

import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.ad.recommend.c.b;
import com.xunlei.downloadprovider.download.tasklist.list.a.n;
import java.util.List;

// compiled from: RecommendAdViewHolder.java
final class e implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ b c;

    e(b bVar, List list, int i) {
        this.c = bVar;
        this.a = list;
        this.b = i;
    }

    public final void run() {
        boolean z;
        b.b;
        StringBuilder stringBuilder = new StringBuilder("postDelayed RecommendAdViewHolder.this.getLayoutPosition() != RecyclerView.NO_POSITION: ");
        if (this.c.getLayoutPosition() != -1) {
            z = true;
        } else {
            z = false;
        }
        stringBuilder.append(z);
        if (this.c.getLayoutPosition() != -1) {
            int i = 0;
            while (i < this.a.size() && i < this.c.a) {
                a aVar = (a) this.a.get(i);
                n.a().a(new b(this.c.d, aVar, com.xunlei.downloadprovider.ad.common.b.a(aVar), aVar.o().getSourceName(), aVar.j(), this.b, i + 1));
                i++;
            }
            return;
        }
        this.c.v.addAll(this.a);
    }
}

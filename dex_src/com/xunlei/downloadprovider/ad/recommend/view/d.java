package com.xunlei.downloadprovider.ad.recommend.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.ad.common.a;

// compiled from: RecommendAdViewHolder.java
final class d implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ RecommendAdItemView b;
    final /* synthetic */ int c;
    final /* synthetic */ b d;

    d(b bVar, a aVar, RecommendAdItemView recommendAdItemView, int i) {
        this.d = bVar;
        this.a = aVar;
        this.b = recommendAdItemView;
        this.c = i;
    }

    public final void onClick(View view) {
        b.b(this.d).a(this.a, this.b, this.c);
    }
}

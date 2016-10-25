package com.xunlei.downloadprovider.ad.recommend.b;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import com.xunlei.downloadprovider.ad.common.a;

// compiled from: RecommendAdPresenter.java
final class e implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ View b;
    final /* synthetic */ int c;
    final /* synthetic */ b d;

    e(b bVar, a aVar, View view, int i) {
        this.d = bVar;
        this.a = aVar;
        this.b = view;
        this.c = i;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        b.a(this.d, this.a, this.b, this.c);
        b.b(this.d).e();
    }
}

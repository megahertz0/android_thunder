package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.c;
import com.xunlei.downloadprovider.web.base.LongVideoDetailActivity;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: ChoicenessMovieSetItemView.java
final class e implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ a b;
    final /* synthetic */ ChoicenessMovieSetItemView c;

    e(ChoicenessMovieSetItemView choicenessMovieSetItemView, c cVar, a aVar) {
        this.c = choicenessMovieSetItemView;
        this.a = cVar;
        this.b = aVar;
    }

    public final void onClick(View view) {
        LongVideoDetailActivity.a(this.c.getContext(), BuildConfig.VERSION_NAME, this.a.i, this.a.a);
        ChoicenessReporter.a(this.b.d, this.b.b, "pic", this.b.f());
    }
}
